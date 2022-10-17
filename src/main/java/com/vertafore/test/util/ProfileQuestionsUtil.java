package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class ProfileQuestionsUtil {

  public static UseAgencyTo agencyAPI = new UseAgencyTo();
  public static UseCustomerTo customerAPI = new UseCustomerTo();

  public static void postProfileQuestion(Actor actor, String question, String format, int length) {
    AgencyProfileQuestionPostRequest agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion(question);
    agencyPostRequest.answerFormat(format);
    agencyPostRequest.answerLength(length);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    actor.attemptsTo(
        agencyAPI.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  public static List<AgencyProfileQuestionResponse> getAgencyProfileQuestions(
      Actor actor, boolean includeInactive) {
    actor.attemptsTo(agencyAPI.GETAgencyProfileQuestionsOnTheAgencyController(includeInactive, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AgencyProfileQuestionResponse> agencyProfileQuestionResponses =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", AgencyProfileQuestionResponse.class);
    assertThat(agencyProfileQuestionResponses).isNotNull();
    assertThat(!agencyProfileQuestionResponses.isEmpty()).isTrue();
    return agencyProfileQuestionResponses;
  }

  public static List<CustomerProfileAnswerResponse> getCustomerProfileAnswers(
      Actor actor, String customerId) {
    actor.attemptsTo(customerAPI.GETCustomerProfileAnswersOnTheCustomersController(customerId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerProfileAnswerResponse> profileAnswerResponses =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", CustomerProfileAnswerResponse.class);
    assertThat(profileAnswerResponses).isNotNull();
    return profileAnswerResponses;
  }

  public static CustomerProfileAnswerIdResponse putCustomerProfileAnswer(
      Actor actor, String customerId, String questionId, String answer) {
    CustomerProfileAnswerPutRequest customerProfileAnswerPutRequest =
        new CustomerProfileAnswerPutRequest();
    customerProfileAnswerPutRequest.setCustomerId(customerId);
    customerProfileAnswerPutRequest.setQuestionId(questionId);
    customerProfileAnswerPutRequest.setAnswer(answer);

    actor.attemptsTo(
        customerAPI.PUTCustomerProfileAnswerOnTheCustomersController(
            customerProfileAnswerPutRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    CustomerProfileAnswerIdResponse customerProfileAnswerIdResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", CustomerProfileAnswerIdResponse.class);
    assertThat(customerProfileAnswerIdResponse).isNotNull();
    assertThat(customerProfileAnswerIdResponse.getCustomerId()).isEqualTo(customerId);
    assertThat(customerProfileAnswerIdResponse.getQuestionId()).isEqualTo(questionId);
    return customerProfileAnswerIdResponse;
  }
}
