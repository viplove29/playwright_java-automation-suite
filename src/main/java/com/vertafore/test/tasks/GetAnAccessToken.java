// package com.vertafore.test.tasks;
//
// import static net.serenitybdd.screenplay.Tasks.instrumented;
//
// import com.vertafore.test.abilities.HaveALoginKey;
// import com.vertafore.test.abilities.HaveAnAccessToken;
// import net.serenitybdd.screenplay.Actor;
// import net.serenitybdd.screenplay.Performable;
// import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
//
// public class GetAnAccessToken implements Performable {
//  private static final String BASE_URL = "https://botd-q-360iis-1.devop.vertafore.com/ems";
//
//  @Override
//  public <T extends Actor> void performAs(T emsActor) {
//    String context = emsActor.getDescription();
//
//    emsActor.can(HaveALoginKey.with()).can(HaveAnAccessToken.with()).can(CallAnApi.at(BASE_URL));
//
//    emsActor.attemptsTo(GetALoginKey.forActor());
//
//    switch (context) {
//      case "appContext":
//        emsActor.attemptsTo(GetAnAppToken.forActor());
//        break;
//      case "adminContext":
//        emsActor.attemptsTo(GetAnAdminToken.forActor());
//        break;
//      default:
//        emsActor.attemptsTo(GetAUserToken.forActor());
//    }
//  }
//
//  public static GetAnAccessToken forActor() {
//    return instrumented(GetAnAccessToken.class);
//  }
// }
