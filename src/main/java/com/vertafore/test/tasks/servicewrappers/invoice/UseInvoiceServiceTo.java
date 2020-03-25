package com.vertafore.test.tasks.servicewrappers.invoice;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:46

*/
public class UseInvoiceServiceTo {

	private static final String THIS_SERVICE = "invoice";
	private static final String GET_INVOICE_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER = "setting-groups/invoices";
	private static final String SET_INVOICE_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER = "setting-groups/invoices";
	private static final String GET_PAYMENTS_BY_INVOICE_ID_USING_GET_ON_THE_PAYMENTS_CONTROLLER = "invoices/{id}/payments";
	private static final String GET_PAYMENT_USING_GET_ON_THE_PAYMENTS_CONTROLLER = "payments/{id}";
	private static final String DELETE_PAYMENT_USING_DELETE_ON_THE_PAYMENTS_CONTROLLER = "payments/{id}";
	private static final String UPDATE_PAYMENT_USING_PUT_ON_THE_PAYMENTS_CONTROLLER = "payments/{id}";
	private static final String PATCH_CHARGE_USING_PATCH_ON_THE_CHARGE_CONTROLLER = "charges/{chargeId}";
	private static final String GET_CHARGE_USING_GET_ON_THE_CHARGE_CONTROLLER = "charges/{chargeId}";
	private static final String UPDATE_CHARGE_USING_PUT_ON_THE_CHARGE_CONTROLLER = "charges/{chargeId}";
	private static final String SEARCH_FOR_INVOICES_USING_GET_ON_THE_INVOICE_CONTROLLER = "invoices?filter=bySearchTerm";
	private static final String GET_INVOICE_BY_IDS_USING_GET_ON_THE_INVOICE_CONTROLLER = "invoices?filter=byIds";
	private static final String CREATE_PAYMENT_USING_POST_ON_THE_PAYMENTS_CONTROLLER = "payments";
	private static final String FORMAT_INVOICE_LINE_ITEM_USING_POST_ON_THE_CHARGE_CONTROLLER = "invoice-line-item";
	private static final String CREATE_INVOICE_USING_POST_ON_THE_INVOICE_CONTROLLER = "invoices";
	private static final String DELETE_CHARGE_USING_DELETE_ON_THE_CHARGE_CONTROLLER = "charges/{id}";
	private static final String GET_CHARGES_USING_GET_ON_THE_CHARGE_CONTROLLER = "charges?sortField=TITLE{&searchTerm,status,isSystemCharge,isDescending,pageSize,page}";
	private static final String GET_PAYMENT_BY_IDS_USING_GET_ON_THE_PAYMENTS_CONTROLLER = "payments?filter=byIds";
	private static final String GET_INVOICES_USING_GET_ON_THE_INVOICE_CONTROLLER = "invoices";
	private static final String GET_INVOICE_USING_GET_ON_THE_INVOICE_CONTROLLER = "invoices/{id}";
	private static final String DELETE_INVOICE_USING_DELETE_ON_THE_INVOICE_CONTROLLER = "invoices/{id}";
	private static final String CREATE_CUSTOMER_INVOICE_RENDER_USING_POST_ON_THE_INVOICE_CONTROLLER = "invoices/{id}/customer-invoices";
	private static final String RETRIEVE_STORED_CUSTOMER_INVOICE_USING_GET_ON_THE_INVOICE_CONTROLLER = "invoices/{id}/customer-invoices";
	private static final String GET_BASIC_INVOICE_INFO_USING_GET_ON_THE_INVOICE_CONTROLLER = "invoices/basic-info";
	private static final String GET_APPLICABLE_POLICY_CHARGES_USING_GET_ON_THE_CHARGE_CONTROLLER = "charges/policy-charges/{policyId}";
	private static final String GET_PAYMENT_PRIORITY_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER = "setting-groups/payment-priority";
	private static final String SET_PAYMENT_PRIORITY_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER = "setting-groups/payment-priority";
	private static final String GET_THRESHOLD_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER = "setting-groups/thresholds";
	private static final String SET_THRESHOLD_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER = "setting-groups/thresholds";
	private static final String CREATE_CHARGE_USING_POST_ON_THE_CHARGE_CONTROLLER = "charges";
	private static final String GET_LATE_FEES_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER = "setting-groups/late-fees";
	private static final String SET_LATE_FEE_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER = "setting-groups/late-fees";
	private static final String GET_PAYMENTS_USING_GET_ON_THE_PAYMENTS_CONTROLLER = "payments";

