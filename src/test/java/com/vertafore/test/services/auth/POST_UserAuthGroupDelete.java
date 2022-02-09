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
public class POST_UserAuthGroupDelete {
  private List<EMSActor> actors = new ArrayList<>();

  public static String randomAGrpId;
  public static String serviceEmployeeEmpCode;

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
  public void PostDeleteUserAuthGroupDeletesUserAuthGroup() {
    Actor bob = theActorCalled("bob");
    Actor adam = theActorCalled("adam");
    Actor doug = theActorCalled("doug");

    // Need to put at least one auth group to delete in case application is empty
    // we will always do this with Orange partner (doug) to guarantee AuthGroup is added
    stageAuthGroupData(bob);

    // ORANG TEST
    assertThat(AuthGroupUtility.DeleteUserAuthGroup(doug, randomAGrpId, serviceEmployeeEmpCode))
        .isEqualTo(200);
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(doug, randomAGrpId))
        .isEqualTo(false); // auth group is deleted, none in the list

    stageAuthGroupData(doug);

    // VADM TEST
    assertThat(AuthGroupUtility.DeleteUserAuthGroup(adam, randomAGrpId, serviceEmployeeEmpCode))
        .isEqualTo(403); // AADM test
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(doug, randomAGrpId))
        .isEqualTo(true); // auth group is not deleted, still in the list

    // AADM TEST
    assertThat(AuthGroupUtility.DeleteUserAuthGroup(bob, randomAGrpId, serviceEmployeeEmpCode))
        .isEqualTo(200); // delete the same auth group VADM wasn't authorized to
    assertThat(AuthGroupUtility.isAuthGroupIdInAuthGroupList(doug, randomAGrpId))
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
