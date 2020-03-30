package com.vertafore.test.tasks.servicewrappers.commission;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:48 */
public class UseCommissionServiceTo {

  private static final String THIS_SERVICE = "commission";

  public static Performable calculateFullCommission_DEPRECATEDUsingGetOnTheCommissionController(
      String policyId, String chargeAmount) {
    return Task.where(
        "{0} DEPRECATED - Get Agency and Employee Commission Amount for a Policy",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .queryParam("chargeAmount", chargeAmount)
              .get("commissions");
        });
  }

  public static Performable patchCommissionRuleUsingPatchOnTheCommissionRulesController(
      String id, Object body) {
    return Task.where(
        "{0} Update existing commission rule.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("rules/{id}");
        });
  }

  public static Performable getCommissionRulesUsingGetOnTheCommissionRulesController(
      String name, String sortField, String isDescending, String pageSize, String page) {
    return Task.where(
        "{0} Retrieve all commission rules ordered by Rank ascending.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("name", name)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("rules");
        });
  }

  public static Performable getDefaultRuleUsingGetOnTheDefaultRuleController() {
    return Task.where(
        "{0} Get default rule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("rules/default");
        });
  }

  public static Performable createCommissionRuleUsingPostOnTheCommissionRulesController(
      Object body) {
    return Task.where(
        "{0} Creates new commission rule.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("rules");
        });
  }

  public static Performable calculateFullCommissionUsingGetOnTheCommissionController(
      String policyId, String chargeAmount) {
    return Task.where(
        "{0} Get Agency and Employee Commission Amount for a Policy",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .queryParam("chargeAmount", chargeAmount)
              .get("commissions/full");
        });
  }

  public static Performable updateCommissionRuleUsingPutOnTheCommissionRulesController(
      String id, Object body) {
    return Task.where(
        "{0} Update existing commission rule.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("rules/{id}");
        });
  }

  public static Performable calculateAgencyCommissionUsingGetOnTheCommissionController(
      String policyId, String chargeAmount) {
    return Task.where(
        "{0} Get Agency Commission amount for the charge amount based on Policy's Agency Commission",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .queryParam("chargeAmount", chargeAmount)
              .get("commissions/agency");
        });
  }

  public static Performable deleteCommissionRuleUsingDeleteOnTheCommissionRulesController(
      String id) {
    return Task.where(
        "{0} Delete Commission rule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("rules/{id}");
        });
  }

  public static Performable updateDefaultRuleUsingPutOnTheDefaultRuleController(Object body) {
    return Task.where(
        "{0} Update default rule",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("rules/default");
        });
  }

  public static Performable getCommissionRuleUsingGetOnTheCommissionRulesController(String id) {
    return Task.where(
        "{0} Get Commission rule by ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("rules/{id}");
        });
  }

  public static Performable calculateEmployeeCommissionUsingGetOnTheCommissionController(
      String policyId, String chargeAmount) {
    return Task.where(
        "{0} Get Employee Commission Amount for the charge amount based on Policy Data",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .queryParam("chargeAmount", chargeAmount)
              .get("commissions/employee");
        });
  }
}
