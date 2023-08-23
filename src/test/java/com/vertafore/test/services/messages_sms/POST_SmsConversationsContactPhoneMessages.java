package com.vertafore.test.services.messages_sms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.MessagePostRequest;
import com.vertafore.test.servicewrappers.UseSmsTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_SmsConversationsContactPhoneMessages extends TokenSuperClass {

  @Test
  public void postSmsConversationsContactPhoneMessagesBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo smsMessagesAPI = new UseSmsTo();

    String contactPhone = "14256598226";
    MessagePostRequest messagePostRequest = new MessagePostRequest();
    messagePostRequest.setMessage("Test message");

    AADM_User.attemptsTo(
        smsMessagesAPI.POSTSmsConversationsContactPhoneMessagesOnTheMessagessmsController(
            contactPhone, messagePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        smsMessagesAPI.POSTSmsConversationsContactPhoneMessagesOnTheMessagessmsController(
            contactPhone, messagePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        smsMessagesAPI.POSTSmsConversationsContactPhoneMessagesOnTheMessagessmsController(
            contactPhone, messagePostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
