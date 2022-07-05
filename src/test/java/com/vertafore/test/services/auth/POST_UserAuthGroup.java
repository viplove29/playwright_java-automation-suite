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
public class POST_UserAuthGroup extends TokenSuperClass {

  /*only the AADM user has access to insert auth groups to an employee
  thus all validation and setup will be done by this user, the other contexts tests
  are to ensure they do NOT have access */
  @Test
  public void PutAuthGroupUserUpdatesAuthGroupUser() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    AuthGroupResponse authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(AADM_User);
    String randomAGrpId = authGroupToInsert.getaGrpId();
    String randomEmpCode;

    /* on the off chance the employee already has access to the auth group,
    find another employee that does not */
    do {
      randomEmpCode = EmployeeUtil.getRandomEmployee(AADM_User).getEmpCode();
    } while (EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode));

    // ORANG TEST
    assertThat(EmployeeUtil.PostAuthGroupUser(ORAN_App, authGroupToInsert, randomEmpCode))
        .isEqualTo(403);
    assertThat(EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode))
        .isFalse();

    // VADM TEST
    assertThat(EmployeeUtil.PostAuthGroupUser(VADM_Admin, authGroupToInsert, randomEmpCode))
        .isEqualTo(403);
    assertThat(EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode))
        .isFalse();

    // AADM TEST
    assertThat(EmployeeUtil.PostAuthGroupUser(AADM_User, authGroupToInsert, randomEmpCode))
        .isEqualTo(200);
    assertThat(EmployeeUtil.doesEmployeeHaveAuthGroupAccess(AADM_User, randomAGrpId, randomEmpCode))
        .isTrue();

    // clean up data
    EmployeeUtil.DeleteUserAuthGroup(AADM_User, randomAGrpId, randomEmpCode);
  }
}
