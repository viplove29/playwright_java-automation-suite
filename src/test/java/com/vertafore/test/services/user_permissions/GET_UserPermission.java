package com.vertafore.test.services.user_permissions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseUserPermissionTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_UserPermission extends TokenSuperClass {
  // This is a placeholder. Yes, please feel free to add/change whatever, including changing the
  // class name. Thanks!
  @Test
  public void getUserPermissionBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseUserPermissionTo userpermApi = new UseUserPermissionTo();

    String aItmId = "5bd5fe6f-afcc-436b-8136-24fd7135ce44";

    VADM_Admin.attemptsTo(userpermApi.GETUserPermissionOnTheUserpermissionsController(aItmId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(userpermApi.GETUserPermissionOnTheUserpermissionsController(aItmId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(userpermApi.GETUserPermissionOnTheUserpermissionsController(aItmId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
