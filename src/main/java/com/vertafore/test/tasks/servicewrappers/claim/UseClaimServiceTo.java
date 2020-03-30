package com.vertafore.test.tasks.servicewrappers.claim;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:48 */
public class UseClaimServiceTo {

  private static final String THIS_SERVICE = "claim";

  public static Performable createClaimPaymentUsingPostOnTheClaimPaymentController(
      String claimId, Object body) {
    return Task.where(
        "{0} Create Claim Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .body(body)
              .post("claims/{claimId}/payments");
        });
  }

  public static Performable updateClaimPartyUsingPutOnTheClaimPartyController(
      String claimId, Object body, String partyId) {
    return Task.where(
        "{0} Update Claim Party",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .body(body)
              .pathParam("partyId", partyId)
              .put("claims/{claimId}/parties/{partyId}");
        });
  }

  public static Performable getClaimUsingGetOnTheClaimController(String id) {
    return Task.where(
        "{0} Get Claim",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("claims/{id}");
        });
  }

  public static Performable createClaimUsingPostOnTheClaimController(Object body) {
    return Task.where(
        "{0} Create Claim",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("claims");
        });
  }

  public static Performable getClaimPartiesByClaimIdUsingGetOnTheClaimPartyController(
      String claimId, String pageSize, String page) {
    return Task.where(
        "{0} Get Claim Party",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("claims/{claimId}/parties");
        });
  }

  public static Performable getClaimsByPolicyIdUsingGetOnTheClaimController(
      String policyId, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get Claims by policyId",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("claims?filter=byPolicyId");
        });
  }

  public static Performable updateClaimPaymentUsingPutOnTheClaimPaymentController(
      String claimId, Object body, String paymentId) {
    return Task.where(
        "{0} Update Claim Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .body(body)
              .pathParam("paymentId", paymentId)
              .put("claims/{claimId}/payments/{paymentId}");
        });
  }

  public static Performable deleteClaimPaymentUsingDeleteOnTheClaimPaymentController(
      String claimId, String paymentId) {
    return Task.where(
        "{0} Delete Claim Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .pathParam("paymentId", paymentId)
              .delete("claims/{claimId}/payments/{paymentId}");
        });
  }

  public static Performable createClaimPartyUsingPostOnTheClaimPartyController(
      String claimId, Object body) {
    return Task.where(
        "{0} Create Claim Party",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .body(body)
              .post("claims/{claimId}/parties");
        });
  }

  public static Performable getClaimsUsingGetOnTheClaimController(
      String sortField,
      String isDescending,
      String pageSize,
      String page,
      String statuses,
      String customerId) {
    return Task.where(
        "{0} Get All Claims",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("statuses", statuses)
              .queryParam("customerId", customerId)
              .get("claims");
        });
  }

  public static Performable deleteClaimUsingDeleteOnTheClaimController(String id) {
    return Task.where(
        "{0} Delete Claim",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("claims/{id}");
        });
  }

  public static Performable deleteClaimPartyUsingDeleteOnTheClaimPartyController(
      String claimId, String partyId) {
    return Task.where(
        "{0} Delete Claim Party",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .pathParam("partyId", partyId)
              .delete("claims/{claimId}/parties/{partyId}");
        });
  }

  public static Performable getClaimPaymentsByClaimIdUsingGetOnTheClaimPaymentController(
      String claimId, String pageSize, String page) {
    return Task.where(
        "{0} Get Claim Payments",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("claimId", claimId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("claims/{claimId}/payments");
        });
  }

  public static Performable updateClaimUsingPutOnTheClaimController(String id, Object body) {
    return Task.where(
        "{0} Update Claim",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("claims/{id}");
        });
  }
}
