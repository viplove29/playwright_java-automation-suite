package com.vertafore.test.services.business_units;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BusinessUnitAccessGroupResponse;
import com.vertafore.test.servicewrappers.UseBusinessUnitsTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_BusinessUnitGroups extends TokenSuperClass {

  /* TODO this needs some work lol */
  @Test
  public void businessUnitGroupsReturnsAllBUGroups() {

    Actor AADM_User = theActorCalled("AADM_User");

    UseBusinessUnitsTo buGroupsAPI = new UseBusinessUnitsTo();

    AADM_User.attemptsTo(buGroupsAPI.GETBusinessUnitsGroupsOnTheBusinessunitsController());

    AADM_User.should(seeThatResponse("successfully gets response", res -> res.statusCode(200)));

    List<BusinessUnitAccessGroupResponse> buList =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitAccessGroupResponse.class);

    assertThat(buList.size()).isGreaterThan(0);

    assertThat(buList.getClass().getDeclaredFields().length).isEqualTo(2);
  }

  /* there should be another test here for business-units/business-units-in-group/{buGroupId} but this endpoint is currently broken. This is addressed by DE23986 */
}
