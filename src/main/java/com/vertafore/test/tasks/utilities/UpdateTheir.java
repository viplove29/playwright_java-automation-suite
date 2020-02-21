package com.vertafore.test.tasks.utilities;

import static com.vertafore.test.abilities.UseADomain.theDomainOf;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.vertafore.test.abilities.HaveTenantId;
import com.vertafore.test.abilities.UseADomain;
import com.vertafore.test.abilities.UseAService;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class UpdateTheir implements Task {

  private static final String TENANT_ID_KEY = "tenantId";
  private static final String ENTITY_ID_KEY = "entityId";
  private static final String SERVICE_KEY = "service";
  private static final String DOMAIN_KEY = "domain";

  private final String newValue;
  private final String abilityToBeChanged;

  public UpdateTheir(String newValue, String abilityToBeChanged) {
    this.newValue = newValue;
    this.abilityToBeChanged = abilityToBeChanged;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    if (abilityToBeChanged.equals(DOMAIN_KEY)) {
      actor.can(UseADomain.of(newValue));
      actor.can(CallAnApi.at(theDomainOf(actor)));
      return;
    } else if (abilityToBeChanged.equals(TENANT_ID_KEY)) {
      actor.can(HaveTenantId.of(newValue));
    } else if (abilityToBeChanged.equals(ENTITY_ID_KEY)) {
      actor.can(HaveTenantId.of(newValue));
    } else if (abilityToBeChanged.equals(SERVICE_KEY)) {
      actor.can(UseAService.called(newValue));
    } else {
      throw new IllegalArgumentException(
          abilityToBeChanged + " is not an actor ability that can be changed");
    }
    actor.attemptsTo(ConfigureParamsAndHeaders.asUser());
  }

  public static UpdateTheir tenantIdTo(String tenantId) {
    return instrumented(UpdateTheir.class, tenantId, TENANT_ID_KEY);
  }

  public static UpdateTheir entityIdTo(String entityId) {
    return instrumented(UpdateTheir.class, entityId, ENTITY_ID_KEY);
  }

  public static UpdateTheir serviceTo(String service) {
    return instrumented(UpdateTheir.class, service, SERVICE_KEY);
  }

  public static UpdateTheir domainTo(String domain) {
    return instrumented(UpdateTheir.class, domain, DOMAIN_KEY);
  }
}
