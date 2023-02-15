package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.EnvVariables;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ActivityCustomerSearch extends TokenSuperClass {

  @Test
  public void postActivityCustomerSearchRetrievesActivity() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    // Stage customer to append activity to
    CustomerIdResponse stagedCustomer = CustomerUtil.stageARandomCustomer(AADM_User);

    // Stage Activity to search
    UseActivityTo activityApi = new UseActivityTo();
    CustomerActivityPostRequest postRequest = new CustomerActivityPostRequest();
    postRequest.setCustomerId(stagedCustomer.getCustomerId());
    String comment = "Automation Test";
    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    postRequest.setComment(comment);
    postRequest.setAction(action);

    AADM_User.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Create request models for search
    CustomerActivitiesSearchPostRequest searchPostRequest =
        new CustomerActivitiesSearchPostRequest();
    searchPostRequest.setCustomerId(stagedCustomer.getCustomerId());
    PagingRequestCustomerActivitiesSearchPostRequest pagingRequest =
        new PagingRequestCustomerActivitiesSearchPostRequest();
    pagingRequest.setModel(searchPostRequest);

    // Send the requests
    ORAN_App.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate the response
    PagingResponseCustomerActivityResponse pagingResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerActivityResponse.class);

    assertThat(pagingResponse).isNotNull();
    assertThat(pagingResponse.getResponse().size()).isGreaterThan(0);

    CustomerActivityResponse customerActivity =
        pagingResponse
            .getResponse()
            .stream()
            .filter(activity -> activity.getComment().equals(comment))
            .collect(Collectors.toList())
            .get(0);

    assertThat(customerActivity).isNotNull();
    assertThat(customerActivity.getAction()).isEqualTo(action);
  }

  @Test
  public void postActivityCustomerSearchReadMaskTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor AADM_NAUser = theActorCalled("AADM_NAUser");
    Actor AADM_CBUUSER = theActorCalled("AADM_CBUUser");
    Actor AADM_PBUUSER = theActorCalled("AADM_PBUUser");
    Actor AADM_EXECUSER = theActorCalled("AADM_EXECUser");
    Actor AADM_PPUser = theActorCalled("AADM_PPUser");
    Actor AADM_SGUser = theActorCalled("AADM_SGUser");

    // use the staged customer ID
    String customerId = EnvVariables.READ_WRITE_MASK_CUSTOMER_ID;

    // Stage Activity to search
    UseActivityTo activityApi = new UseActivityTo();
    CustomerActivityPostRequest postRequest = new CustomerActivityPostRequest();
    postRequest.setCustomerId(customerId);
    String comment = "Automation Read Mask Test";
    String action = ActivityUtil.getRandomActivityAction(AADM_User);
    postRequest.setComment(comment);
    postRequest.setAction(action);

    AADM_User.attemptsTo(
        activityApi.POSTActivityCustomerOnTheActivitiesController(postRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Create request models for search
    CustomerActivitiesSearchPostRequest searchPostRequest =
        new CustomerActivitiesSearchPostRequest();
    searchPostRequest.setCustomerId(customerId);
    PagingRequestCustomerActivitiesSearchPostRequest pagingRequest =
        new PagingRequestCustomerActivitiesSearchPostRequest();
    pagingRequest.setModel(searchPostRequest);

    // No access user doesn't have access to customer or policy
    AADM_NAUser.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    // validate the response
    PagingResponseCustomerActivityResponse pagingResponse =
        LastResponse.received()
            .answeredBy(AADM_NAUser)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerActivityResponse.class);

    assertThat(pagingResponse).isNotNull();
    assertThat(pagingResponse.getResponse()).isEmpty();

    // Customer Business user has read and write access to the customer but no policy access
    AADM_CBUUSER.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Business Unit user has read access to customer and read and write access to policy
    AADM_PBUUSER.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Executive user has read and write access to customer but no policy access
    AADM_EXECUSER.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Policy Personnel user has read access to customer and read and write access to policy
    AADM_PPUser.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Service Group user has read only access to customer and no access to policy
    AADM_SGUser.attemptsTo(
        activityApi.POSTActivityCustomerSearchOnTheActivitiesController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
