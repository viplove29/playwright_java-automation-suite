package com.vertafore.test.services.data_schedule;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.DataScheduleTemplatePostRequest;
import com.vertafore.test.models.ems.DataScheduleTemplateResponse;
import com.vertafore.test.models.ems.PagingRequestDataScheduleTemplatePostRequest;
import com.vertafore.test.models.ems.PagingResponseDataScheduleTemplateResponse;
import com.vertafore.test.servicewrappers.UseDataScheduleTo;
import com.vertafore.test.util.Util;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@WithTag("dataSchedule")
public class POST_DataScheduleTemplateListSearch extends TokenSuperClass {

  @Test
  public void postDataScheduleTemplateListSearch() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseDataScheduleTo dataScheduleAPI = new UseDataScheduleTo();

    DataScheduleTemplatePostRequest dataScheduleTemplatePostRequest =
        new DataScheduleTemplatePostRequest();

    PagingRequestDataScheduleTemplatePostRequest pagingRequestDataScheduleTemplatePostRequest =
        new PagingRequestDataScheduleTemplatePostRequest();

    pagingRequestDataScheduleTemplatePostRequest.model(dataScheduleTemplatePostRequest);
    pagingRequestDataScheduleTemplatePostRequest.setSkip(0);
    pagingRequestDataScheduleTemplatePostRequest.setTop(1000);
    pagingRequestDataScheduleTemplatePostRequest.setTotalRecords(1000);

    VADM_Admin.attemptsTo(
        dataScheduleAPI.POSTDataScheduleTemplateListSearchOnTheDatascheduleController(
            pagingRequestDataScheduleTemplatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        dataScheduleAPI.POSTDataScheduleTemplateListSearchOnTheDatascheduleController(
            pagingRequestDataScheduleTemplatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        dataScheduleAPI.POSTDataScheduleTemplateListSearchOnTheDatascheduleController(
            pagingRequestDataScheduleTemplatePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseDataScheduleTemplateResponse pagingResponseDataScheduleTemplateResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseDataScheduleTemplateResponse.class);

    assertThat(pagingResponseDataScheduleTemplateResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);

    DataScheduleTemplateResponse dataScheduleTemplateResponse =
        pagingResponseDataScheduleTemplateResponse.getResponse().get(0);

    assertThat(dataScheduleTemplateResponse.getClass().getDeclaredFields().length).isEqualTo(4);
    assertThat(Util.isValidGUID(dataScheduleTemplateResponse.getDataScheduleTemplateId())).isTrue();
  }
}
