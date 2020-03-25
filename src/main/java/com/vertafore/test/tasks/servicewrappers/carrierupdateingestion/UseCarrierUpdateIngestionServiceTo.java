package com.vertafore.test.tasks.servicewrappers.carrierupdateingestion;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:35:05

*/
public class UseCarrierUpdateIngestionServiceTo {

	private static final String THIS_SERVICE = "carrier-update-ingestion";
	private static final String FIND_MATCH_RESULTS_BY_ID_USING_GET_ON_THE_MATCH_CONTROLLER = "matchresults/{id}";
	private static final String CREATE_CUSTOMER_FROM_CARRIER_TRANSACTION_USING_POST_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER = "carriertransactions/{carrierTransactionId}/customer/matchrequests";
	private static final String CREATE_CARRIER_TRANSACTION_USING_POST_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER = "carriertransactions";
	private static final String GET_PROCESS_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER = "setting-groups/process";
	private static final String SET_PROCESSING_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER = "setting-groups/process";
	private static final String GET_CARRIER_TRANSACTION_BY_TRANSACTIONTYPE_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER = "carriertransactions?filter=byTransactionType";
	private static final String FIND_MATCH_RESULT_BY_CARRIER_TRANSACTION_ID_USING_GET_ON_THE_MATCH_CONTROLLER = "carriertransactions/{id}/matchresults";
	private static final String FIND_BY_TRANSACTION_TYPE_USING_GET_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER = "carriertransactions/{carrierTransactionId}";
	private static final String DELETE_CARRIER_TRANSACTION_USING_DELETE_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER = "carriertransactions/{carrierTransactionId}";
	private static final String CREATE_POLICY_FROM_CARRIER_TRANSACTION_USING_POST_ON_THE_PROCESS_CONTROLLER = "carriertransactions/{carrierTransactionId}/policies";
	private static final String DELETE_CARRIER_TRANSACTIONS_USING_DELETE_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER = "carriertransactions";
	private static final String CREATE_PROCESS_REQUEST_USING_POST_ON_THE_PROCESS_CONTROLLER = "processrequests";
	private static final String FIND_MATCH_RESULT_BY_NAME_USING_GET_ON_THE_MATCH_CONTROLLER = "matchresults?filter=byName&status=unprocessed";
	private static final String POST_MATCH_REQUEST_USING_POST_ON_THE_MATCH_CONTROLLER = "matchrequests";
	private static final String POST_BULK_MATCH_REQUEST_USING_POST_ON_THE_MATCH_CONTROLLER = "matchrequests/bulk";

	public static Performable findMatchResultsByIdUsingGetOnTheMatchController(String id){
		return Task.where(
		"{0} Find match results by match result id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(FIND_MATCH_RESULTS_BY_ID_USING_GET_ON_THE_MATCH_CONTROLLER);
			}
		);
	}

	public static Performable createCustomerFromCarrierTransactionUsingPostOnTheCarrierUpdateIngestionController(String carrierTransactionId){
		return Task.where(
		"{0} Create Customer and New Match Request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).post(CREATE_CUSTOMER_FROM_CARRIER_TRANSACTION_USING_POST_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER);
			}
		);
	}

	public static Performable createCarrierTransactionUsingPostOnTheCarrierUpdateIngestionController(Object body){
		return Task.where(
		"{0} Create a CarrierTransaction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_CARRIER_TRANSACTION_USING_POST_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER);
			}
		);
	}

	public static Performable getProcessSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get processing settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_PROCESS_SETTINGS_USING_GET_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable setProcessingSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set processing settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SET_PROCESSING_SETTINGS_USING_PUT_ON_THE_SETTINGS_CONTROLLER);
			}
		);
	}

	public static Performable getCarrierTransactionByTransactiontypeOnTheCarrierUpdateIngestionController(String transactionType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Carrier Transaction by TransactionType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("transactionType", transactionType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_CARRIER_TRANSACTION_BY_TRANSACTIONTYPE_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER);
			}
		);
	}

	public static Performable findMatchResultByCarrierTransactionIdUsingGetOnTheMatchController(String id){
		return Task.where(
		"{0} Get match result by carrier transaction ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(FIND_MATCH_RESULT_BY_CARRIER_TRANSACTION_ID_USING_GET_ON_THE_MATCH_CONTROLLER);
			}
		);
	}

	public static Performable findByTransactionTypeUsingGetOnTheCarrierUpdateIngestionController(String carrierTransactionId){
		return Task.where(
		"{0} Get Carrier Transaction by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).get(FIND_BY_TRANSACTION_TYPE_USING_GET_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER);
			}
		);
	}

	public static Performable deleteCarrierTransactionUsingDeleteOnTheCarrierUpdateIngestionController(String carrierTransactionId){
		return Task.where(
		"{0} Delete Carrier Transaction by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).delete(DELETE_CARRIER_TRANSACTION_USING_DELETE_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER);
			}
		);
	}

	public static Performable createPolicyFromCarrierTransactionUsingPostOnTheProcessController(String carrierTransactionId){
		return Task.where(
		"{0} Create a policy from the carrier transaction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).post(CREATE_POLICY_FROM_CARRIER_TRANSACTION_USING_POST_ON_THE_PROCESS_CONTROLLER);
			}
		);
	}

	public static Performable deleteCarrierTransactionsUsingDeleteOnTheCarrierUpdateIngestionController(String carrierTransactionIds){
		return Task.where(
		"{0} Delete Carrier Transactions by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("carrierTransactionIds", carrierTransactionIds).delete(DELETE_CARRIER_TRANSACTIONS_USING_DELETE_ON_THE_CARRIER_UPDATE_INGESTION_CONTROLLER);
			}
		);
	}

	public static Performable createProcessRequestUsingPostOnTheProcessController(Object body){
		return Task.where(
		"{0} Create processing request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_PROCESS_REQUEST_USING_POST_ON_THE_PROCESS_CONTROLLER);
			}
		);
	}

	public static Performable findMatchResultByNameUsingGetOnTheMatchController(String name, String sortField, String isDescending, String pageSize, String page, String filter, String status){
		return Task.where(
		"{0} Find match results by customer name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("status", status).get(FIND_MATCH_RESULT_BY_NAME_USING_GET_ON_THE_MATCH_CONTROLLER);
			}
		);
	}

	public static Performable postMatchRequestUsingPostOnTheMatchController(Object body){
		return Task.where(
		"{0} Post Match Request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(POST_MATCH_REQUEST_USING_POST_ON_THE_MATCH_CONTROLLER);
			}
		);
	}

	public static Performable postBulkMatchRequestUsingPostOnTheMatchController(Object body){
		return Task.where(
		"{0} Post Bulk Match Requests", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(POST_BULK_MATCH_REQUEST_USING_POST_ON_THE_MATCH_CONTROLLER);
			}
		);
	}



}
