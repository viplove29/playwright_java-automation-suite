package com.vertafore.test.services.agency;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AgencyProfileQuestionPostRequest;
import com.vertafore.test.models.ems.AgencyProfileQuestionResponse;
import com.vertafore.test.models.ems.PostGenericResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import com.vertafore.test.util.AgencyUtil;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_AgencyProfileQuestion extends TokenSuperClass {

  @Test
  public void postAgencyProfileQuestionSavesProfileQuestion() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAgencyTo agencyApi = new UseAgencyTo();

    // setup data to post
    AgencyProfileQuestionPostRequest agencyPostRequest = new AgencyProfileQuestionPostRequest();
    /*Add secondOfDay as random parameter to the question so repeatedly running the test would not fail.
    secondOfDay could go up to 86400 which is 5 characters.So that leaves question to be 15 characters since
    max allowed characters for question is 20.
    */
    int secondOfDay = DateTime.now().getSecondOfDay();
    String question = "Profile Que " + secondOfDay;
    agencyPostRequest.setQuestion(question);
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // change question  again since the previous call would have inserted the profile question with
    // the current question
    agencyPostRequest.setQuestion(question + "!");
    VADM_Admin.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    agencyPostRequest.setQuestion(question + "@");
    ORAN_App.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // validate response
    PostGenericResponse postProfileQuestionResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PostGenericResponse.class);
    assertThat(postProfileQuestionResponse).isNotNull();
    assertThat(postProfileQuestionResponse.getNumberOfRecordsCreated()).isEqualTo(1);

    // Get the agency profile questions and check if it contains the question that's just posted now
    AADM_User.attemptsTo(agencyApi.GETAgencyProfileQuestionsOnTheAgencyController(false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AgencyProfileQuestionResponse> profileQuestions =
        AgencyUtil.getAllAgencyProfileQuestion(AADM_User, false);
    assertThat(profileQuestions).isNotNull();

    // check if the posted question exists in profile questions
    profileQuestions =
        profileQuestions
            .stream()
            .filter(profileQuestion -> profileQuestion.getQuestion().equals(question))
            .collect(Collectors.toList());
    assertThat(profileQuestions.size()).isEqualTo(1);

    // verify inserting the same question again returns error
    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
  }

  @Test
  public void postAgencyProfileQuestionWithMissingDataReturnsError() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseAgencyTo agencyApi = new UseAgencyTo();

    // setup data to post
    // Check sending question more than 20 characters returns error
    AgencyProfileQuestionPostRequest agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance - Profile Question of the day");
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending without question
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending without answer format
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending invalid answer format
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance");
    agencyPostRequest.answerFormat("14%forMe");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending without type of business
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance");
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending invalid type of business
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance");
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("14%forMe");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending without answer length
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance");
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);

    // Check sending answer length as 0
    agencyPostRequest = new AgencyProfileQuestionPostRequest();
    agencyPostRequest.setQuestion("Love Insurance");
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(0);
    agencyPostRequest.setActive(true);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
  }

  @Test
  public void postAgencyProfileQuestionWithoutActiveFlagSavesProfileQuestionAsActive() {
    Actor AADM_User = theActorCalled("AADM_User");

    UseAgencyTo agencyApi = new UseAgencyTo();

    // setup data to post without active flag set.
    AgencyProfileQuestionPostRequest agencyPostRequest = new AgencyProfileQuestionPostRequest();
    /*Add secondOfDay as random parameter to the question so repeatedly running the test would not fail.
    secondOfDay could go up to 86400 which is 5 characters.So that leaves question to be 15 characters since
    max allowed characters for question is 20.
    */
    int secondOfDay = DateTime.now().getSecondOfDay();
    String question = "Profile Que1 " + secondOfDay;
    agencyPostRequest.setQuestion(question);
    agencyPostRequest.answerFormat("Alpha");
    agencyPostRequest.answerLength(20);
    agencyPostRequest.typeOfBusiness("All");

    AADM_User.attemptsTo(
        agencyApi.POSTAgencyProfileQuestionOnTheAgencyController(agencyPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Get the agency profile questions and check if it contains the question that's just posted now
    AADM_User.attemptsTo(agencyApi.GETAgencyProfileQuestionsOnTheAgencyController(false, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AgencyProfileQuestionResponse> profileQuestions =
        AgencyUtil.getAllAgencyProfileQuestion(AADM_User, false);
    assertThat(profileQuestions).isNotNull();

    // check if the posted question exists in profile questions
    profileQuestions =
        profileQuestions
            .stream()
            .filter(profileQuestion -> profileQuestion.getQuestion().equals(question))
            .collect(Collectors.toList());
    assertThat(profileQuestions.size()).isEqualTo(1);
    // check active flag is set to true for the question
    assertThat(profileQuestions.get(0).getActive()).isTrue();
  }
}
