package com.vertafore.test.tasks.servicewrappers.invoice;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;


public class UseInvoiceServiceTo {

	private static final String GET_INVOICE_SETTINGS_USING_GET = "setting-groups/invoices";
	private static final String SET_INVOICE_SETTINGS_USING_PUT = "setting-groups/invoices";
	private static final String GET_INVOICE_PAYMENTS_USING_GET = "invoice-payments";
	private static final String GET_INVOICE_PAYMENT_BY_IDS_USING_GET = "invoice-payments?filter=byIds{&ids,pageSize,page}";
	private static final String PATCH_CHARGE_USING_PATCH = "charges/{chargeId}";
	private static final String GET_CHARGE_USING_GET = "charges/{chargeId}";
	private static final String CREATE_INVOICE_PAYMENT_USING_POST = "invoice-payments";
	private static final String SEARCH_FOR_INVOICES_USING_GET = "invoices?filter=bySearchTerm{&searchTerm,statuses,sortField,isDescending,pageSize,page}";
	private static final String GET_INVOICE_PAYMENT_USING_GET = "invoice-payments/{id}";
	private static final String DELETE_INVOICE_PAYMENT_USING_DELETE = "invoice-payments/{id}";
	private static final String UPDATE_INVOICE_PAYMENT_USING_PUT = "invoice-payments/{id}";
	private static final String GET_INVOICE_BY_IDS_USING_GET = "invoices?filter=byIds{&ids,pageSize,page}";
	private static final String FORMAT_INVOICE_LINE_ITEM_USING_POST = "invoice-line-item";
	private static final String CREATE_INVOICE_USING_POST = "invoices";
	private static final String DELETE_CHARGE_USING_DELETE = "charges/{id}";
	private static final String GET_CHARGES_USING_GET = "charges?sortField=TITLE{&searchTerm,status,isSystemCharge,isDescending,pageSize,page}";
	private static final String GET_INVOICES_USING_GET = "invoices";
	private static final String GET_INVOICE_PAYMENTS_BY_INVOICE_ID_USING_GET = "invoices/{id}/invoice-payments";
	private static final String GET_INVOICE_USING_GET = "invoices/{id}";
	private static final String DELETE_INVOICE_USING_DELETE = "invoices/{id}";
	private static final String CREATE_CUSTOMER_INVOICE_RENDER_USING_POST = "invoices/{id}/customer-invoices";
	private static final String RETRIEVE_STORED_CUSTOMER_INVOICE_USING_GET = "invoices/{id}/customer-invoices";
	private static final String GET_BASIC_INVOICE_INFO_USING_GET = "invoices/basic-info";
	private static final String GET_APPLICABLE_POLICY_CHARGES_USING_GET = "charges/policy-charges/{policyId}";
	private static final String GET_PAYMENT_PRIORITY_SETTINGS_USING_GET = "setting-groups/payment-priority";
	private static final String SET_PAYMENT_PRIORITY_SETTINGS_USING_PUT = "setting-groups/payment-priority";
	private static final String GET_THRESHOLD_SETTINGS_USING_GET = "setting-groups/thresholds";
	private static final String SET_THRESHOLD_SETTINGS_USING_PUT = "setting-groups/thresholds";
	private static final String CREATE_CHARGE_USING_POST = "charges";
	private static final String GET_LATE_FEES_SETTINGS_USING_GET = "setting-groups/late-fees";
	private static final String SET_LATE_FEE_SETTINGS_USING_PUT = "setting-groups/late-fees";

