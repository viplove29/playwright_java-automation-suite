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
              new EMSActor().called("VADM_Admin").withKeyType("VADM").withLoginPath("admin"),
              new EMSActor()
                  .called("AADM_NAUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("na_user"),
              new EMSActor()
                  .called("AADM_CBUUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("cbu_user"),
              new EMSActor()
                  .called("AADM_PBUUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("pbu_user"),
              new EMSActor()
                  .called("AADM_EXECUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("exec_user"),
              new EMSActor()
                  .called("AADM_PPUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("pp_user"),
              new EMSActor()
                  .called("AADM_SGUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("sg_user"),
              new EMSActor()
                  .called("AADM_NBTAUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("nbta_user"),
              new EMSActor()
                  .called("AADM_FBTAUser")
                  .withKeyType("AADM")
                  .withLoginPath("user")
                  .withLoginType("fbta_user"),
              new EMSActor().called("IVAN_User").withKeyType("IVAN").withLoginPath("ivans")));

      //    AADM_NAUSER - no access user - no customer or policy access
      //    AADM_CBUUSER - Customer Business Unit access user - read and write access to the
      // customer but no policy access
      //    AADM_PBUUSER - Policy Business Unit access user - read access to customer and read and
      // write access to policy
      //    AADM_EXECUSER - Executive user - read and write access to customer but no policy access
      //    AADM_PPUSER - Policy Personnel user - read access to customer and read and write access
      // to policy
      //    AADM_SGUSER - Service Group user - read only access to customer
      //    AADM_NBTAUSER - User with no access to Bank Transactions

      cast = GetAccessTokens(actors);
      haveAccessTokensBeenGranted = true;
    }
    OnStage.setTheStage(cast);
  }
}
