package com.vertafore.test.services.messages_sms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseSmsTo;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_SmsConversationsContactPhoneMessagesMessageId extends TokenSuperClass {

  @Test
  public void getSmsConversationsContactPhoneMessagesMessageIdBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo smsMessagesAPI = new UseSmsTo();

    String contactPhone = "14256598226";
    Long messageId = Long.valueOf(7480408);

    AADM_User.attemptsTo(
        smsMessagesAPI.GETSmsConversationsContactPhoneMessagesMessageIdOnTheMessagessmsController(
            messageId, contactPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        smsMessagesAPI.GETSmsConversationsContactPhoneMessagesMessageIdOnTheMessagessmsController(
            messageId, contactPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        smsMessagesAPI.GETSmsConversationsContactPhoneMessagesMessageIdOnTheMessagessmsController(
            messageId, contactPhone, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
