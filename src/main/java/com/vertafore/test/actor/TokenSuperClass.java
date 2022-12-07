package com.vertafore.test.actor;

import static com.vertafore.test.actor.BuildEMSCast.GetAccessTokens;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.util.EnvVariables;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class TokenSuperClass {
  public static List<EMSActor> actors = new ArrayList<>();
  public static OnlineCast cast;
  private static boolean haveAccessTokensBeenGranted = false;

  @BeforeClass
  public static void beforeAll() {
    System.out.println("Environment is : " + EnvVariables.BASE_URL + "\n");

    if (!haveAccessTokensBeenGranted) {
      actors.addAll(
          List.of(
              new EMSActor().called("AADM_User").withKeyType("AADM").withLoginPath("user"),
              new EMSActor().called("AADM_V4App").withKeyType("AADM").withLoginPath("v4app"),
              new EMSActor().called("ORAN_App").withKeyType("ORAN").withLoginPath("app"),
              new EMSActor().called("AGNY_User").withKeyType("AGNY").withLoginPath("user"),
              new EMSActor().called("VERT_User").withKeyType("VERT").withLoginPath("user"),
              new EMSActor().called("VERT_V4App").withKeyType("VERT").withLoginPath("v4app"),
              new EMSActor().called("VADM_Admin").withKeyType("VADM").withLoginPath("admin")));
      cast = GetAccessTokens(actors);
      haveAccessTokensBeenGranted = true;
    }

    OnStage.setTheStage(cast);
  }
}