	public static Performable getInvoiceSettingsUsingGet(){
		return Task.where(
		"{0} Get invoice settings", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(GET_INVOICE_SETTINGS_USING_GET));
			}
		);
	}

	public static Performable setInvoiceSettingsUsingPut(Object body){
		return Task.where(
		"{0} Set invoice settings", 
			actor -> {
				rest().with().contentType("application/json").body(body).put(as(actor).toEndpoint(SET_INVOICE_SETTINGS_USING_PUT));
			}
		);
	}

	public static Performable getInvoicePaymentsUsingGet(String pageSize, String page){
		return Task.where(
		"{0} Retrieve Invoice Payments for all Invoices", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_INVOICE_PAYMENTS_USING_GET));
			}
		);
	}

	public static Performable getInvoicePaymentByIdsUsingGet(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Invoice Payments by IDs", 
			actor -> {
				rest().with().contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(GET_INVOICE_PAYMENT_BY_IDS_USING_GET));
			}
		);
	}

	public static Performable patchChargeUsingPatch(String chargeId, Object body){
		return Task.where(
		"{0} Patch Charge", 
			actor -> {
				rest().with().contentType("application/json").pathParam("chargeId", chargeId).body(body).patch(as(actor).toEndpoint(PATCH_CHARGE_USING_PATCH));
			}
		);
	}

	public static Performable getChargeUsingGet(String chargeId){
		return Task.where(
		"{0} Get Charge By ID", 
			actor -> {
				rest().with().contentType("application/json").pathParam("chargeId", chargeId).get(as(actor).toEndpoint(GET_CHARGE_USING_GET));
			}
		);
	}

	public static Performable createInvoicePaymentUsingPost(Object body){
		return Task.where(
		"{0} Create Invoice Payment", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_INVOICE_PAYMENT_USING_POST));
			}
		);
	}

	public static Performable searchForInvoicesUsingGet(String searchTerm, String statuses, String sortField, String isDescending, String pageSize, String page, String filter){
		return Task.where(
		"{0} Search for Invoices", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("statuses", statuses).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(SEARCH_FOR_INVOICES_USING_GET));
			}
		);
	}

	public static Performable getInvoicePaymentUsingGet(String id){
		return Task.where(
		"{0} Retrieve Invoice Payment", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_INVOICE_PAYMENT_USING_GET));
			}
		);
	}

	public static Performable deleteInvoicePaymentUsingDelete(String id){
		return Task.where(
		"{0} Delete Invoice Payment", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_INVOICE_PAYMENT_USING_DELETE));
			}
		);
	}

	public static Performable updateInvoicePaymentUsingPut(String id, Object body){
		return Task.where(
		"{0} Replace Invoice Payment", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_INVOICE_PAYMENT_USING_PUT));
			}
		);
	}

	public static Performable getInvoiceByIdsUsingGet(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Invoices by IDs", 
			actor -> {
				rest().with().contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(GET_INVOICE_BY_IDS_USING_GET));
			}
		);
	}

	public static Performable formatInvoiceLineItemUsingPost(Object body){
		return Task.where(
		"{0} Provides logic to specially format an invoice line item.", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(FORMAT_INVOICE_LINE_ITEM_USING_POST));
			}
		);
	}

	public static Performable createInvoiceUsingPost(Object body){
		return Task.where(
		"{0} Create Invoice", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_INVOICE_USING_POST));
			}
		);
	}

	public static Performable deleteChargeUsingDelete(String id){
		return Task.where(
		"{0} Delete Charge", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_CHARGE_USING_DELETE));
			}
		);
	}

	public static Performable getChargesUsingGet(String searchTerm, String status, String isSystemCharge, String sortField, String isDescending, String pageSize, String page){
		return Task.where(
		"{0} Retrieve a list of all Charges", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("status", status).queryParam("isSystemCharge", isSystemCharge).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_CHARGES_USING_GET));
			}
		);
	}

	public static Performable getInvoicesUsingGet(String pageSize, String page){
		return Task.where(
		"{0} Get all Invoices", 
			actor -> {
				rest().with().contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_INVOICES_USING_GET));
			}
		);
	}

	public static Performable getInvoicePaymentsByInvoiceIdUsingGet(String id, String pageSize, String page){
		return Task.where(
		"{0} Retrieve Invoice Payments for Invoice", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_INVOICE_PAYMENTS_BY_INVOICE_ID_USING_GET));
			}
		);
	}

	public static Performable getInvoiceUsingGet(String id){
		return Task.where(
		"{0} Get Invoice by ID", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_INVOICE_USING_GET));
			}
		);
	}

	public static Performable deleteInvoiceUsingDelete(String id){
		return Task.where(
		"{0} Delete invoice", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_INVOICE_USING_DELETE));
			}
		);
	}

	public static Performable createCustomerInvoiceRenderUsingPost(String id){
		return Task.where(
		"{0} Generate Customer Invoice", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).post(as(actor).toEndpoint(CREATE_CUSTOMER_INVOICE_RENDER_USING_POST));
			}
		);
	}

	public static Performable retrieveStoredCustomerInvoiceUsingGet(String id){
		return Task.where(
		"{0} Retrieve Customer Invoice", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(RETRIEVE_STORED_CUSTOMER_INVOICE_USING_GET));
			}
		);
	}

	public static Performable getBasicInvoiceInfoUsingGet(String policyId){
		return Task.where(
		"{0} Generate basic invoice info", 
			actor -> {
				rest().with().contentType("application/json").queryParam("policyId", policyId).get(as(actor).toEndpoint(GET_BASIC_INVOICE_INFO_USING_GET));
			}
		);
	}

	public static Performable getApplicablePolicyChargesUsingGet(String policyId, String pageSize, String page){
		return Task.where(
		"{0} Retrieve a list of all Charges applicable to a Policy", 
			actor -> {
				rest().with().contentType("application/json").pathParam("policyId", policyId).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_APPLICABLE_POLICY_CHARGES_USING_GET));
			}
		);
	}

	public static Performable getPaymentPrioritySettingsUsingGet(){
		return Task.where(
		"{0} Get payment priority setting", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(GET_PAYMENT_PRIORITY_SETTINGS_USING_GET));
			}
		);
	}

	public static Performable setPaymentPrioritySettingsUsingPut(Object body){
		return Task.where(
		"{0} Set payment priority settings", 
			actor -> {
				rest().with().contentType("application/json").body(body).put(as(actor).toEndpoint(SET_PAYMENT_PRIORITY_SETTINGS_USING_PUT));
			}
		);
	}

	public static Performable getThresholdSettingsUsingGet(){
		return Task.where(
		"{0} Get threshold settings", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(GET_THRESHOLD_SETTINGS_USING_GET));
			}
		);
	}

	public static Performable setThresholdSettingsUsingPut(Object body){
		return Task.where(
		"{0} Set threshold settings", 
			actor -> {
				rest().with().contentType("application/json").body(body).put(as(actor).toEndpoint(SET_THRESHOLD_SETTINGS_USING_PUT));
			}
		);
	}

	public static Performable createChargeUsingPost(Object body){
		return Task.where(
		"{0} Create Charge", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_CHARGE_USING_POST));
			}
		);
	}

	public static Performable getLateFeesSettingsUsingGet(){
		return Task.where(
		"{0} Get late fees settings", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(GET_LATE_FEES_SETTINGS_USING_GET));
			}
		);
	}

	public static Performable setLateFeeSettingsUsingPut(Object body){
		return Task.where(
		"{0} Set late fee settings", 
			actor -> {
				rest().with().contentType("application/json").body(body).put(as(actor).toEndpoint(SET_LATE_FEE_SETTINGS_USING_PUT));
			}
		);
	}

}
