package com.vertafore.test.services.data_schedule;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseDataScheduleTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
@WithTag("dataSchedule")
public class GET_DataScheduleTemplateDetail extends TokenSuperClass {

  @Test
  public void getDataScheduleTemplateDetail() {
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

    DataScheduleTemplateResponse dataScheduleTemplateResponse =
        pagingResponseDataScheduleTemplateResponse.getResponse().get(0);

    String dataScheduleTemplateID = dataScheduleTemplateResponse.getDataScheduleTemplateId();

    VADM_Admin.attemptsTo(
        dataScheduleAPI.GETDataScheduleTemplateDetailOnTheDatascheduleController(
            dataScheduleTemplateResponse.getDataScheduleTemplateId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        dataScheduleAPI.GETDataScheduleTemplateDetailOnTheDatascheduleController(
            dataScheduleTemplateResponse.getDataScheduleTemplateId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        dataScheduleAPI.GETDataScheduleTemplateDetailOnTheDatascheduleController(
            dataScheduleTemplateResponse.getDataScheduleTemplateId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DataScheduleTemplateDetailResponse dataScheduleTemplateDetailResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DataScheduleTemplateDetailResponse.class);

    assertThat(dataScheduleTemplateDetailResponse.getClass().getDeclaredFields().length)
        .isEqualTo(4);
    assertThat(dataScheduleTemplateDetailResponse.getDataScheduleTemplateID())
        .isEqualTo(dataScheduleTemplateID);
  }
}
