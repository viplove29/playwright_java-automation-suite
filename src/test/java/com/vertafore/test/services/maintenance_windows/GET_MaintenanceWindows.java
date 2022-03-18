package com.vertafore.test.services.maintenance_windows;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.MaintenanceWindowResponse;
import com.vertafore.test.servicewrappers.UseMaintenanceWindowsTo;
import java.util.List;
import java.util.Random;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_MaintenanceWindows extends TokenSuperClass {

  @Test
  public void MaintenanceWindowsReturnsAllMaintenanceWindows() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseMaintenanceWindowsTo maintwinAPI = new UseMaintenanceWindowsTo();

    // basic status code assertions
    AADM_User.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsOnTheMaintenancewindowsController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsOnTheMaintenancewindowsController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsOnTheMaintenancewindowsController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<MaintenanceWindowResponse> mwResponse =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getList("", MaintenanceWindowResponse.class);

    // basic field name assertions
    assertThat(mwResponse.get(0).getClass().getDeclaredFields().length).isEqualTo(8);
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[0].getName()).isEqualTo("id");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[1].getName()).isEqualTo("status");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[2].getName())
        .isEqualTo("startTime");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[3].getName()).isEqualTo("endTime");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[4].getName())
        .isEqualTo("prodVersion");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[5].getName()).isEqualTo("comments");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[6].getName()).isEqualTo("enabled");
    assertThat(mwResponse.get(0).getClass().getDeclaredFields()[7].getName()).isEqualTo("agencies");

    // pick random index, set variables for specific maintenance window check below
    int mwIndex = new Random().nextInt(mwResponse.size());
    String id = mwResponse.get(mwIndex).getId();
    String status = mwResponse.get(mwIndex).getStatus();
    DateTime startTime = mwResponse.get(mwIndex).getStartTime();
    DateTime endTime = mwResponse.get(mwIndex).getEndTime();
    String prodVersion = mwResponse.get(mwIndex).getProdVersion();
    String comments = mwResponse.get(mwIndex).getComments();
    Boolean enabled = mwResponse.get(mwIndex).getEnabled();
    List agencies = mwResponse.get(mwIndex).getAgencies();

    // get with id from above
    VADM_Admin.attemptsTo(
        maintwinAPI.GETMaintenanceWindowsOnTheMaintenancewindowsController(id, "string"));

    MaintenanceWindowResponse singleMWResponse =
        LastResponse.received()
            .answeredBy(VADM_Admin)
            .getBody()
            .jsonPath()
            .getList("", MaintenanceWindowResponse.class)
            .get(0);

    // verify fields in response
    assertThat(singleMWResponse).isNotNull();
    assertThat(singleMWResponse.getId()).isEqualTo(id);
    assertThat(singleMWResponse.getStatus()).isEqualTo(status);
    assertThat(singleMWResponse.getStartTime()).isEqualTo(startTime);
    assertThat(singleMWResponse.getEndTime()).isEqualTo(endTime);
    assertThat(singleMWResponse.getProdVersion()).isEqualTo(prodVersion);
    assertThat(singleMWResponse.getComments()).isEqualTo(comments);
    assertThat(singleMWResponse.getEnabled()).isEqualTo(enabled);
    assertThat(singleMWResponse.getAgencies()).isEqualTo(agencies);
  }
}
