package com.vertafore.test.services.conversations_sms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.ConversationStatusPostRequest;
import com.vertafore.test.servicewrappers.UseSmsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_SmsConversationsContactPhoneStatus extends TokenSuperClass {

  @Test
  public void postSmsConversationsContactPhoneStatusBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo conversationsAPI = new UseSmsTo();

    String contactNo = "14255551515";

    ConversationStatusPostRequest conversationStatusPostRequest =
        new ConversationStatusPostRequest();
    conversationStatusPostRequest.setStatus("C");

    ORAN_App.attemptsTo(
        conversationsAPI.POSTSmsConversationsContactPhoneStatusOnTheConversationssmsController(
            contactNo, conversationStatusPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        conversationsAPI.POSTSmsConversationsContactPhoneStatusOnTheConversationssmsController(
            contactNo, conversationStatusPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        conversationsAPI.POSTSmsConversationsContactPhoneStatusOnTheConversationssmsController(
            contactNo, conversationStatusPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
