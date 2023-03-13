package com.vertafore.test.tasks;

import static com.vertafore.test.abilities.HaveALoginKey.theLoginKeyOf;
import static com.vertafore.test.abilities.HaveAnAccessToken.*;
import static com.vertafore.test.util.EnvVariables.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.vertafore.test.abilities.HaveAnAccessToken;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.HashMap;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class GetAToken implements Performable {

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(GetALoginKey.forActor());

    String accessJWT;
    String loginKey = theLoginKeyOf(actor);
    String username = usernameForActor(actor);
    String password = passwordForActor(actor);

    String loginType = loginTypeForActor(actor);
    if (loginType.isBlank() && username.isBlank() && password.isBlank()) {
      username = USERNAME;
      password = PASSWORD;
    } else if (loginType.equals("na_user") && username.isBlank() && password.isBlank()) {
      username = AADM_NAUSER_USERNAME;
      password = AADM_NAUSER_PASSWORD;
    } else if (loginType.equals("cbu_user") && username.isBlank() && password.isBlank()) {
      username = AADM_CBUUSER_USERNAME;
      password = AADM_CBUUSER_PASSWORD;
    } else if (loginType.equals("pbu_user") && username.isBlank() && password.isBlank()) {
      username = AADM_PBUUSER_USERNAME;
      password = AADM_PBUUSER_PASSWORD;
    } else if (loginType.equals("exec_user") && username.isBlank() && password.isBlank()) {
      username = AADM_EXECUSER_USERNAME;
      password = AADM_EXECUSER_PASSWORD;
    } else if (loginType.equals("pp_user") && username.isBlank() && password.isBlank()) {
      username = AADM_PPUSER_USERNAME;
      password = AADM_PPUSER_PASSWORD;
    } else if (loginType.equals("sg_user") && username.isBlank() && password.isBlank()) {
      username = AADM_SGUSER_USERNAME;
      password = AADM_SGUSER_PASSWORD;
    } else if (loginType.equals("nbta_user") && username.isBlank() && password.isBlank()) {
      username = NO_BANK_TRANSACTION_ACCESS_USERNAME;
      password = NO_BANK_TRANSACTION_ACCESS_PASSWORD;
    }
    // since VSSO functionality has fundamentally changed this code is no longer needed, but can be
    // left here for future reference
    // else if (loginType.equals("vsso") && username.isBlank() && password.isBlank()) {
    //      username = VSSO_USERNAME;
    //      password = VSSO_PASSWORD;
    //    }

    UseAuthTo authAPI = new UseAuthTo();

    /* the model for this, LoginPostRequest, will send nulls for unused keys but the endpoints will not accept nulls for these values, hence the use of a regular hash map instead */
    HashMap<String, String> tokenBody = new HashMap<>();
    tokenBody.put("loginKey", loginKey);

    switch (loginPathForActor(actor).toLowerCase()) {
      case "user":
        tokenBody.put("agencyNo", AGENCY_NO);
        tokenBody.put("username", username);
        tokenBody.put("password", password);

        actor.attemptsTo(
            authAPI.POSTAuthUserLoginOnTheAuthenticationController(tokenBody, "string"));
        break;

      case "app":
        tokenBody.put("agencyNo", AGENCY_NO);
        tokenBody.put("appAccessToAgencyKey", APP_ACCESS_TO_AGENCY_KEY);

        actor.attemptsTo(
            authAPI.POSTAuthAppLoginOnTheAuthenticationController(tokenBody, "string"));
        break;

      case "admin":
        tokenBody.put("username", ADMIN_EMP_CODE);

        actor.attemptsTo(
            authAPI.POSTAuthAdminLoginOnTheAuthenticationController(tokenBody, "string"));
        break;

      case "v4app":
        tokenBody.put("agencyNo", AGENCY_NO);
        tokenBody.put("empCode", ADMIN_EMP_CODE);

        actor.attemptsTo(
            authAPI.POSTAuthVertaforeAppLoginOnTheAuthenticationController(tokenBody, "string"));
        break;

      default:
        throw new IllegalArgumentException("Invalid login path");
    }

    accessJWT =
        LastResponse.received().answeredBy(actor).getBody().jsonPath().getString("accessJwt");

    HaveAnAccessToken.theNewAccessTokenOf(actor, accessJWT);
  }

  public static GetAToken forActor() {
    return instrumented(GetAToken.class);
  }
}
