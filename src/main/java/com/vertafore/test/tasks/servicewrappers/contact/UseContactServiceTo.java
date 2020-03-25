package com.vertafore.test.tasks.servicewrappers.contact;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:35:08

*/
public class UseContactServiceTo {

	private static final String THIS_SERVICE = "contact";
	private static final String CREATE_CONTACT_USING_POST_ON_THE_CONTACT_CONTROLLER = "contacts";
	private static final String GET_ALL_ADDRESSES_USING_GET_ON_THE_ADDRESS_CONTROLLER = "contacts/{contactId}/addresses";
	private static final String GET_SOCIAL_MEDIA_BY_IDS_USING_GET_ON_THE_SOCIAL_MEDIA_CONTROLLER = "contacts/{contactId}/socialMedia?filter=byIds";
	private static final String GET_ALL_EMAILS_USING_GET_ON_THE_EMAIL_CONTROLLER = "contacts/{contactId}/emails";
	private static final String CREATE_ADDRESS_USING_POST_ON_THE_ADDRESS_CONTROLLER = "contacts/{contactId}/addresses";
	private static final String CREATE_PHONE_NUMBER_USING_POST_ON_THE_PHONE_NUMBER_CONTROLLER = "contacts/{contactId}/phoneNumbers";
	private static final String UPDATE_ADDRESS_USING_PATCH_ON_THE_ADDRESS_CONTROLLER = "contacts/{contactId}/addresses/{addressId}";
	private static final String DELETE_ADDRESS_USING_DELETE_ON_THE_ADDRESS_CONTROLLER = "contacts/{contactId}/addresses/{addressId}";
	private static final String SEARCH_FOR_CONTACTS_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts?filter=byTerm&scope=descendants";
	private static final String UPDATE_PHONE_NUMBER_USING_PATCH_ON_THE_PHONE_NUMBER_CONTROLLER = "contacts/{contactId}/phoneNumbers/{phoneNumberId}";
	private static final String DELETE_PHONE_NUMBER_USING_DELETE_ON_THE_PHONE_NUMBER_CONTROLLER = "contacts/{contactId}/phoneNumbers/{phoneNumberId}";
	private static final String GET_ALL_PHONE_NUMBERS_USING_GET_ON_THE_PHONE_NUMBER_CONTROLLER = "contacts/{contactId}/phoneNumbers";
	private static final String CREATE_EMAIL_USING_POST_ON_THE_EMAIL_CONTROLLER = "contacts/{contactId}/emails";
	private static final String UPDATE_EMAIL_USING_PATCH_ON_THE_EMAIL_CONTROLLER = "contacts/{contactId}/emails/{emailId}";
	private static final String DELETE_EMAIL_USING_DELETE_ON_THE_EMAIL_CONTROLLER = "contacts/{contactId}/emails/{emailId}";
	private static final String FIND_CONTACTS_BY_CONTEXTUAL_IDS_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts?filter=byContextualIds";
	private static final String GET_ALL_CONTACTS_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts";
	private static final String UPDATE_CONTACT_USING_PATCH_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String GET_CONTACT_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String DELETE_CONTACT_USING_DELETE_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String PUT_CONTACT_USING_PUT_ON_THE_CONTACT_CONTROLLER = "contacts/{id}";
	private static final String UPDATE_SOCIAL_MEDIA_USING_PATCH_ON_THE_SOCIAL_MEDIA_CONTROLLER = "contacts/{contactId}/socialMedia/{id}";
	private static final String GET_SOCIAL_MEDIA_BY_ID_USING_GET_ON_THE_SOCIAL_MEDIA_CONTROLLER = "contacts/{contactId}/socialMedia/{id}";
	private static final String DELETE_SOCIAL_MEDIA_USING_DELETE_ON_THE_SOCIAL_MEDIA_CONTROLLER = "contacts/{contactId}/socialMedia/{id}";
	private static final String CREATE_SOCIAL_MEDIA_USING_POST_ON_THE_SOCIAL_MEDIA_CONTROLLER = "contacts/{contactId}/socialMedia";
	private static final String FIND_CONTACTS_BY_IDS_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts?filter=byIds";
	private static final String SEARCH_CONTACTS_USING_GET_ON_THE_CONTACT_CONTROLLER = "contacts?search=byTerm{&term,pageSize,page}";

