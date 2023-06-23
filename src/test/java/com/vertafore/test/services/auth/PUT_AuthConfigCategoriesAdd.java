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
import org.junit.After;
import org.junit.Test;

public class PUT_AuthConfigCategoriesAdd extends TokenSuperClass {

  String categoryId;

  @Test
  public void putAuthConfigCategoriesAddBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseAuthTo AuthAPI = new UseAuthTo();

    // init random category id
    categoryId = "3c08e07a-0133-4f90-adb3-604960d9f9b7";

    List<EmsCategoryPutRequest> categoriesList = new ArrayList<>();
    EmsCategoryPutRequest emsCategoryPutRequest = new EmsCategoryPutRequest();
    emsCategoryPutRequest.setCategoryId(categoryId);
    emsCategoryPutRequest.setDescription("EMS Automation Category");
    emsCategoryPutRequest.setCategoryName("EMS_Automation");

    categoriesList.add(emsCategoryPutRequest);

    // ORAN TEST
    ORAN_App.attemptsTo(
        AuthAPI.PUTAuthConfigCategoriesAddOnTheConfigauthController(categoriesList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // AADM TEST
    AADM_User.attemptsTo(
        AuthAPI.PUTAuthConfigCategoriesAddOnTheConfigauthController(categoriesList, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    // VADM TEST
    VADM_Admin.attemptsTo(AuthAPI.GETAuthConfigCategoriesOnTheConfigauthController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }

  @After
  public void tearDown() {
    if (categoryId != null) {
      // delete the category.
      Actor VADM_Admin = theActorCalled("VADM_Admin");
      UseAuthTo AuthAPI = new UseAuthTo();
      EmsCategoryDeletePostRequest emsCategoryDeletePostRequest =
          new EmsCategoryDeletePostRequest();
      emsCategoryDeletePostRequest.setCategoryId(categoryId);
      VADM_Admin.attemptsTo(
          AuthAPI.POSTAuthConfigCategoriesDeleteOnTheConfigauthController(
              Arrays.asList(emsCategoryDeletePostRequest), "string"));
      assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
      categoryId = null;
    }
  }
}
