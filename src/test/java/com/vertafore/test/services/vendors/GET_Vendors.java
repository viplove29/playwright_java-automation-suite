package com.vertafore.test.services.vendors;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.VendorResponse;
import com.vertafore.test.servicewrappers.UseVendorsTo;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_Vendors extends TokenSuperClass {

  // Helpers
  Random random = new Random();
  Integer randomInt = random.nextInt(5);

  /* The purpose of this test is to hit the Get /vendors endpoint using admin, user, and app context
  to validate proper response codes, as well as capturing a vendor object to then query through the
  same endpoint. By doing so the data validations are not hardcoded.
   */
  @Test
  public void vendorsReturnsAllVendors() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseVendorsTo vendorsApi = new UseVendorsTo();

    VADM_Admin.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(vendorsApi.GETVendorsOnTheVendorsController(null, null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VendorResponse vendorResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
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

    AADM_User.attemptsTo(
        vendorsApi.GETVendorsOnTheVendorsController(vendorId, vendorCode, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VendorResponse vendorCheck =
        LastResponse.received()
            .answeredBy(AADM_User)
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
