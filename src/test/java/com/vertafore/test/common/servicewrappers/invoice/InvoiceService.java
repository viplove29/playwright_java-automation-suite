package com.vertafore.test.common.servicewrappers.invoice;

import com.vertafore.test.common.models.services.invoice.invoice.InvoiceV1;
import com.vertafore.test.common.models.services.invoice.invoicePayments.InvoicePaymentV1;
import com.vertafore.test.common.models.services.invoice.settings.LateFeeSettingsV1;
import com.vertafore.test.common.models.services.invoice.settings.PaymentPrioritySettingsV1;
import com.vertafore.test.common.models.services.invoice.settings.ThresholdSettingsV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;

public class InvoiceService {

  // settings controller constants
  public final String GET_LATE_FEE_SETTINGS = "/setting-groups/late-fees";
  public final String PUT_LATE_FEE_SETTINGS = "/setting-groups/late-fees";
  public final String GET_PAYMENT_PRIORITY_SETTINGS = "/setting-groups/payment-priority";
  public final String PUT_PAYMENT_PRIORITY_SETTINGS = "/setting-groups/payment-priority";
  public final String GET_THRESHOLD_SETTINGS = "/setting-groups/thresholds";
  public final String PUT_THRESHOLD_SETTINGS = "/setting-groups/thresholds";

  // invoice payments controller constants
  public final String POST_INVOICE_PAYMENT = "/invoice-payments";
  public final String GET_INVOICE_PAYMENT_BY_ID = "/invoice-payments/{id}";
  public final String PUT_INVOICE_PAYMENT_BY_ID = "/invoice-payments/{id}";
  public final String DELETE_INVOICE_PAYMENT_BY_ID = "/invoice-payments/{id}";

  // invoice controller constants
  public final String POST_INVOICE = "/invoices";
  public final String GET_INVOICE_BY_ID = "/invoices/{id}";
  public final String DELETE_INVOICE_BY_ID = "/invoices/{id}";
  public final String GET_INVOICE_PDF_BY_ID = "/invoices/{id}/pdf";
  public final String POST_INVOICE_PDF_BY_ID = "/invoices/{id}/pdf";

  private ServiceUtils serviceUtils;

  public InvoiceService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // settings API calls
  public LateFeeSettingsV1 getLateFeeSettings() {
    Response response = serviceUtils.sendGetRequest(GET_LATE_FEE_SETTINGS);
    return response.getBody().jsonPath().getObject("content", LateFeeSettingsV1.class);
  }

  public LateFeeSettingsV1 putLateFeeSettings(LateFeeSettingsV1 requestBody) {
    Response response = serviceUtils.sendPutRequest(PUT_LATE_FEE_SETTINGS, requestBody);
    return response.getBody().jsonPath().getObject("content", LateFeeSettingsV1.class);
  }

  public PaymentPrioritySettingsV1 getPaymentPrioritySettings() {
    Response response = serviceUtils.sendGetRequest(GET_PAYMENT_PRIORITY_SETTINGS);
    return response.getBody().jsonPath().getObject("content", PaymentPrioritySettingsV1.class);
  }

  public PaymentPrioritySettingsV1 putPaymentPriorityettings(
      PaymentPrioritySettingsV1 requestBody) {
    Response response = serviceUtils.sendPutRequest(PUT_PAYMENT_PRIORITY_SETTINGS, requestBody);
    return response.getBody().jsonPath().getObject("content", PaymentPrioritySettingsV1.class);
  }

  public ThresholdSettingsV1 getThresholdSettings() {
    Response response = serviceUtils.sendGetRequest(GET_THRESHOLD_SETTINGS);
    return response.getBody().jsonPath().getObject("content", ThresholdSettingsV1.class);
  }

  public ThresholdSettingsV1 putLateFeeSettings(ThresholdSettingsV1 requestBody) {
    Response response = serviceUtils.sendPutRequest(PUT_THRESHOLD_SETTINGS, requestBody);
    return response.getBody().jsonPath().getObject("content", ThresholdSettingsV1.class);
  }

  // invoice payments API calls
  public InvoicePaymentV1 getInvoicePaymentById(String id) {
    String hydratedURL = hydrateURL(GET_INVOICE_PAYMENT_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", InvoicePaymentV1.class);
  }

  public InvoicePaymentV1 postInvoicePayment(InvoicePaymentV1 requestBody) {
    Response response = serviceUtils.sendPostRequest(POST_INVOICE_PAYMENT, requestBody);
    return response.getBody().jsonPath().getObject("content", InvoicePaymentV1.class);
  }

  public InvoicePaymentV1 putInvoicePaymentById(String id, InvoicePaymentV1 requestBody) {
    String hydratedURL = hydrateURL(PUT_INVOICE_PAYMENT_BY_ID, "{id}", id);
    Response response = serviceUtils.sendPutRequest(hydratedURL, requestBody);
    return response.getBody().jsonPath().getObject("content", InvoicePaymentV1.class);
  }

  public Response deleteInvoicePaymentById(String id) {
    String hydratedURL = hydrateURL(DELETE_INVOICE_PAYMENT_BY_ID, "{id}", id);
    return serviceUtils.sendDeleteRequest(hydratedURL);
  }

  // invoice API calls
  public InvoiceV1 postInvoice(InvoiceV1 requestBody) {
    Response response = serviceUtils.sendPostRequest(POST_INVOICE, requestBody);
    return response.getBody().jsonPath().getObject("content", InvoiceV1.class);
  }

  public InvoiceV1 getInvoiceById(String id) {
    String hydratedURL = hydrateURL(GET_INVOICE_BY_ID, "{id}", id);
    Response response = serviceUtils.sendGetRequest(hydratedURL);
    return response.getBody().jsonPath().getObject("content", InvoiceV1.class);
  }

  public Response deleteInvoiceById(String id) {
    String hydratedURL = hydrateURL(DELETE_INVOICE_BY_ID, "{id}", id);
    return serviceUtils.sendGetRequest(hydratedURL);
  }

  // make get and post invoice pdfs later

  // helper methods
  private String hydrateURL(String url, String target, String value) {
    return url.replace(target, value);
  }
}
