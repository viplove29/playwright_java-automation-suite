package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AuthGroupResponse;
import com.vertafore.test.util.AuthGroupUtility;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_UserAuthGroupDelete extends TokenSuperClass {

  public static String randomAGrpId;
  public static String serviceEmployeeEmpCode;

  @Test
  public void PostDeleteUserAuthGroupDeletesUserAuthGroup() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    Actor ORAN_App = theActorCalled("ORAN_App");

    // Need to put at least one auth group to delete in case application is empty
    // we will always do this with Orange partner (ORAN_App) to guarantee AuthGroup is added
    stageAuthGroupData(AADM_User);

    // ORANG TEST
    assertThat(AuthGroupUtility.DeleteUserAuthGroup(ORAN_App, randomAGrpId, serviceEmployeeEmpCode))
        .isEqualTo(200);
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(ORAN_App, randomAGrpId))
        .isEqualTo(false); // auth group is deleted, none in the list

    stageAuthGroupData(ORAN_App);

    // VADM TEST
    assertThat(
            AuthGroupUtility.DeleteUserAuthGroup(VADM_Admin, randomAGrpId, serviceEmployeeEmpCode))
        .isEqualTo(403); // AADM test
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(ORAN_App, randomAGrpId))
        .isEqualTo(true); // auth group is not deleted, still in the list

    // AADM TEST
    assertThat(
            AuthGroupUtility.DeleteUserAuthGroup(AADM_User, randomAGrpId, serviceEmployeeEmpCode))
        .isEqualTo(200); // delete the same auth group VADM wasn't authorized to
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(ORAN_App, randomAGrpId))
        .isEqualTo(false); // auth group is deleted, none in the list
  }

  public void stageAuthGroupData(Actor actor) {
    AuthGroupResponse authGroupToInsert;

    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(actor);
    randomAGrpId = authGroupToInsert.getaGrpId();
    serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(actor);
    AuthGroupUtility.PutAuthGroupUser(actor, authGroupToInsert, serviceEmployeeEmpCode);
  }
}
