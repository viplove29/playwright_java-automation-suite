package com.vertafore.test.services.vendors;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.VendorResponse;
import com.vertafore.test.servicewrappers.UseVendorsTo;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
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
            new EMSActor().called("adam").withContext("adminContext"),
            new EMSActor().called("mary").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void vendorsReturnsAllVendors() {
    Actor bob = theActorCalled("bob");
    Actor doug = theActorCalled("doug");
    Actor adam = theActorCalled("adam");

    UseVendorsTo vendorsApi = new UseVendorsTo();

    bob.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    doug.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    adam.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }

  @Test
  @WithTag("19R2")
  public void vendorsReturnsAllVendors19R2() {
    Actor mary = theActorCalled("mary");

    UseVendorsTo vendorsApi = new UseVendorsTo();

    mary.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VendorResponse vendorResponse =
        LastResponse.received()
            .answeredBy(mary)
            .getBody()
            .jsonPath()
            .getList("", VendorResponse.class)
            .get(0);

    assertThat(vendorResponse).isNotEqualTo(null);
    assertThat(vendorResponse.getClass().getDeclaredFields().length).isEqualTo(6);
    assertThat(vendorResponse.geteMail()).isEqualTo(null);
    assertThat(vendorResponse.getFirstName()).isEqualTo(null);
    assertThat(vendorResponse.getLastName()).isEqualTo("Vendor");
    assertThat(vendorResponse.getVendorId()).isEqualTo("b1e6015e-564b-4843-be3f-ab965a0cdb24");
    assertThat(vendorResponse.getVendorCode()).isEqualTo("!!\"");
    assertThat(vendorResponse.getIsCompany()).isEqualTo("N");
  }
}
