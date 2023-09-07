package com.vertafore.test.services.vmb_messages_sms;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.IncomingVmbMessagePostRequest;
import com.vertafore.test.models.ems.IncomingVmbMessageResponse;
import com.vertafore.test.servicewrappers.UseSmsTo;
import java.util.ArrayList;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_SMSVMBMessageUpdate extends TokenSuperClass {

  @Test
  public void postSMSVMBMessageUpdateBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseSmsTo smsAPI = new UseSmsTo();

    String randomVertaforeNo = "14255550043";
    String randomContactNo = "18591111111";

    IncomingVmbMessagePostRequest incomingVmbMessagePostRequest =
        new IncomingVmbMessagePostRequest();

    incomingVmbMessagePostRequest.setContactNo(randomContactNo);
    incomingVmbMessagePostRequest.setVertaforeNo(randomVertaforeNo);
    incomingVmbMessagePostRequest.setStatus("Delivered");
    incomingVmbMessagePostRequest.setBody("Test Body");
    incomingVmbMessagePostRequest.setAttachments(new ArrayList<>());

    VADM_Admin.attemptsTo(
        (smsAPI.POSTSmsVmbMessageUpdateOnTheVmbmessagessmsController(
            incomingVmbMessagePostRequest, "")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        (smsAPI.POSTSmsVmbMessageUpdateOnTheVmbmessagessmsController(
            incomingVmbMessagePostRequest, "")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        (smsAPI.POSTSmsVmbMessageUpdateOnTheVmbmessagessmsController(
            incomingVmbMessagePostRequest, "")));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    IncomingVmbMessageResponse vmbMessage =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", IncomingVmbMessageResponse.class);

    assertThat(vmbMessage).isNotNull();
    assertThat(vmbMessage.getVertaforeNo()).isEqualTo(randomVertaforeNo);
    assertThat(vmbMessage.getContactNo()).isEqualTo(randomContactNo);
  }
}