	public static Performable getInvoiceSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get invoice settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_INVOICE_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable setInvoiceSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set invoice settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_INVOICE_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable getPaymentsByInvoiceIdUsingGetOnThePaymentsController(String id, String pageSize, String page){
		return Task.where(
		"{0} Retrieve Payments for Invoice", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_PAYMENTS_BY_INVOICE_ID_USING_GET_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}

	public static Performable getPaymentUsingGetOnThePaymentsController(String id){
		return Task.where(
		"{0} Retrieve Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_PAYMENT_USING_GET_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}

	public static Performable deletePaymentUsingDeleteOnThePaymentsController(String id){
		return Task.where(
		"{0} Delete Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_PAYMENT_USING_DELETE_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}

	public static Performable updatePaymentUsingPutOnThePaymentsController(String id, Object body){
		return Task.where(
		"{0} Replace Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_PAYMENT_USING_PUT_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}

	public static Performable patchChargeUsingPatchOnTheChargeController(String chargeId, Object body){
		return Task.where(
		"{0} Patch Charge", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("chargeId", chargeId).body(body).patch(PATCH_CHARGE_USING_PATCH_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable getChargeUsingGetOnTheChargeController(String chargeId){
		return Task.where(
		"{0} Get Charge By ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("chargeId", chargeId).get(GET_CHARGE_USING_GET_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable updateChargeUsingPutOnTheChargeController(String chargeId, Object body){
		return Task.where(
		"{0} Update Charge", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("chargeId", chargeId).body(body).put(UPDATE_CHARGE_USING_PUT_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable searchForInvoicesUsingGetOnTheInvoiceController(String searchTerm, String statuses, String sortField, String isDescending, String pageSize, String page, String filter){
		return Task.where(
		"{0} Search for Invoices", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("statuses", statuses).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(SEARCH_FOR_INVOICES_USING_GET_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable getInvoiceByIdsUsingGetOnTheInvoiceController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Invoices by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_INVOICE_BY_IDS_USING_GET_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable createPaymentUsingPostOnThePaymentsController(Object body){
		return Task.where(
		"{0} Create Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_PAYMENT_USING_POST_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}

	public static Performable formatInvoiceLineItemUsingPostOnTheChargeController(Object body){
		return Task.where(
		"{0} Provides logic to specially format an invoice line item.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(FORMAT_INVOICE_LINE_ITEM_USING_POST_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable createInvoiceUsingPostOnTheInvoiceController(Object body){
		return Task.where(
		"{0} Create Invoice", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_INVOICE_USING_POST_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable deleteChargeUsingDeleteOnTheChargeController(String id){
		return Task.where(
		"{0} Delete Charge", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_CHARGE_USING_DELETE_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable getChargesUsingGetOnTheChargeController(String searchTerm, String status, String isSystemCharge, String sortField, String isDescending, String pageSize, String page){
		return Task.where(
		"{0} Retrieve a list of all Charges", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("status", status).queryParam("isSystemCharge", isSystemCharge).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_CHARGES_USING_GET_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable getPaymentByIdsUsingGetOnThePaymentsController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Payments by IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PAYMENT_BY_IDS_USING_GET_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}

	public static Performable getInvoicesUsingGetOnTheInvoiceController(String pageSize, String page){
		return Task.where(
		"{0} Get all Invoices", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_INVOICES_USING_GET_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable getInvoiceUsingGetOnTheInvoiceController(String id){
		return Task.where(
		"{0} Get Invoice by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_INVOICE_USING_GET_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable deleteInvoiceUsingDeleteOnTheInvoiceController(String id){
		return Task.where(
		"{0} Delete invoice", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_INVOICE_USING_DELETE_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable createCustomerInvoiceRenderUsingPostOnTheInvoiceController(String id){
		return Task.where(
		"{0} Generate Customer Invoice", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).post(CREATE_CUSTOMER_INVOICE_RENDER_USING_POST_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable retrieveStoredCustomerInvoiceUsingGetOnTheInvoiceController(String id){
		return Task.where(
		"{0} Retrieve Customer Invoice", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(RETRIEVE_STORED_CUSTOMER_INVOICE_USING_GET_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable getBasicInvoiceInfoUsingGetOnTheInvoiceController(String policyId){
		return Task.where(
		"{0} Generate basic invoice info", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("policyId", policyId).get(GET_BASIC_INVOICE_INFO_USING_GET_ON_THE_INVOICE_CONTROLLER);
			}
		);
	}

	public static Performable getApplicablePolicyChargesUsingGetOnTheChargeController(String policyId, String pageSize, String page){
		return Task.where(
		"{0} Retrieve a list of all Charges applicable to a Policy", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("policyId", policyId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_APPLICABLE_POLICY_CHARGES_USING_GET_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable getPaymentPrioritySettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get payment priority setting", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_PAYMENT_PRIORITY_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable setPaymentPrioritySettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set payment priority settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_PAYMENT_PRIORITY_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable getThresholdSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get threshold settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_THRESHOLD_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable setThresholdSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set threshold settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_THRESHOLD_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable createChargeUsingPostOnTheChargeController(Object body){
		return Task.where(
		"{0} Create Charge", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_CHARGE_USING_POST_ON_THE_CHARGE_CONTROLLER);
			}
		);
	}

	public static Performable getLateFeesSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get late fees settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_LATE_FEES_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable setLateFeeSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set late fee settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_LATE_FEE_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable getPaymentsUsingGetOnThePaymentsController(String pageSize, String page){
		return Task.where(
		"{0} Retrieve Payments for all Invoices", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_PAYMENTS_USING_GET_ON_THE_PAYMENTS_CONTROLLER);
			}
		);
	}



}
