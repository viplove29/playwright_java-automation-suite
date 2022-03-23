package com.vertafore.test.util;

import static com.vertafore.test.util.EnvVariables.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class AuthGroupUtility {

  // Validation Helpers
  public static String currentAGrpId;
  public static String currentGrpDescShort;
  public static String currentGrpDescLong;
  public static Integer currentPermFlag;
  public static Boolean currentIsHide; // changed from String

  public static void setAuthGroupHelperVariables(AuthGroupResponse authGroupResponse) {
    currentAGrpId = authGroupResponse.getaGrpId();
    currentGrpDescShort = authGroupResponse.getGrpDescShort();
    currentGrpDescLong = authGroupResponse.getGrpDescLong();
    currentPermFlag = authGroupResponse.getPermFlag();
    currentIsHide = authGroupResponse.getIsHide();
  }

  public static void validateSingleAuthGroup(AuthGroupResponse authGroupResponse) {
    assertThat(authGroupResponse.getaGrpId()).isEqualTo(currentAGrpId);
    assertThat(authGroupResponse.getGrpDescShort()).isEqualTo(currentGrpDescShort);
    assertThat(authGroupResponse.getGrpDescLong()).isEqualTo(currentGrpDescLong);
    assertThat(authGroupResponse.getPermFlag()).isEqualTo(currentPermFlag);
  }

  public static AuthGroupResponse selectRandomAuthGroup(Actor actor) {
    UseAuthTo AuthAPI = new UseAuthTo();

    actor.attemptsTo(
        AuthAPI.GETAuthConfigAuthGroupsAuthGroupIdOnTheConfigauthController(null, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<AuthGroupResponse> authGroupResponses =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", AuthGroupResponse.class);

    assertThat(authGroupResponses.get(0).getClass().getDeclaredFields().length)
        .isEqualTo(5); // changed from 13
    assertThat(authGroupResponses.size()).isGreaterThan(0);
    int randomInt = new Random().nextInt(authGroupResponses.size());

    return authGroupResponses.get(randomInt);
  }

  /*
     This functionality is only available through orange partner applications with service employees,
     however, the endpoints themselves can be used by a user context as well, hence the multiple actors
     accessing the same data of the environments current orange partner app
  */
  public static AppAccessToAgencyResponse getCurrentApplicationWithCurrentAgency(Actor actor) {

    UseAuthTo authAPI = new UseAuthTo();

    actor.attemptsTo(
        authAPI.GETAuthConfigAgencyKeysAppIdOnTheConfigauthController(ORAN_APP_ID, "string"));

    List<AppAccessToAgencyResponse> appAccessToAgencyResponses =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", AppAccessToAgencyResponse.class);

    /*
       an app can be available through multiple agencies
       Filtering by the app access to agency key returns the right appAccessToAgencyResponse
    */
    //    String version = HaveALoginKey.versionForActor(actor);
    String appAccessToAgencyKey = APP_ACCESS_TO_AGENCY_KEY.toLowerCase();

    AppAccessToAgencyResponse filteredAppAccessToAgencyResponse =
        appAccessToAgencyResponses
            .stream()
            .filter(appAgency -> appAgency.getAppAccessToAgencyKey().contains(appAccessToAgencyKey))
            .collect(Collectors.toList())
            .get(0);

    return filteredAppAccessToAgencyResponse;
  }

  public static String getCurrentServiceEmployeeEmpCode(Actor actor) {
    return getCurrentApplicationWithCurrentAgency(actor).getEmpCode();
  }

  public static boolean isAuthGroupIdInAuthGroupList(Actor actor, String authGroupID) {

    // get current application/agency to validate against
    AppAccessToAgencyResponse appAccessToAgencyResponse =
        AuthGroupUtility.getCurrentApplicationWithCurrentAgency(actor);

    // validate response, since Service Employee can have multiple auth groups, cycle through
    // the list and see if the respective unique authGroupId appears.

    List<AuthGroupUser> authGroupUserList =
        appAccessToAgencyResponse
            .getUserAuthGroups()
            .stream()
            .filter(authGroup -> authGroup.getAuthGroupId().contains(authGroupID))
            .collect(Collectors.toList());

    return authGroupUserList.size() > 0;
  }
}
