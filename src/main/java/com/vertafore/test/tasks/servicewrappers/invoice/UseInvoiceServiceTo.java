package com.vertafore.test.tasks.servicewrappers.invoice;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:56 */
public class UseInvoiceServiceTo {

  private static final String THIS_SERVICE = "invoice";

  public static Performable getBasicInvoiceInfoUsingGetOnTheInvoiceController(String policyId) {
    return Task.where(
        "{0} Generate basic invoice info",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .get("invoices/basic-info");
        });
  }

  public static Performable setPaymentPrioritySettingsUsingPutOnTheSettingsController(Object body) {
    return Task.where(
        "{0} Set payment priority settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("setting-groups/payment-priority");
        });
  }

  public static Performable getInvoiceByIdsUsingGetOnTheInvoiceController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get Invoices by IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("invoices?filter=byIds");
        });
  }

  public static Performable createPaymentUsingPostOnThePaymentsController(Object body) {
    return Task.where(
        "{0} Create Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("payments");
        });
  }

  public static Performable createInvoiceUsingPostOnTheInvoiceController(Object body) {
    return Task.where(
        "{0} Create Invoice",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("invoices");
        });
  }

  public static Performable getPaymentsUsingGetOnThePaymentsController(
      String pageSize, String page) {
    return Task.where(
        "{0} Retrieve Payments for all Invoices",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("payments");
        });
  }

  public static Performable updatePaymentUsingPutOnThePaymentsController(String id, Object body) {
    return Task.where(
        "{0} Replace Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("payments/{id}");
        });
  }

  public static Performable createCustomerInvoiceRenderUsingPostOnTheInvoiceController(String id) {
    return Task.where(
        "{0} Generate Customer Invoice",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .post("invoices/{id}/customer-invoices");
        });
  }

  public static Performable retrieveStoredCustomerInvoiceUsingGetOnTheInvoiceController(String id) {
    return Task.where(
        "{0} Retrieve Customer Invoice",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("invoices/{id}/customer-invoices");
        });
  }

  public static Performable searchForInvoicesUsingGetOnTheInvoiceController(
      String searchTerm,
      String statuses,
      String sortField,
      String isDescending,
      String pageSize,
      String page,
      String filter) {
    return Task.where(
        "{0} Search for Invoices",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("statuses", statuses)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("invoices?filter=bySearchTerm");
        });
  }

  public static Performable getLateFeesSettingsUsingGetOnTheSettingsController() {
    return Task.where(
        "{0} Get late fees settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("setting-groups/late-fees");
        });
  }

  public static Performable formatInvoiceLineItemUsingPostOnTheChargeController(Object body) {
    return Task.where(
        "{0} Provides logic to specially format an invoice line item.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("invoice-line-item");
        });
  }

  public static Performable getInvoicesUsingGetOnTheInvoiceController(
      String pageSize, String page) {
    return Task.where(
        "{0} Get all Invoices",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("invoices");
        });
  }

  public static Performable deletePaymentUsingDeleteOnThePaymentsController(String id) {
    return Task.where(
        "{0} Delete Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("payments/{id}");
        });
  }

  public static Performable setInvoiceSettingsUsingPutOnTheSettingsController(Object body) {
    return Task.where(
        "{0} Set invoice settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("setting-groups/invoices");
        });
  }

  public static Performable patchChargeUsingPatchOnTheChargeController(
      String chargeId, Object body) {
    return Task.where(
        "{0} Patch Charge",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("chargeId", chargeId)
              .body(body)
              .patch("charges/{chargeId}");
        });
  }

  public static Performable getInvoiceSettingsUsingGetOnTheSettingsController() {
    return Task.where(
        "{0} Get invoice settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("setting-groups/invoices");
        });
  }

  public static Performable getApplicablePolicyChargesUsingGetOnTheChargeController(
      String policyId, String pageSize, String page) {
    return Task.where(
        "{0} Retrieve a list of all Charges applicable to a Policy",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("policyId", policyId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("charges/policy-charges/{policyId}");
        });
  }

  public static Performable createChargeUsingPostOnTheChargeController(Object body) {
    return Task.where(
        "{0} Create Charge",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("charges");
        });
  }

  public static Performable deleteChargeUsingDeleteOnTheChargeController(String id) {
    return Task.where(
        "{0} Delete Charge",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("charges/{id}");
        });
  }

  public static Performable deleteInvoiceUsingDeleteOnTheInvoiceController(String id) {
    return Task.where(
        "{0} Delete invoice",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("invoices/{id}");
        });
  }

  public static Performable getChargeUsingGetOnTheChargeController(String chargeId) {
    return Task.where(
        "{0} Get Charge By ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("chargeId", chargeId)
              .get("charges/{chargeId}");
        });
  }

  public static Performable getPaymentUsingGetOnThePaymentsController(String id) {
    return Task.where(
        "{0} Retrieve Payment",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("payments/{id}");
        });
  }

  public static Performable getInvoiceUsingGetOnTheInvoiceController(String id) {
    return Task.where(
        "{0} Get Invoice by ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("invoices/{id}");
        });
  }

  public static Performable updateChargeUsingPutOnTheChargeController(
      String chargeId, Object body) {
    return Task.where(
        "{0} Update Charge",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("chargeId", chargeId)
              .body(body)
              .put("charges/{chargeId}");
        });
  }

  public static Performable getThresholdSettingsUsingGetOnTheSettingsController() {
    return Task.where(
        "{0} Get threshold settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("setting-groups/thresholds");
        });
  }

  public static Performable setThresholdSettingsUsingPutOnTheSettingsController(Object body) {
    return Task.where(
        "{0} Set threshold settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("setting-groups/thresholds");
        });
  }

  public static Performable setLateFeeSettingsUsingPutOnTheSettingsController(Object body) {
    return Task.where(
        "{0} Set late fee settings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("setting-groups/late-fees");
        });
  }

  public static Performable getChargesUsingGetOnTheChargeController(
      String searchTerm,
      String status,
      String isSystemCharge,
      String isArchived,
      String sortField,
      String isDescending,
      String pageSize,
      String page) {
    return Task.where(
        "{0} Retrieve a list of all Charges",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("status", status)
              .queryParam("isSystemCharge", isSystemCharge)
              .queryParam("isArchived", isArchived)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(
                  "charges?sortField=TITLE{&searchTerm,status,isSystemCharge,isArchived,isDescending,pageSize,page}");
        });
  }

  public static Performable getPaymentPrioritySettingsUsingGetOnTheSettingsController() {
    return Task.where(
        "{0} Get payment priority setting",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("setting-groups/payment-priority");
        });
  }

  public static Performable getPaymentByIdsUsingGetOnThePaymentsController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve Payments by IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("payments?filter=byIds");
        });
  }

  public static Performable getPaymentsByInvoiceIdUsingGetOnThePaymentsController(
      String id, String pageSize, String page) {
    return Task.where(
        "{0} Retrieve Payments for Invoice",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("invoices/{id}/payments");
        });
  }
}
