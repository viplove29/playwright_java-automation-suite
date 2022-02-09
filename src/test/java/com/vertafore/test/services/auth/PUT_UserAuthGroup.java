package com.vertafore.test.services.auth;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.models.ems.AuthGroupResponse;
import com.vertafore.test.util.AuthGroupUtility;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class PUT_UserAuthGroup {
  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(
        List.of(
            new EMSActor().called("bob").withContext("userContext"),
            new EMSActor().called("doug").withContext("appContext"),
            new EMSActor().called("adam").withContext("adminContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void PutAuthGroupUserUpdatesAuthGroupUser() {
    Actor bob = theActorCalled("bob");
    Actor adam = theActorCalled("adam");
    Actor doug = theActorCalled("doug");

    AuthGroupResponse authGroupToInsert;
    String randomAGrpId;
    String serviceEmployeeEmpCode = AuthGroupUtility.getCurrentServiceEmployeeEmpCode(doug);

    // ORANG TEST
    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(doug);
    randomAGrpId = authGroupToInsert.getaGrpId();

    assertThat(AuthGroupUtility.PutAuthGroupUser(doug, authGroupToInsert, serviceEmployeeEmpCode))
        .isEqualTo(200);
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(doug, randomAGrpId)).isTrue();

    AuthGroupUtility.DeleteUserAuthGroup(
        doug, randomAGrpId, serviceEmployeeEmpCode); // clean up data

    // VADM TEST
    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(doug);
    randomAGrpId = authGroupToInsert.getaGrpId();
    assertThat(AuthGroupUtility.PutAuthGroupUser(adam, authGroupToInsert, serviceEmployeeEmpCode))
        .isEqualTo(403);
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(doug, randomAGrpId)).isFalse();

    AuthGroupUtility.DeleteUserAuthGroup(
        doug, randomAGrpId, serviceEmployeeEmpCode); // clean up data

    // AADM TEST
    authGroupToInsert = AuthGroupUtility.selectRandomAuthGroup(doug);
    randomAGrpId = authGroupToInsert.getaGrpId();
    assertThat(AuthGroupUtility.PutAuthGroupUser(bob, authGroupToInsert, serviceEmployeeEmpCode))
        .isEqualTo(200);
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(doug, randomAGrpId)).isTrue();

    AuthGroupUtility.DeleteUserAuthGroup(
        doug, randomAGrpId, serviceEmployeeEmpCode); // clean up data
  }
}
