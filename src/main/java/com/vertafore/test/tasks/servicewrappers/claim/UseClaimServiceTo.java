package com.vertafore.test.tasks.servicewrappers.claim;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:35:05

*/
public class UseClaimServiceTo {

	private static final String THIS_SERVICE = "claim";
	private static final String CREATE_CLAIM_PARTY_USING_POST_ON_THE_CLAIM_PARTY_CONTROLLER = "claims/{claimId}/parties";
	private static final String GET_CLAIM_PARTIES_BY_CLAIM_ID_USING_GET_ON_THE_CLAIM_PARTY_CONTROLLER = "claims/{claimId}/parties";
	private static final String GET_CLAIMS_BY_POLICY_ID_USING_GET_ON_THE_CLAIM_CONTROLLER = "claims?filter=byPolicyId";
	private static final String DELETE_CLAIM_PARTY_USING_DELETE_ON_THE_CLAIM_PARTY_CONTROLLER = "claims/{claimId}/parties/{partyId}";
	private static final String UPDATE_CLAIM_PARTY_USING_PUT_ON_THE_CLAIM_PARTY_CONTROLLER = "claims/{claimId}/parties/{partyId}";
	private static final String GET_CLAIMS_USING_GET_ON_THE_CLAIM_CONTROLLER = "claims";
	private static final String DELETE_CLAIM_PAYMENT_USING_DELETE_ON_THE_CLAIM_PAYMENT_CONTROLLER = "claims/{claimId}/payments/{paymentId}";
	private static final String UPDATE_CLAIM_PAYMENT_USING_PUT_ON_THE_CLAIM_PAYMENT_CONTROLLER = "claims/{claimId}/payments/{paymentId}";
	private static final String GET_CLAIM_PAYMENTS_BY_CLAIM_ID_USING_GET_ON_THE_CLAIM_PAYMENT_CONTROLLER = "claims/{claimId}/payments";
	private static final String CREATE_CLAIM_USING_POST_ON_THE_CLAIM_CONTROLLER = "claims";
	private static final String CREATE_CLAIM_PAYMENT_USING_POST_ON_THE_CLAIM_PAYMENT_CONTROLLER = "claims/{claimId}/payments";
	private static final String GET_CLAIM_USING_GET_ON_THE_CLAIM_CONTROLLER = "claims/{id}";
	private static final String DELETE_CLAIM_USING_DELETE_ON_THE_CLAIM_CONTROLLER = "claims/{id}";
	private static final String UPDATE_CLAIM_USING_PUT_ON_THE_CLAIM_CONTROLLER = "claims/{id}";

	public static Performable createClaimPartyUsingPostOnTheClaimPartyController(String claimId, Object body){
		return Task.where(
		"{0} Create Claim Party", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).body(body).post(CREATE_CLAIM_PARTY_USING_POST_ON_THE_CLAIM_PARTY_CONTROLLER);
			}
		);
	}

	public static Performable getClaimPartiesByClaimIdUsingGetOnTheClaimPartyController(String claimId, String pageSize, String page){
		return Task.where(
		"{0} Get Claim Party", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_CLAIM_PARTIES_BY_CLAIM_ID_USING_GET_ON_THE_CLAIM_PARTY_CONTROLLER);
			}
		);
	}

	public static Performable getClaimsByPolicyIdUsingGetOnTheClaimController(String policyId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Claims by policyId", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("policyId", policyId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_CLAIMS_BY_POLICY_ID_USING_GET_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}

	public static Performable deleteClaimPartyUsingDeleteOnTheClaimPartyController(String claimId, String partyId){
		return Task.where(
		"{0} Delete Claim Party", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).pathParam("partyId", partyId).delete(DELETE_CLAIM_PARTY_USING_DELETE_ON_THE_CLAIM_PARTY_CONTROLLER);
			}
		);
	}

	public static Performable updateClaimPartyUsingPutOnTheClaimPartyController(String claimId, Object body, String partyId){
		return Task.where(
		"{0} Update Claim Party", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).body(body).pathParam("partyId", partyId).put(UPDATE_CLAIM_PARTY_USING_PUT_ON_THE_CLAIM_PARTY_CONTROLLER);
			}
		);
	}

	public static Performable getClaimsUsingGetOnTheClaimController(String sortField, String isDescending, String pageSize, String page, String statuses, String customerId){
		return Task.where(
		"{0} Get All Claims", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("sortField", sortField).queryParam("isDescending", isDescending).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("statuses", statuses).queryParam("customerId", customerId).get(GET_CLAIMS_USING_GET_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}

	public static Performable deleteClaimPaymentUsingDeleteOnTheClaimPaymentController(String claimId, String paymentId){
		return Task.where(
		"{0} Delete Claim Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).pathParam("paymentId", paymentId).delete(DELETE_CLAIM_PAYMENT_USING_DELETE_ON_THE_CLAIM_PAYMENT_CONTROLLER);
			}
		);
	}

	public static Performable updateClaimPaymentUsingPutOnTheClaimPaymentController(String claimId, Object body, String paymentId){
		return Task.where(
		"{0} Update Claim Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).body(body).pathParam("paymentId", paymentId).put(UPDATE_CLAIM_PAYMENT_USING_PUT_ON_THE_CLAIM_PAYMENT_CONTROLLER);
			}
		);
	}

	public static Performable getClaimPaymentsByClaimIdUsingGetOnTheClaimPaymentController(String claimId, String pageSize, String page){
		return Task.where(
		"{0} Get Claim Payments", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_CLAIM_PAYMENTS_BY_CLAIM_ID_USING_GET_ON_THE_CLAIM_PAYMENT_CONTROLLER);
			}
		);
	}

	public static Performable createClaimUsingPostOnTheClaimController(Object body){
		return Task.where(
		"{0} Create Claim", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_CLAIM_USING_POST_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}

	public static Performable createClaimPaymentUsingPostOnTheClaimPaymentController(String claimId, Object body){
		return Task.where(
		"{0} Create Claim Payment", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("claimId", claimId).body(body).post(CREATE_CLAIM_PAYMENT_USING_POST_ON_THE_CLAIM_PAYMENT_CONTROLLER);
			}
		);
	}

	public static Performable getClaimUsingGetOnTheClaimController(String id){
		return Task.where(
		"{0} Get Claim", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_CLAIM_USING_GET_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}

	public static Performable deleteClaimUsingDeleteOnTheClaimController(String id){
		return Task.where(
		"{0} Delete Claim", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_CLAIM_USING_DELETE_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}

	public static Performable updateClaimUsingPutOnTheClaimController(String id, Object body){
		return Task.where(
		"{0} Update Claim", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_CLAIM_USING_PUT_ON_THE_CLAIM_CONTROLLER);
			}
		);
	}



}
