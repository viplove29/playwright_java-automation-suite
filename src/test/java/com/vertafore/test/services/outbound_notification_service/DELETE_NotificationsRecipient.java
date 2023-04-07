package com.vertafore.test.services.outbound_notification_service;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ActivityActionNotificationResponse;
import com.vertafore.test.models.ems.DeleteGenericResponse;
import com.vertafore.test.models.ems.NotificationClientFullInfoResponse;
import com.vertafore.test.models.ems.NotificationRecipientResponse;
import com.vertafore.test.servicewrappers.UseNotificationsTo;
import com.vertafore.test.util.NotificationUtil;
import com.vertafore.test.util.Util;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class DELETE_NotificationsRecipient extends TokenSuperClass {

  String clientId;
  Actor AADM_User = theActorCalled("AADM_User");

  @Test
  public void deleteNotificationsRecipientDeletesRecipient() {

    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post notification client and get client details.
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);

    clientId = clientResponse.getClientId();
    String recipientId1ToDelete = clientResponse.getRecipients().get(0).getRecipientId();
    String recipientId2ToDelete = clientResponse.getRecipients().get(1).getRecipientId();

    // call delete recipient
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientId1ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteGenericResponse deleteRecipientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericResponse.class);
    assertThat(deleteRecipientResponse).isNotNull();
    assertThat(deleteRecipientResponse.getNumberOfRecordsDeleted()).isEqualTo(1);

    ORAN_App.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientId2ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    deleteRecipientResponse =
        LastResponse.received()
            .answeredBy(ORAN_App)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericResponse.class);
    assertThat(deleteRecipientResponse).isNotNull();
    assertThat(deleteRecipientResponse.getNumberOfRecordsDeleted()).isEqualTo(1);

    VADM_Admin.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientId1ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // Get the client again and check that that deleted recipient is not there for the client
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsClientOnTheOutboundnotificationserviceController(
            clientResponse.getClientId(), ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    NotificationClientFullInfoResponse updatedClientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", NotificationClientFullInfoResponse.class);

    for (NotificationRecipientResponse recipientResponse : updatedClientResponse.getRecipients()) {
      if (recipientResponse.getClientId().equals(recipientId1ToDelete)
          || recipientResponse.getClientId().equals(recipientId2ToDelete)) {
        // Deleted recipient is returned after the DELETE call was successful. This is not expected.
        // Fail the test.
        Assert.fail(
            "Recipient with id "
                + recipientId1ToDelete
                + " for the client (id: "
                + clientResponse.getClientId()
                + ") is still there even after the DeleteNotificationsRecipient call.");
      }
    }
  }

  @After
  public void tearDown() {
    if (clientId != null) {
      // delete the client.
      NotificationUtil.deleteNotificationsClient(clientId, AADM_User);
      clientId = null;
    }
  }

  @Test
  public void deleteNotificationsRecipientDeletesNotificationRecipientActivityActions() {

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Post version 3.0 notification client and get client details. Only version 3.0 supports
    // notification actions
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postNotificationClientAndGetClientDetails(AADM_User, "3.0");
    clientId = clientResponse.getClientId();
    String recipientIdToDelete = clientResponse.getRecipients().get(0).getRecipientId();

    // call delete recipient
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientIdToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    DeleteGenericResponse deleteRecipientResponse =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", DeleteGenericResponse.class);
    assertThat(deleteRecipientResponse).isNotNull();
    assertThat(deleteRecipientResponse.getNumberOfRecordsDeleted()).isEqualTo(1);

    // Get the activity actions for the recipient. Check activity actions are also deleted since the
    // recipient is deleted.
    AADM_User.attemptsTo(
        notificationsApi.GETNotificationsActivityActionsOnTheOutboundnotificationserviceController(
            recipientIdToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<ActivityActionNotificationResponse> activityActions =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", ActivityActionNotificationResponse.class);
    assertThat(activityActions.size()).isEqualTo(0);
  }

  @Test
  public void deleteNotificationsRecipientErrorTest() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseNotificationsTo notificationsApi = new UseNotificationsTo();

    // Test delete notifications recipient call without passing recipient Id
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            null, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "There were missing required fields for the Notification Recipient Delete Request: { 'RecipientId' }",
        AADM_User);

    // Test delete notifications recipient call with passing not valid (GUID) recipient Id
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            "4295d91a44d5", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString(
        "The value '4295d91a44d5' is not valid for RecipientId.; The RecipientId field is required.",
        AADM_User);

    // Test delete notifications recipient call with passing empty recipient Id
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            "", ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(400);
    Util.validateErrorResponseContainsString("The RecipientId field is required.", AADM_User);

    // Test delete notifications recipient call by passing non existing recipient Id
    // Post notification client and get client details.
    NotificationClientFullInfoResponse clientResponse =
        NotificationUtil.postRandomNotificationClientAndGetClientDetails(AADM_User);

    String recipientId1ToDelete = clientResponse.getRecipients().get(0).getRecipientId();
    clientId = clientResponse.getClientId();

    // call delete recipient
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientId1ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Call delete again with same recipient again.
    AADM_User.attemptsTo(
        notificationsApi.DELETENotificationsRecipientOnTheOutboundnotificationserviceController(
            recipientId1ToDelete, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(404);

    Util.validateErrorResponseContainsString(
        "A notification recipient with '" + recipientId1ToDelete + "' could not be found.",
        AADM_User);
  }
}
