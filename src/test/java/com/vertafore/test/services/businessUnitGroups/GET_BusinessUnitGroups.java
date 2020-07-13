package com.vertafore.test.services.businessUnitGroups;

import static com.vertafore.test.actor.BuildEMSCast.GetAnAccessToken;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.EMSActor;
import com.vertafore.test.servicewrappers.UseBusinessUnitsTo;
import io.restassured.path.json.JsonPath;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_BusinessUnitGroups {

  private List<EMSActor> actors = new ArrayList<>();

  @Before
  public void getAnAccessToken() {
    actors.addAll(List.of(new EMSActor().called("bob").withContext("userContext")));
    OnStage.setTheStage(GetAnAccessToken(actors));
  }

  @Test
  public void businessUnitGroupsReturnsAllBUGroups() {

    Actor bob = theActorCalled("bob");

    UseBusinessUnitsTo buGroupsAPI = new UseBusinessUnitsTo();

    bob.attemptsTo(buGroupsAPI.GETBusinessUnitsGroupsOnTheBusinessunitsController());

    SerenityRest.lastResponse().prettyPrint();

    bob.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    Integer numberOfBUGroups =
        LastResponse.received().answeredBy(bob).getBody().jsonPath().getList("").size();
    assertThat(numberOfBUGroups).isEqualTo(5);
    assertThat(LastResponse.received().answeredBy(bob).getBody().jsonPath().getString("name"))
        .isEqualTo(
            "[Div: (All), Branch: (All), Dept: (All), Group: (All), Div: Division two, Branch: Division two, Dept: Department two, Group: Group two, Div: Division one, Branch: Division one, Dept: Department one, Group: Group one, Div - Division One , Branch - Division one Branch, Div: Division Test Customers, Branch: Division Test Customers, Dept: Department TC, Group: Group TC]");
    assertThat(LastResponse.received().answeredBy(bob).getBody().jsonPath().getString("id"))
        .isEqualTo(
            "[945d93e7-9792-4cf0-8040-1b79a73d2826, c670a447-1a07-4f66-ae28-48ad91b11df9, f42d8d3e-d55d-4ae5-93fc-4b825cf77465, 06f45ebf-3c01-4469-a250-8c5800966113, 84d596fe-29f0-4b1a-b885-a098f6268ac0]");
  }

  @Test
  public void businessUnitsInGroupReturnsHierarchyOfSelectedGroup() {

    Actor bob = theActorCalled("bob");

    UseBusinessUnitsTo buGroupsAPI = new UseBusinessUnitsTo();

    bob.attemptsTo(buGroupsAPI.GETBusinessUnitsGroupsOnTheBusinessunitsController());

    bob.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));
    String[] ids =
        LastResponse.received().answeredBy(bob).getBody().jsonPath().getString("id").split(", ");

    bob.attemptsTo(
        buGroupsAPI.GETBusinessUnitsBusinessUnitsInGroupBuGroupIdOnTheBusinessunitsController(
            ids[1], "string"));

    SerenityRest.lastResponse().prettyPrint();

    JsonPath jsonPath = LastResponse.received().answeredBy(bob).getBody().jsonPath();
    assertThat(jsonPath.getString("name")).isEqualTo("[Group two]");
    assertThat(jsonPath.getString("shortName")).isEqualTo("[Grp2  ]");
    assertThat(jsonPath.getString("division")).isEqualTo("[Division two]");
    assertThat(jsonPath.getString("branch")).isEqualTo("[Division two]");
    assertThat(jsonPath.getString("department")).isEqualTo("[Department two]");
    assertThat(jsonPath.getString("group")).isEqualTo("[Group two]");
    assertThat(jsonPath.getString("isHide")).isEqualTo("[false]");
    assertThat(jsonPath.getString("status")).isEqualTo("[C]");
    assertThat(jsonPath.getString("glCode")).isEqualTo("[!!\"]");
  }
}
