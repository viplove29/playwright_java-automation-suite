package com.vertafore.test.services.auth;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.EmsCategoryDeletePostRequest;
import com.vertafore.test.models.ems.EmsCategoryPutRequest;
import com.vertafore.test.servicewrappers.UseAuthTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_AuthConfigCategoriesDelete extends TokenSuperClass {

  @Test
  public void postAuthConfigCategoriesDeleteBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // init random category id
    String categoryId = "3c08e07a-0133-4f90-adb3-604960d9f9b7";

    List<EmsCategoryPutRequest> categoriesList = new ArrayList<>();
    EmsCategoryPutRequest emsCategoryPutRequest = new EmsCategoryPutRequest();
    emsCategoryPutRequest.setCategoryId(categoryId);
    emsCategoryPutRequest.setDescription("EMS Automation Category");
    emsCategoryPutRequest.setCategoryName("EMS_Automation");

    categoriesList.add(emsCategoryPutRequest);

    // Create Category
    VADM_Admin.attemptsTo(AuthAPI.GETAuthConfigCategoriesOnTheConfigauthController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // make post delete calls
    EmsCategoryDeletePostRequest emsCategoryDeletePostRequest = new EmsCategoryDeletePostRequest();
    emsCategoryDeletePostRequest.setCategoryId(categoryId);
    List<EmsCategoryDeletePostRequest> emsCategoryList =
        Arrays.asList(emsCategoryDeletePostRequest);

    AADM_User.attemptsTo(
        AuthAPI.POSTAuthConfigCategoriesDeleteOnTheConfigauthController(emsCategoryList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        AuthAPI.POSTAuthConfigCategoriesDeleteOnTheConfigauthController(emsCategoryList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    VADM_Admin.attemptsTo(
        AuthAPI.POSTAuthConfigCategoriesDeleteOnTheConfigauthController(emsCategoryList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
