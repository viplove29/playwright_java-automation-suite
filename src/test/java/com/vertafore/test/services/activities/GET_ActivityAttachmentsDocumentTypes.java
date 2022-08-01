package com.vertafore.test.services.activities;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.DocTypeListResponse;
import com.vertafore.test.servicewrappers.UseActivityTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class GET_ActivityAttachmentsDocumentTypes extends TokenSuperClass {

  @Test
  public void getActivityAttachmentsDocumentTypesGetsAllDocumentTypes() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseActivityTo ActivityAPI = new UseActivityTo();

    VADM_Admin.attemptsTo(
        ActivityAPI.GETActivityAttachmentsDocumentTypesOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(ActivityAPI.GETActivityAttachmentsDocumentTypesOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        ActivityAPI.GETActivityAttachmentsDocumentTypesOnTheActivitiesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DocTypeListResponse docTypesResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DocTypeListResponse.class);

    assertThat(docTypesResponse).isNotNull();
    assertThat(docTypesResponse.getClass().getDeclaredFields().length).isEqualTo(1);
    assertThat(docTypesResponse.getDocTypes()).isNotNull();
    assertThat(docTypesResponse.getDocTypes().get(0).getClass().getDeclaredFields().length)
        .isEqualTo(3);
  }
}
