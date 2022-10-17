package com.vertafore.test.services.customers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.util.CustomerUtil;
import com.vertafore.test.util.ProfileQuestionsUtil;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.junit.Test;

public class PUT_CustomerProfileAnswers extends TokenSuperClass {

  @Test
  public void putCustomerProfileAnswerSavesAnswer() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseCustomerTo customerApi = new UseCustomerTo();
    // insert new profile question so we can post answer to the question for a random customer
    // setup data to post
    /*Add secondOfDay as random parameter to the question so repeatedly running the test would not fail.
    secondOfDay could go up to 86400 which is 5 characters.So that leaves question to be 15 characters since
    max allowed characters for question is 20.
    */
    int secondOfDay = DateTime.now().getSecondOfDay();
    String question = "Profile Que " + secondOfDay;
    ProfileQuestionsUtil.postProfileQuestion(AADM_User, question, "Alpha", 20);

    /* It would have been ideal if the POSTAgencyProfileQuestion API returns the question id but it doesn't.
    So get all profiled questions, iterate all questions to match the question that's sent just now and
    get the question id. */
    List<AgencyProfileQuestionResponse> agencyProfileQuestionResponses =
        ProfileQuestionsUtil.getAgencyProfileQuestions(AADM_User, true);

    String questionId = "";
    // find the question id for the question posted
    for (AgencyProfileQuestionResponse profileQuestionResponse : agencyProfileQuestionResponses) {
      if (profileQuestionResponse.getQuestion().equals(question)) {
        questionId = profileQuestionResponse.getQuestionId();
        break;
      }
    }

    // select a random customer and post the answer for the question.
    String randomCustomerId =
        CustomerUtil.selectRandomCustomer(AADM_User, "customer").getCustomerId();

    // set the Alpha answer
    String answer = "I love insurance";
    CustomerProfileAnswerPutRequest customerProfileAnswerPutRequest =
        new CustomerProfileAnswerPutRequest();
    customerProfileAnswerPutRequest.setCustomerId(randomCustomerId);
    customerProfileAnswerPutRequest.setQuestionId(questionId);
    customerProfileAnswerPutRequest.setAnswer(answer);

    VADM_Admin.attemptsTo(
        customerApi.PUTCustomerProfileAnswerOnTheCustomersController(
            customerProfileAnswerPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        customerApi.PUTCustomerProfileAnswerOnTheCustomersController(
            customerProfileAnswerPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        customerApi.PUTCustomerProfileAnswerOnTheCustomersController(
            customerProfileAnswerPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    CustomerProfileAnswerIdResponse customerProfileAnswerIdResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", CustomerProfileAnswerIdResponse.class);

    assertThat(customerProfileAnswerIdResponse).isNotNull();
    assertThat(customerProfileAnswerIdResponse.getCustomerId()).isEqualTo(randomCustomerId);
    assertThat(customerProfileAnswerIdResponse.getQuestionId()).isEqualTo(questionId);

    // check if set profile answer is saved successfully
    List<CustomerProfileAnswerResponse> profileAnswerResponses =
        ProfileQuestionsUtil.getCustomerProfileAnswers(AADM_User, randomCustomerId);

    assertThat(profileAnswerResponses).isNotNull();
    assertThat(profileAnswerResponses.isEmpty()).isFalse();
    for (CustomerProfileAnswerResponse profileAnswer : profileAnswerResponses) {
      if (profileAnswer.getQuestionId() == questionId) {
        assertThat(profileAnswer.getAnswer()).isEqualTo(answer);
      }
    }

    // test for answer update
    answer = "I love insurance too";
    customerProfileAnswerIdResponse =
        ProfileQuestionsUtil.putCustomerProfileAnswer(
            AADM_User, randomCustomerId, questionId, answer);

    assertThat(customerProfileAnswerIdResponse).isNotNull();
    assertThat(customerProfileAnswerIdResponse.getCustomerId()).isEqualTo(randomCustomerId);
    assertThat(customerProfileAnswerIdResponse.getQuestionId()).isEqualTo(questionId);

    // check if set profile answer is saved successfully
    profileAnswerResponses =
        ProfileQuestionsUtil.getCustomerProfileAnswers(AADM_User, randomCustomerId);

    assertThat(profileAnswerResponses).isNotNull();
    assertThat(profileAnswerResponses.isEmpty()).isFalse();
    for (CustomerProfileAnswerResponse profileAnswer : profileAnswerResponses) {
      if (profileAnswer.getQuestionId() == questionId) {
        assertThat(profileAnswer.getAnswer()).isEqualTo(answer);
      }
    }

    // check of negative case. Update an answer longer than the accepted length
    answer =
        "I love insurance  I love insurance  I love insurance  I love insurance  I love insurance";
    customerProfileAnswerPutRequest = new CustomerProfileAnswerPutRequest();
    customerProfileAnswerPutRequest.setCustomerId(randomCustomerId);
    customerProfileAnswerPutRequest.setQuestionId(questionId);
    customerProfileAnswerPutRequest.setAnswer(answer);

    AADM_User.attemptsTo(
        customerApi.PUTCustomerProfileAnswerOnTheCustomersController(
            customerProfileAnswerPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
  }
}
