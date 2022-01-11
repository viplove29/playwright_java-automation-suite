package com.vertafore.test.services.vendors;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.VendorResponse;
import com.vertafore.test.servicewrappers.UseVendorsTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Vendors {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  // Helpers
  Random random = new Random();
  Integer randomInt = random.nextInt(5);

  /* The purpose of this test is to hit the Get /vendors endpoint using admin, user, and app context
  to validate proper response codes, as well as capturing a vendor object to then query through the
  same endpoint. By doing so the data validations are not hardcoded.
   */
  @Test
  public void vendorsReturnsAllVendors() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseVendorsTo vendorsApi = new UseVendorsTo();

    adam.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    doug.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    bob.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VendorResponse vendorResponse =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", VendorResponse.class)
            .get(randomInt);

    assertThat(vendorResponse.getClass().getDeclaredFields().length).isEqualTo(6);

    String vendorId = vendorResponse.getVendorId();
    String vendorCode = vendorResponse.getVendorCode();
    String email = vendorResponse.geteMail();
    String firstName = vendorResponse.getFirstName();
    String lastName = vendorResponse.getLastName();
    String isCompany = vendorResponse.getIsCompany();

    bob.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(vendorId, vendorCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VendorResponse vendorCheck =
        LastResponse.received()
            .answeredBy(bob)
            .getBody()
            .jsonPath()
            .getList("", VendorResponse.class)
            .get(0);

    assertThat(vendorCheck.getClass().getDeclaredFields().length).isEqualTo(6);
    assertThat(vendorCheck.getVendorId()).isEqualTo(vendorId);
    assertThat(vendorCheck.getVendorCode()).isEqualTo(vendorCode);
    assertThat(vendorCheck.geteMail()).isEqualTo(email);
    assertThat(vendorCheck.getFirstName()).isEqualTo(firstName);
    assertThat(vendorCheck.getLastName()).isEqualTo(lastName);
    assertThat(vendorCheck.getIsCompany()).isEqualTo(isCompany);
  }
}
