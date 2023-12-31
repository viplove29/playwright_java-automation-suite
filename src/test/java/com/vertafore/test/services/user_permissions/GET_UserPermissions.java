package com.vertafore.test.services.user_permissions;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseUserPermissionsTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_UserPermissions extends TokenSuperClass {
  // This is a placeholder. Yes, please feel free to add/change whatever, including changing the
  // class name. Thanks!
  @Test
  public void getUserPermissionsBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseUserPermissionsTo userpermApi = new UseUserPermissionsTo();

    VADM_Admin.attemptsTo(userpermApi.GETUserPermissionsOnTheUserpermissionsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(userpermApi.GETUserPermissionsOnTheUserpermissionsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(userpermApi.GETUserPermissionsOnTheUserpermissionsController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
