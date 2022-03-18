// package com.vertafore.test.services.auth;
//
// import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
// import static org.assertj.core.api.Assertions.assertThat;
//
// import com.vertafore.test.actor.TokenSuperClass;
// import com.vertafore.test.models.ems.AuthGroupResponse;
// import com.vertafore.test.util.AuthGroupUtility;
// import net.serenitybdd.junit.runners.SerenityRunner;
// import net.serenitybdd.screenplay.Actor;
// import org.junit.Test;
// import org.junit.runner.RunWith;
//
// @RunWith(SerenityRunner.class)
// public class PUT_UserAuthGroup extends TokenSuperClass {
//
//  @Test
//  public void PutAuthGroupUserUpdatesAuthGroupUser() {
//    Actor AADM_User = theActorCalled("AADM_User");
//    Actor ORAN_App = theActorCalled("ORAN_App");
//    Actor VADM_Admin = theActorCalled("VADM_Admin");
//
//    AuthGroupResponse authGroupToInsert;
//    String randomAGrpId;
//    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(VADM_Admin);
//
//    // ORANG TEST
//    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(VADM_Admin);
//    randomAGrpId = authGroupToInsert.getaGrpId();
//
//    assertThat(AuthGroupUtility.PutAuthGroupUser(VADM_Admin, authGroupToInsert,
// serviceEmployeeEmpCode))
//        .isEqualTo(200);
//    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(VADM_Admin, randomAGrpId)).isTrue();
//
//    AuthGroupUtility.DeleteUserAuthGroup(
//        VADM_Admin, randomAGrpId, serviceEmployeeEmpCode); // clean up data
//
//    // VADM TEST
//    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(VADM_Admin);
//    randomAGrpId = authGroupToInsert.getaGrpId();
//    assertThat(AuthGroupUtility.PutAuthGroupUser(ORAN_App, authGroupToInsert,
// serviceEmployeeEmpCode))
//        .isEqualTo(403);
//    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(VADM_Admin, randomAGrpId)).isFalse();
//
//    AuthGroupUtility.DeleteUserAuthGroup(
//        VADM_Admin, randomAGrpId, serviceEmployeeEmpCode); // clean up data
//
//    // AADM TEST
//    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(VADM_Admin);
//    randomAGrpId = authGroupToInsert.getaGrpId();
//    assertThat(AuthGroupUtility.PutAuthGroupUser(AADM_User, authGroupToInsert,
// serviceEmployeeEmpCode))
//        .isEqualTo(200);
//    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(VADM_Admin, randomAGrpId)).isTrue();
//
//    AuthGroupUtility.DeleteUserAuthGroup(
//        VADM_Admin, randomAGrpId, serviceEmployeeEmpCode); // clean up data
//  }
// }
