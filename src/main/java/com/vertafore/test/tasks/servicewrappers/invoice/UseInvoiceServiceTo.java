package com.vertafore.test.tasks.servicewrappers.invoice;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseInvoiceServiceTo {

  private static final String GET_INVOICE_SETTINGS = "setting-groups/invoices";
  private static final String SET_INVOICE_SETTINGS = "setting-groups/invoices";
  private static final String RETRIEVE_INVOICE_PAYMENTS_FOR_ALL_INVOICES =
      "invoice-payments{?pageSize,page}";
  private static final String RETRIEVE_INVOICE_PAYMENTS_BY_IDS =
      "invoice-payments?filter=byIds{&ids,pageSize,page}";
  private static final String PATCH_CHARGE = "charges/{chargeId}";
  private static final String GET_CHARGE_BY_ID = "charges/{chargeId}";
  private static final String CREATE_INVOICE_PAYMENT = "invoice-payments";
  private static final String SEARCH_FOR_INVOICES =
      "invoices?filter=bySearchTerm{&searchTerm,statuses,sortField,isDescending,pageSize,page}";
  private static final String RETRIEVE_INVOICE_PAYMENT = "invoice-payments/{id}";
  private static final String DELETE_INVOICE_PAYMENT = "invoice-payments/{id}";
  private static final String REPLACE_INVOICE_PAYMENT = "invoice-payments/{id}";
  private static final String GET_INVOICES_BY_IDS = "invoices?filter=byIds{&ids,pageSize,page}";
  private static final String PROVIDES_LOGIC_TO_SPECIALLY_FORMAT_AN_INVOICE_LINE_ITEM =
      "invoice-line-item";
  private static final String CREATE_INVOICE = "invoices";
  private static final String DELETE_CHARGE = "charges/{id}";
  private static final String RETRIEVE_A_LIST_OF_ALL_CHARGES =
      "charges?sortField=TITLE{&searchTerm,status,isSystemCharge,isDescending,pageSize,page}";
  private static final String GET_ALL_INVOICES = "invoices{?pageSize,page}";
  private static final String RETRIEVE_INVOICE_PAYMENTS_FOR_INVOICE =
      "invoices/{id}/invoice-payments{?pageSize,page}";
  private static final String GET_INVOICE_BY_ID = "invoices/{id}";
  private static final String DELETE_INVOICE = "invoices/{id}";
  private static final String GENERATE_CUSTOMER_INVOICE = "invoices/{id}/customer-invoices";
  private static final String RETRIEVE_CUSTOMER_INVOICE = "invoices/{id}/customer-invoices";
  private static final String GENERATE_BASIC_INVOICE_INFO = "invoices/basic-info{?policyId}";
  private static final String RETRIEVE_A_LIST_OF_ALL_CHARGES_APPLICABLE_TO_A_POLICY =
      "charges/policy-charges/{policyId}{?pageSize,page}";
  private static final String GET_PAYMENT_PRIORITY_SETTING = "setting-groups/payment-priority";
  private static final String SET_PAYMENT_PRIORITY_SETTINGS = "setting-groups/payment-priority";
  private static final String GET_THRESHOLD_SETTINGS = "setting-groups/thresholds";
  private static final String SET_THRESHOLD_SETTINGS = "setting-groups/thresholds";
  private static final String CREATE_CHARGE = "charges";
  private static final String GET_LATE_FEES_SETTINGS = "setting-groups/late-fees";
  private static final String SET_LATE_FEE_SETTINGS = "setting-groups/late-fees";

  public static Performable getInvoiceSettings() {
    return Task.where(
        "{0} Get invoice settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GET_INVOICE_SETTINGS));
        });
  }

  public static Performable setInvoiceSettings(Object body) {
    return Task.where(
        "{0} Set invoice settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .put(as(actor).toEndpoint(SET_INVOICE_SETTINGS));
        });
  }

  public static Performable retrieveInvoicePaymentsForAllInvoices(String pageSize, String page) {
    return Task.where(
        "{0} Retrieve Invoice Payments for all Invoices",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(RETRIEVE_INVOICE_PAYMENTS_FOR_ALL_INVOICES));
        });
  }

  public static Performable retrieveInvoicePaymentsByIds(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve Invoice Payments by IDs",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(RETRIEVE_INVOICE_PAYMENTS_BY_IDS));
        });
  }

  public static Performable patchCharge(String chargeId, Object body) {
    return Task.where(
        "{0} Patch Charge",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("chargeId", chargeId)
              .body(body)
              .patch(as(actor).toEndpoint(PATCH_CHARGE));
        });
  }

  public static Performable getChargeById(String chargeId) {
    return Task.where(
        "{0} Get Charge By ID",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("chargeId", chargeId)
              .get(as(actor).toEndpoint(GET_CHARGE_BY_ID));
        });
  }

  public static Performable createInvoicePayment(Object body) {
    return Task.where(
        "{0} Create Invoice Payment",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .post(as(actor).toEndpoint(CREATE_INVOICE_PAYMENT));
        });
  }

  public static Performable searchForInvoices(
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
          rest()
              .with()
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("statuses", statuses)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(SEARCH_FOR_INVOICES));
        });
  }

  public static Performable retrieveInvoicePayment(String id) {
    return Task.where(
        "{0} Retrieve Invoice Payment",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(RETRIEVE_INVOICE_PAYMENT));
        });
  }

  public static Performable deleteInvoicePayment(String id) {
    return Task.where(
        "{0} Delete Invoice Payment",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETE_INVOICE_PAYMENT));
        });
  }

  public static Performable replaceInvoicePayment(String id, Object body) {
    return Task.where(
        "{0} Replace Invoice Payment",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put(as(actor).toEndpoint(REPLACE_INVOICE_PAYMENT));
        });
  }

  public static Performable getInvoicesByIds(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get Invoices by IDs",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(GET_INVOICES_BY_IDS));
        });
  }

  public static Performable providesLogicToSpeciallyFormatAnInvoiceLineItem(Object body) {
    return Task.where(
        "{0} Provides logic to specially format an invoice line item.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .post(as(actor).toEndpoint(PROVIDES_LOGIC_TO_SPECIALLY_FORMAT_AN_INVOICE_LINE_ITEM));
        });
  }

  public static Performable createInvoice(Object body) {
    return Task.where(
        "{0} Create Invoice",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .post(as(actor).toEndpoint(CREATE_INVOICE));
        });
  }

  public static Performable deleteCharge(String id) {
    return Task.where(
        "{0} Delete Charge",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETE_CHARGE));
        });
  }

  public static Performable retrieveAListOfAllCharges(
      String searchTerm,
      String status,
      String isSystemCharge,
      String sortField,
      String isDescending,
      String pageSize,
      String page) {
    return Task.where(
        "{0} Retrieve a list of all Charges",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("status", status)
              .queryParam("isSystemCharge", isSystemCharge)
              .queryParam("sortField", sortField)
              .queryParam("isDescending", isDescending)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(RETRIEVE_A_LIST_OF_ALL_CHARGES));
        });
  }

  public static Performable getAllInvoices(String pageSize, String page) {
    return Task.where(
        "{0} Get all Invoices",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(GET_ALL_INVOICES));
        });
  }

  public static Performable retrieveInvoicePaymentsForInvoice(
      String id, String pageSize, String page) {
    return Task.where(
        "{0} Retrieve Invoice Payments for Invoice",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(RETRIEVE_INVOICE_PAYMENTS_FOR_INVOICE));
        });
  }

  public static Performable getInvoiceById(String id) {
    return Task.where(
        "{0} Get Invoice by ID",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(GET_INVOICE_BY_ID));
        });
  }

  public static Performable deleteInvoice(String id) {
    return Task.where(
        "{0} Delete invoice",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETE_INVOICE));
        });
  }

  public static Performable generateCustomerInvoice(String id) {
    return Task.where(
        "{0} Generate Customer Invoice",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .post(as(actor).toEndpoint(GENERATE_CUSTOMER_INVOICE));
        });
  }

  public static Performable retrieveCustomerInvoice(String id) {
    return Task.where(
        "{0} Retrieve Customer Invoice",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(RETRIEVE_CUSTOMER_INVOICE));
        });
  }

  public static Performable generateBasicInvoiceInfo(String policyId) {
    return Task.where(
        "{0} Generate basic invoice info",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("policyId", policyId)
              .get(as(actor).toEndpoint(GENERATE_BASIC_INVOICE_INFO));
        });
  }

  public static Performable retrieveAListOfAllChargesApplicableToAPolicy(
      String policyId, String pageSize, String page) {
    return Task.where(
        "{0} Retrieve a list of all Charges applicable to a Policy",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("policyId", policyId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(RETRIEVE_A_LIST_OF_ALL_CHARGES_APPLICABLE_TO_A_POLICY));
        });
  }

  public static Performable getPaymentPrioritySetting() {
    return Task.where(
        "{0} Get payment priority setting",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GET_PAYMENT_PRIORITY_SETTING));
        });
  }

  public static Performable setPaymentPrioritySettings(Object body) {
    return Task.where(
        "{0} Set payment priority settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .put(as(actor).toEndpoint(SET_PAYMENT_PRIORITY_SETTINGS));
        });
  }

  public static Performable getThresholdSettings() {
    return Task.where(
        "{0} Get threshold settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GET_THRESHOLD_SETTINGS));
        });
  }

  public static Performable setThresholdSettings(Object body) {
    return Task.where(
        "{0} Set threshold settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .put(as(actor).toEndpoint(SET_THRESHOLD_SETTINGS));
        });
  }

  public static Performable createCharge(Object body) {
    return Task.where(
        "{0} Create Charge",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .post(as(actor).toEndpoint(CREATE_CHARGE));
        });
  }

  public static Performable getLateFeesSettings() {
    return Task.where(
        "{0} Get late fees settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GET_LATE_FEES_SETTINGS));
        });
  }

  public static Performable setLateFeeSettings(Object body) {
    return Task.where(
        "{0} Set late fee settings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .put(as(actor).toEndpoint(SET_LATE_FEE_SETTINGS));
        });
  }
}
