package com.vertafore.test.tasks.servicewrappers.carrierupdateingestion;

import net.serenitybdd.screenplay.Performable;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:08

*/
public class UseCarrierUpdateIngestionServiceTo {

	private static final String THIS_SERVICE = "carrier-update-ingestion";

	public static Performable getProcessSettingsUsingGetOnTheSettingsController(){
		return Task.where(
		"{0} Get processing settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get("setting-groups/process");
			}
		);
	}

	public static Performable findMatchResultByNameUsingGetOnTheMatchController(String name, String sortField, String isDescending, String pageSize, String page, String filter, String status){
		return Task.where(
		"{0} Find match results by customer name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("status", status).get("matchresults?filter=byName&status=unprocessed");
			}
		);
	}

	public static Performable createCarrierTransactionUsingPostOnTheCarrierUpdateIngestionController(Object body){
		return Task.where(
		"{0} Create a CarrierTransaction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("carriertransactions");
			}
		);
	}

	public static Performable getCarrierTransactionByTransactiontypeOnTheCarrierUpdateIngestionController(String transactionType, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Carrier Transaction by TransactionType", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("transactionType", transactionType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get("carriertransactions?filter=byTransactionType");
			}
		);
	}

	public static Performable findMatchResultByCarrierTransactionIdUsingGetOnTheMatchController(String id){
		return Task.where(
		"{0} Get match result by carrier transaction ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("carriertransactions/{id}/matchresults");
			}
		);
	}

	public static Performable postMatchRequestUsingPostOnTheMatchController(Object body){
		return Task.where(
		"{0} Post Match Request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("matchrequests");
			}
		);
	}

	public static Performable setProcessingSettingsUsingPutOnTheSettingsController(Object body){
		return Task.where(
		"{0} Set processing settings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put("setting-groups/process");
			}
		);
	}

	public static Performable createPolicyFromCarrierTransactionUsingPostOnTheProcessController(String carrierTransactionId){
		return Task.where(
		"{0} Create a policy from the carrier transaction", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).post("carriertransactions/{carrierTransactionId}/policies");
			}
		);
	}

	public static Performable createCustomerFromCarrierTransactionUsingPostOnTheCarrierUpdateIngestionController(String carrierTransactionId){
		return Task.where(
		"{0} Create Customer and New Match Request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).post("carriertransactions/{carrierTransactionId}/customer/matchrequests");
			}
		);
	}

	public static Performable postBulkMatchRequestUsingPostOnTheMatchController(Object body){
		return Task.where(
		"{0} Post Bulk Match Requests", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("matchrequests/bulk");
			}
		);
	}

	public static Performable findMatchResultsByIdUsingGetOnTheMatchController(String id){
		return Task.where(
		"{0} Find match results by match result id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("matchresults/{id}");
			}
		);
	}

	public static Performable deleteCarrierTransactionUsingDeleteOnTheCarrierUpdateIngestionController(String carrierTransactionId){
		return Task.where(
		"{0} Delete Carrier Transaction by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).delete("carriertransactions/{carrierTransactionId}");
			}
		);
	}

	public static Performable findByTransactionTypeUsingGetOnTheCarrierUpdateIngestionController(String carrierTransactionId){
		return Task.where(
		"{0} Get Carrier Transaction by Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("carrierTransactionId", carrierTransactionId).get("carriertransactions/{carrierTransactionId}");
			}
		);
	}

	public static Performable createProcessRequestUsingPostOnTheProcessController(Object body){
		return Task.where(
		"{0} Create processing request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("processrequests");
			}
		);
	}

	public static Performable deleteCarrierTransactionsUsingDeleteOnTheCarrierUpdateIngestionController(String carrierTransactionIds){
		return Task.where(
		"{0} Delete Carrier Transactions by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("carrierTransactionIds", carrierTransactionIds).delete("carriertransactions");
			}
		);
	}



}
