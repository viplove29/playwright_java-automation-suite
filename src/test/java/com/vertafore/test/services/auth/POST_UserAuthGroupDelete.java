package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.AuthGroupResponse;
import com.vertafore.test.util.AuthGroupUtility;
import com.vertafore.test.util.EmployeeUtil;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class POST_UserAuthGroupDelete extends TokenSuperClass {

  public static String randomAGrpId;
  public static String randomEmpCode;

  @Test
  public void PostDeleteUserAuthGroupDeletesUserAuthGroup() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor VADM_Admin = theActorCalled("VADM_Admin");
    Actor ORAN_App = theActorCalled("ORAN_App");

    // Need to put at least one auth group to delete in case application is empty
    // we will always do this with AADM to guarantee AuthGroup is added
    stageAuthGroupData(AADM_User);

    // ORANG TEST
    assertThat(EmployeeUtil.DeleteUserAuthGroup(ORAN_App, randomAGrpId, randomEmpCode))
        .isEqualTo(403);
    assertThat(EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode))
        .isTrue(); // not deleted, still in the list

    // VADM TEST
    assertThat(EmployeeUtil.DeleteUserAuthGroup(VADM_Admin, randomAGrpId, randomEmpCode))
        .isEqualTo(403);
    assertThat(EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode))
        .isTrue();

    // AADM TEST
    assertThat(EmployeeUtil.DeleteUserAuthGroup(AADM_User, randomAGrpId, randomEmpCode))
        .isEqualTo(200);
    assertThat(EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode))
        .isFalse(); // auth group is deleted, none in the list
  }

  public void stageAuthGroupData(Actor actor) {
    AuthGroupResponse authGroupToInsert;

    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(actor);
    randomAGrpId = authGroupToInsert.getaGrpId();
    randomEmpCode = EmployeeUtil.getRandomEmployee(actor).getEmpCode();

    EmployeeUtil.PutAuthGroupUser(actor, authGroupToInsert, randomEmpCode);
  }
}
