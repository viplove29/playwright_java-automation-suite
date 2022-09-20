package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseActivityTo;
import com.vertafore.test.util.ActivityUtil;
import com.vertafore.test.util.CustomerUtil;
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
}
