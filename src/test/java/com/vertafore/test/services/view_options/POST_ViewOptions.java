package com.vertafore.test.services.view_options;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.PostGenericResponse;
import com.vertafore.test.models.ems.ViewOptionsPostRequest;
import com.vertafore.test.servicewrappers.UseViewOptionsTo;
import com.vertafore.test.util.ViewOptionsUtil;
import java.util.Map;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;

public class POST_ViewOptions extends TokenSuperClass {

  @Test
  public void viewOptionsUpdatesBankTransactionsViewOptions() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseViewOptionsTo viewOptionsApi = new UseViewOptionsTo();

    /*This test will GET the 'System' View Options and randomize the values before POSTing the randomized View Options for the 'User' ownerType.
    The options are randomized again before each POST from ORAN_App, VADM_Admin, and AADM_User.
    After the 3 POSTs, GET the latest 'User' View Options and verify they match what was POSTed by AADM_User.
    At the end, POST the original 'System' View Options for the 'User' ownerType to make the options match for environment cleanup.*/

    Map<String, Object> options =
        ViewOptionsUtil.getCurrentViewOptionsAsMap(AADM_User, "System", "BankTransactionsView");

    // a deep copy is needed to reset the user view options at the end since the first options will
    // be modified, and it is simpler to use the endpoint again to avoid the possibility of needing
    // to copy nulls
    Map<String, Object> originalOptions =
        ViewOptionsUtil.getCurrentViewOptionsAsMap(AADM_User, "System", "BankTransactionsView");

    assertThat(options.keySet()).isNotEmpty();
    ViewOptionsUtil.randomizeViewOptionsValues(options);

    ViewOptionsPostRequest viewOptionsPostRequest = new ViewOptionsPostRequest();
    viewOptionsPostRequest.setOwnerType("User");
    viewOptionsPostRequest.setSettingType("BankTransactionsView");
    viewOptionsPostRequest.setOptions(options);

    ORAN_App.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ViewOptionsUtil.randomizeViewOptionsValues(options);
    viewOptionsPostRequest.setOptions(options);

    VADM_Admin.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ViewOptionsUtil.randomizeViewOptionsValues(options);
    viewOptionsPostRequest.setOptions(options);

    AADM_User.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PostGenericResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PostGenericResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getNumberOfRecordsCreated()).isNotNull();
    assertThat(response.getNumberOfRecordsCreated()).isEqualTo(1);

    // verify options were updated to the correct values
    Map<String, Object> updatedOptions =
        ViewOptionsUtil.getCurrentViewOptionsAsMap(AADM_User, "User", "BankTransactionsView");
    assertThat(updatedOptions).isEqualTo(options);

    // reset user view options to match original view options
    viewOptionsPostRequest.setOptions(originalOptions);
    AADM_User.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @Test
  public void viewOptionsUpdatesChecksToApproveViewOptions() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseViewOptionsTo viewOptionsApi = new UseViewOptionsTo();

    /*This test will GET the 'System' View Options and randomize the values before POSTing the randomized View Options for the 'User' ownerType.
    The options are randomized again before each POST from ORAN_App, VADM_Admin, and AADM_User.
    After the 3 POSTs, GET the latest 'User' View Options and verify they match what was POSTed by AADM_User.
    At the end, POST the original 'System' View Options for the 'User' ownerType to make the options match for environment cleanup.*/

    Map<String, Object> options =
        ViewOptionsUtil.getCurrentViewOptionsAsMap(AADM_User, "System", "ApproveChecksView");

    // a deep copy is needed to reset the user view options at the end since the first options will
    // be modified, and it is simpler to use the endpoint again to avoid the possibility of needing
    // to copy nulls
    Map<String, Object> originalOptions =
        ViewOptionsUtil.getCurrentViewOptionsAsMap(AADM_User, "System", "ApproveChecksView");

    assertThat(options.keySet()).isNotEmpty();
    ViewOptionsUtil.randomizeViewOptionsValues(options);

    ViewOptionsPostRequest viewOptionsPostRequest = new ViewOptionsPostRequest();
    viewOptionsPostRequest.setOwnerType("User");
    viewOptionsPostRequest.setSettingType("ApproveChecksView");
    viewOptionsPostRequest.setOptions(options);

    ORAN_App.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ViewOptionsUtil.randomizeViewOptionsValues(options);
    viewOptionsPostRequest.setOptions(options);

    VADM_Admin.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ViewOptionsUtil.randomizeViewOptionsValues(options);
    viewOptionsPostRequest.setOptions(options);

    AADM_User.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PostGenericResponse response =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getObject("", PostGenericResponse.class);

    assertThat(response).isNotNull();
    assertThat(response.getNumberOfRecordsCreated()).isNotNull();
    assertThat(response.getNumberOfRecordsCreated()).isEqualTo(1);

    // verify options were updated to the correct values
    Map<String, Object> updatedOptions =
        ViewOptionsUtil.getCurrentViewOptionsAsMap(AADM_User, "User", "ApproveChecksView");
    assertThat(updatedOptions).isEqualTo(options);

    // reset user view options to match original view options
    viewOptionsPostRequest.setOptions(originalOptions);
    AADM_User.attemptsTo(
        viewOptionsApi.POSTViewOptionsOnTheViewoptionsController(viewOptionsPostRequest, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