	public static Performable createContactUsingPostOnTheContactController(Object body){
		return Task.where(
		"{0} Create Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_CONTACT_USING_POST_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable getAllAddressesUsingGetOnTheAddressController(String pageSize, String page, String contactId){
		return Task.where(
		"{0} GET all addresses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("contactId", contactId).get(GET_ALL_ADDRESSES_USING_GET_ON_THE_ADDRESS_CONTROLLER);
			}
		);
	}

	public static Performable getSocialMediaByIdsUsingGetOnTheSocialMediaController(String contactId, String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get a Social Media object by many IDs", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("contactId", contactId).queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_SOCIAL_MEDIA_BY_IDS_USING_GET_ON_THE_SOCIAL_MEDIA_CONTROLLER);
			}
		);
	}

	public static Performable getAllEmailsUsingGetOnTheEmailController(String pageSize, String page, String contactId){
		return Task.where(
		"{0} GET all emails", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("contactId", contactId).get(GET_ALL_EMAILS_USING_GET_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable createAddressUsingPostOnTheAddressController(Object body, String contactId){
		return Task.where(
		"{0} Create Address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("contactId", contactId).post(CREATE_ADDRESS_USING_POST_ON_THE_ADDRESS_CONTROLLER);
			}
		);
	}

	public static Performable createPhoneNumberUsingPostOnThePhoneNumberController(Object body, String contactId){
		return Task.where(
		"{0} Create Phone Number", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("contactId", contactId).post(CREATE_PHONE_NUMBER_USING_POST_ON_THE_PHONE_NUMBER_CONTROLLER);
			}
		);
	}

	public static Performable updateAddressUsingPatchOnTheAddressController(String contactId, String addressId, Object body){
		return Task.where(
		"{0} PATCH address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("contactId", contactId).pathParam("addressId", addressId).body(body).patch(UPDATE_ADDRESS_USING_PATCH_ON_THE_ADDRESS_CONTROLLER);
			}
		);
	}

	public static Performable deleteAddressUsingDeleteOnTheAddressController(String contactId, String addressId){
		return Task.where(
		"{0} Delete Address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("contactId", contactId).pathParam("addressId", addressId).delete(DELETE_ADDRESS_USING_DELETE_ON_THE_ADDRESS_CONTROLLER);
			}
		);
	}

	public static Performable searchForContactsUsingGetOnTheContactController(String pageSize, String page, String searchTerm, String types, String filter, String scope){
		return Task.where(
		"{0} Search for contacts", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("searchTerm", searchTerm).queryParam("types", types).queryParam("filter", filter).queryParam("scope", scope).get(SEARCH_FOR_CONTACTS_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable updatePhoneNumberUsingPatchOnThePhoneNumberController(String contactId, String phoneNumberId, Object body){
		return Task.where(
		"{0} PATCH phoneNumber", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("contactId", contactId).pathParam("phoneNumberId", phoneNumberId).body(body).patch(UPDATE_PHONE_NUMBER_USING_PATCH_ON_THE_PHONE_NUMBER_CONTROLLER);
			}
		);
	}

	public static Performable deletePhoneNumberUsingDeleteOnThePhoneNumberController(String contactId, String phoneNumberId){
		return Task.where(
		"{0} Delete PhoneNumber", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("contactId", contactId).pathParam("phoneNumberId", phoneNumberId).delete(DELETE_PHONE_NUMBER_USING_DELETE_ON_THE_PHONE_NUMBER_CONTROLLER);
			}
		);
	}

	public static Performable getAllPhoneNumbersUsingGetOnThePhoneNumberController(String pageSize, String page, String contactId){
		return Task.where(
		"{0} GET all Phone Numbers", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).pathParam("contactId", contactId).get(GET_ALL_PHONE_NUMBERS_USING_GET_ON_THE_PHONE_NUMBER_CONTROLLER);
			}
		);
	}

	public static Performable createEmailUsingPostOnTheEmailController(Object body, String contactId){
		return Task.where(
		"{0} Create Email", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("contactId", contactId).post(CREATE_EMAIL_USING_POST_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable updateEmailUsingPatchOnTheEmailController(String contactId, String emailId, Object body){
		return Task.where(
		"{0} PATCH email", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("contactId", contactId).pathParam("emailId", emailId).body(body).patch(UPDATE_EMAIL_USING_PATCH_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable deleteEmailUsingDeleteOnTheEmailController(String contactId, String emailId){
		return Task.where(
		"{0} Delete Email", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("contactId", contactId).pathParam("emailId", emailId).delete(DELETE_EMAIL_USING_DELETE_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable findContactsByContextualIdsUsingGetOnTheContactController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve many Contacts by contextualIds", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_CONTACTS_BY_CONTEXTUAL_IDS_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable getAllContactsUsingGetOnTheContactController(String pageSize, String page){
		return Task.where(
		"{0} GET all contacts", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_CONTACTS_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable updateContactUsingPatchOnTheContactController(String id, Object body){
		return Task.where(
		"{0} Update Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).body(body).patch(UPDATE_CONTACT_USING_PATCH_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable getContactUsingGetOnTheContactController(String id){
		return Task.where(
		"{0} Get Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_CONTACT_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable deleteContactUsingDeleteOnTheContactController(String id){
		return Task.where(
		"{0} Delete Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_CONTACT_USING_DELETE_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable putContactUsingPutOnTheContactController(String id, Object body){
		return Task.where(
		"{0} Replace Contact", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(PUT_CONTACT_USING_PUT_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable updateSocialMediaUsingPatchOnTheSocialMediaController(String id, String contactId, Object body){
		return Task.where(
		"{0} Update SocialMedia", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("id", id).pathParam("contactId", contactId).body(body).patch(UPDATE_SOCIAL_MEDIA_USING_PATCH_ON_THE_SOCIAL_MEDIA_CONTROLLER);
			}
		);
	}

	public static Performable getSocialMediaByIdUsingGetOnTheSocialMediaController(String contactId, String id){
		return Task.where(
		"{0} Get a Social Media object by ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("contactId", contactId).pathParam("id", id).get(GET_SOCIAL_MEDIA_BY_ID_USING_GET_ON_THE_SOCIAL_MEDIA_CONTROLLER);
			}
		);
	}

	public static Performable deleteSocialMediaUsingDeleteOnTheSocialMediaController(String contactId, String id){
		return Task.where(
		"{0} Delete SocialMedia", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("contactId", contactId).pathParam("id", id).delete(DELETE_SOCIAL_MEDIA_USING_DELETE_ON_THE_SOCIAL_MEDIA_CONTROLLER);
			}
		);
	}

	public static Performable createSocialMediaUsingPostOnTheSocialMediaController(Object body, String contactId){
		return Task.where(
		"{0} Create Social Media", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).pathParam("contactId", contactId).post(CREATE_SOCIAL_MEDIA_USING_POST_ON_THE_SOCIAL_MEDIA_CONTROLLER);
			}
		);
	}

	public static Performable findContactsByIdsUsingGetOnTheContactController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Contact by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(FIND_CONTACTS_BY_IDS_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}

	public static Performable searchContactsUsingGetOnTheContactController(String term, String pageSize, String page, String search){
		return Task.where(
		"{0} Retrieve Contacts that match the provided search term", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("term", term).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("search", search).get(SEARCH_CONTACTS_USING_GET_ON_THE_CONTACT_CONTROLLER);
			}
		);
	}



}
