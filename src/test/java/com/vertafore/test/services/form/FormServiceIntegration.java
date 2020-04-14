package com.vertafore.test.services.form;

import static com.vertafore.test.utilities.misc.HelperUtils.checkStatusForSuccess;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

import com.vertafore.test.abilities.HaveTitanContext;
import com.vertafore.test.models.TitanUser;
import com.vertafore.test.servicewrappers.form;
import com.vertafore.test.utilities.actorextractor.BuildCastOfTitanUsers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class FormServiceIntegration {

  private List<TitanUser> users = new ArrayList<>();

  @Before
  public void setupActors() {
    users.add(new TitanUser("titan.support1@vertafore.com", "VERTAFORE", "VERTAFORE"));
    OnStage.setTheStage(BuildCastOfTitanUsers.loadAndAuthenticate(users));
  }

  @Test
  public void formServiceTest() throws IOException {
    Actor formActor = theActorCalled("titan.support1@vertafore.com");

    HaveTitanContext.theNewProductOf(formActor, "FORM-ADMIN-WEB-UI");
    String productId = HaveTitanContext.theProductIdOf(formActor);
    String entityId = HaveTitanContext.theEntityIdOf(formActor);
    String tenantId = HaveTitanContext.theTenantIdOf(formActor);

    form formApi = new form();

    formActor.attemptsTo(
        formApi.searchForFormTemplatesUsingGetOnTheFormTemplateController(
            productId,
            tenantId,
            entityId,
            "name",
            "bySearchTerm",
            1,
            3,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null));
    SerenityRest.lastResponse().prettyPrint();
    checkStatusForSuccess();
  }
}
