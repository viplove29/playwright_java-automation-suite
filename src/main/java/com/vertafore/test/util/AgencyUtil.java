package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.AgencyProfileQuestionResponse;
import com.vertafore.test.servicewrappers.UseAgencyTo;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class AgencyUtil {

  public static UseAgencyTo agencyAPI = new UseAgencyTo();

  public static List<AgencyProfileQuestionResponse> getAllAgencyProfileQuestion(
      Actor actor, boolean includeInactive) {
    actor.attemptsTo(agencyAPI.GETAgencyProfileQuestionsOnTheAgencyController(includeInactive, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AgencyProfileQuestionResponse> profileQuestions =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", AgencyProfileQuestionResponse.class);
    assertThat(profileQuestions).isNotNull();
    return profileQuestions;
  }
}
