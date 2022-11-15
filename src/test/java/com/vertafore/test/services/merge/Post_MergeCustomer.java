package com.vertafore.test.services.merge;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseMergeCustomerTo;
import com.vertafore.test.util.CustomerUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class Post_MergeCustomer extends TokenSuperClass {
  @Test
  public void postMergeCustomer() throws InterruptedException {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseMergeCustomerTo mergeCustomerAPI = new UseMergeCustomerTo();
    UseCustomerTo customerAPI = new UseCustomerTo();

    // Stage Target Customer for Merge for orange partner user
    CustomerBasicInfoPostRequest targetCustomerORAN =
        CustomerUtil.createBasicCustomer("Customer", "Individual", ORAN_App);

    ORAN_App.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(targetCustomerORAN, ""));

    CustomerIdResponse targetCustomerIdResponseORAN =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    // STAGE CUSTOMER TO Merge for orange partner
    CustomerBasicInfoPostRequest sourceCustomerORAN =
        CustomerUtil.createBasicCustomer("Customer", "Individual", ORAN_App);

    ORAN_App.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(sourceCustomerORAN, ""));

    CustomerIdResponse sourceCustomerIdResponseORAN =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    // Merge Customer Post Request object
    MergeCustomerPostRequest mergeCustomerPostRequestORAN = new MergeCustomerPostRequest();

    // Set source customer Id and Target customer ID
    mergeCustomerPostRequestORAN.setSourceCustomerId(sourceCustomerIdResponseORAN.getCustomerId());
    mergeCustomerPostRequestORAN.setTargetCustomerId(targetCustomerIdResponseORAN.getCustomerId());

    // Make call to merge customer for ORAN_App and VADM_Admin user
    VADM_Admin.attemptsTo(
        mergeCustomerAPI.POSTMergeCustomerOnTheMergecustomerController(
            mergeCustomerPostRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        mergeCustomerAPI.POSTMergeCustomerOnTheMergecustomerController(
            mergeCustomerPostRequestORAN, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Wait for Merge to complete
    Thread.sleep(5000);

    // Stage Target Customer for Merge for AADM_User
    CustomerBasicInfoPostRequest targetCustomerAADM =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);

    AADM_User.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(targetCustomerAADM, ""));

    CustomerIdResponse targetCustomerIdResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    // STAGE CUSTOMER TO Merge
    CustomerBasicInfoPostRequest sourceCustomerAADM =
        CustomerUtil.createBasicCustomer("Customer", "Individual", AADM_User);

    AADM_User.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(sourceCustomerAADM, ""));

    CustomerIdResponse sourceCustomerIdResponseAADM =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    // Merge Customer Post Request object
    MergeCustomerPostRequest mergeCustomerPostRequestAADM = new MergeCustomerPostRequest();

    // Set source customer Id and Target customer ID
    mergeCustomerPostRequestAADM.setSourceCustomerId(sourceCustomerIdResponseAADM.getCustomerId());
    mergeCustomerPostRequestAADM.setTargetCustomerId(targetCustomerIdResponseAADM.getCustomerId());

    // Make call to merge customer for AADM_User
    AADM_User.attemptsTo(
        mergeCustomerAPI.POSTMergeCustomerOnTheMergecustomerController(
            mergeCustomerPostRequestAADM, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    MergeCustomerResponse mergeCustomerResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", MergeCustomerResponse.class);

    assertThat(mergeCustomerResponse != null).isTrue();
    assertThat(mergeCustomerResponse.getClass().getDeclaredFields().length).isEqualTo(2);
    assertThat(mergeCustomerResponse.getClass().getDeclaredFields()[0].getName())
        .isEqualTo("errorMessage");
    assertThat(mergeCustomerResponse.getClass().getDeclaredFields()[1].getName())
        .isEqualTo("isSuccessful");
    assertThat(mergeCustomerResponse.getIsSuccessful()).isTrue();
  }
}
