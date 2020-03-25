package com.vertafore.test.tasks.servicewrappers.notification;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:48

*/
public class UseNotificationServiceTo {

	private static final String THIS_SERVICE = "notification";
	private static final String REFRESH_TOKEN_USING_PUT_ON_THE_O_AUTH_CONTROLLER = "oauth/{provider}/token/refresh";
	private static final String SEARCH_EMAIL_MESSAGES_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-messages?filter=byEmailMessages";
	private static final String GET_SELECTED_SYNC_FOLDER_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER = "sync-folder";
	private static final String DELETE_SELECTED_SYNC_FOLDER_USING_DELETE_ON_THE_EMAIL_MESSAGE_CONTROLLER = "sync-folder";
	private static final String SELECT_SYNC_FOLDER_USING_PUT_ON_THE_EMAIL_MESSAGE_CONTROLLER = "sync-folder";
	private static final String SYNC_EMAIL_FOLDERS_USING_POST_ON_THE_EMAIL_MESSAGE_CONTROLLER = "sync-folder/sync";
	private static final String GET_FOLDERS_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-folders";
	private static final String PROCESS_CALLBACK_WITH_CONTEXT_USING_POST_ON_THE_O_AUTH_CONTROLLER = "oauth/{provider}/callback";
	private static final String GENERATE_VERIFICATION_CODE_AND_EMAIL_USING_POST_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses/{id}/verification-code";
	private static final String VALIDATE_VERIFICATION_CODE_USING_PUT_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses/{id}/verification-code";
	private static final String PATCH_MESSAGE_USING_PATCH_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-messages/{id}";
	private static final String GET_MESSAGE_BY_ID_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-messages/{id}";
	private static final String DELETE_MESSAGE_BY_ID_USING_DELETE_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-messages/{id}";
	private static final String UPDATE_MESSAGE_USING_PUT_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-messages/{id}";
	private static final String MATCH_MESSAGE_USING_POST_ON_THE_MESSAGE_CONTROLLER = "messages/{messageId}/match";
	private static final String GET_ALL_CHANNELS_USING_GET_ON_THE_CHANNEL_CONTROLLER = "channels";
	private static final String CREATE_EMAIL_ADDRESS_USING_POST_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses";
	private static final String GET_EMAIL_ADDRESS_USING_GET_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses/{id}";
	private static final String DELETE_EMAIL_ADDRESS_USING_DELETE_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses/{id}";
	private static final String UPDATE_EMAIL_ADDRESS_USING_PUT_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses/{id}";
	private static final String GET_CHANNELS_BY_CHANNEL_TYPE_USING_GET_ON_THE_CHANNEL_CONTROLLER = "channels?filter=byChannelType";
	private static final String GET_CHANNEL_USING_GET_ON_THE_CHANNEL_CONTROLLER = "channels/{id}";
	private static final String UPDATE_CHANNEL_USING_PUT_ON_THE_CHANNEL_CONTROLLER = "channels/{id}";
	private static final String GET_EMAIL_STATUS_USING_GET_ON_THE_EMAIL_CONTROLLER = "emails/{id}";
	private static final String CANCEL_EMAIL_SEND_USING_DELETE_ON_THE_EMAIL_CONTROLLER = "emails/{id}";
	private static final String GET_AUTHORIZE_URL_USING_GET_ON_THE_O_AUTH_CONTROLLER = "oauth/{provider}/authorize-url";
	private static final String FIND_ALL_VERIFIED_AND_ACTIVE_EMAIL_ADDRESSES_USING_GET_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses/active";
	private static final String SEND_EMAIL_USING_POST_ON_THE_EMAIL_CONTROLLER = "emails";
	private static final String TEST_ACCOUNT_PROVISIONING_ON_THE_EMAIL_RELAY_ACCOUNT_CONTROLLER = "provision-sendgrid-accounts";
	private static final String DELETE_EMAIL_RELAY_ACCOUNT_USING_DELETE_ON_THE_EMAIL_RELAY_ACCOUNT_CONTROLLER = "email-relays";
	private static final String CREATE_MESSAGE_USING_POST_ON_THE_EMAIL_MESSAGE_CONTROLLER = "email-messages";
	private static final String FIND_ALL_EMAIL_ADDRESSES_USING_GET_ON_THE_USER_PREFERENCES_CONTROLLER = "email-addresses";

	public static Performable refreshTokenUsingPutOnTheOAuthController(String provider, String baseEmailAddressId){
		return Task.where(
		"{0} Refresh OAuth Token", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("provider", provider).queryParam("baseEmailAddressId", baseEmailAddressId).put(REFRESH_TOKEN_USING_PUT_ON_THE_O_AUTH_CONTROLLER);
			}
		);
	}

	public static Performable searchEmailMessagesUsingGetOnTheEmailMessageController(String pageSize, String page, String isDescending, String searchTerm, String sortField, String status, String filterRead, String filter){
		return Task.where(
		"{0} Search EmailMessages by search terms and filters", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("isDescending", isDescending).queryParam("searchTerm", searchTerm).queryParam("sortField", sortField).queryParam("status", status).queryParam("filterRead", filterRead).queryParam("filter", filter).get(SEARCH_EMAIL_MESSAGES_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable getSelectedSyncFolderUsingGetOnTheEmailMessageController(){
		return Task.where(
		"{0} Get User's Sync Folder for their Primary Email", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_SELECTED_SYNC_FOLDER_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable deleteSelectedSyncFolderUsingDeleteOnTheEmailMessageController(){
		return Task.where(
		"{0} Delete User's Sync Folder for their Primary Email", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").delete(DELETE_SELECTED_SYNC_FOLDER_USING_DELETE_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable selectSyncFolderUsingPutOnTheEmailMessageController(Object body){
		return Task.where(
		"{0} Upsert User's selected Folder for their Primary Email to be used by Mailroom", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).put(SELECT_SYNC_FOLDER_USING_PUT_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable syncEmailFoldersUsingPostOnTheEmailMessageController(){
		return Task.where(
		"{0} Sync email folder by folderId", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").post(SYNC_EMAIL_FOLDERS_USING_POST_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable getFoldersUsingGetOnTheEmailMessageController(){
		return Task.where(
		"{0} Get User's Primary Email Folders", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(GET_FOLDERS_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable processCallbackWithContextUsingPostOnTheOAuthController(String provider, String code, String state){
		return Task.where(
		"{0} Process OAuth Callback", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("provider", provider).queryParam("code", code).queryParam("state", state).post(PROCESS_CALLBACK_WITH_CONTEXT_USING_POST_ON_THE_O_AUTH_CONTROLLER);
			}
		);
	}

	public static Performable generateVerificationCodeAndEmailUsingPostOnTheUserPreferencesController(String id){
		return Task.where(
		"{0} Generate verification code for email address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).post(GENERATE_VERIFICATION_CODE_AND_EMAIL_USING_POST_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable validateVerificationCodeUsingPutOnTheUserPreferencesController(String id, Object body){
		return Task.where(
		"{0} Validate verification code for email address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(VALIDATE_VERIFICATION_CODE_USING_PUT_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable patchMessageUsingPatchOnTheEmailMessageController(String id, Object body){
		return Task.where(
		"{0} Update a EmailMessage identified by the provided id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).patch(PATCH_MESSAGE_USING_PATCH_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable getMessageByIdUsingGetOnTheEmailMessageController(String id){
		return Task.where(
		"{0} Update a EmailMessage identified by the provided id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_MESSAGE_BY_ID_USING_GET_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable deleteMessageByIdUsingDeleteOnTheEmailMessageController(String id){
		return Task.where(
		"{0} Delete a Message identified by the provided id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_MESSAGE_BY_ID_USING_DELETE_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable updateMessageUsingPutOnTheEmailMessageController(String id, Object body){
		return Task.where(
		"{0} Update a EmailMessage identified by the provided id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_MESSAGE_USING_PUT_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable matchMessageUsingPostOnTheMessageController(String messageId, Object body){
		return Task.where(
		"{0} Create a CustomerMatch for a Message", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("messageId", messageId).body(body).post(MATCH_MESSAGE_USING_POST_ON_THE_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable getAllChannelsUsingGetOnTheChannelController(String includeInactive, String pageSize, String page){
		return Task.where(
		"{0} Get all channels", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("includeInactive", includeInactive).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_ALL_CHANNELS_USING_GET_ON_THE_CHANNEL_CONTROLLER);
			}
		);
	}

	public static Performable createEmailAddressUsingPostOnTheUserPreferencesController(Object body){
		return Task.where(
		"{0} Create an email address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_EMAIL_ADDRESS_USING_POST_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable getEmailAddressUsingGetOnTheUserPreferencesController(String id){
		return Task.where(
		"{0} Get an email address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_EMAIL_ADDRESS_USING_GET_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable deleteEmailAddressUsingDeleteOnTheUserPreferencesController(String id){
		return Task.where(
		"{0} Delete email address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_EMAIL_ADDRESS_USING_DELETE_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable updateEmailAddressUsingPutOnTheUserPreferencesController(String id, Object body){
		return Task.where(
		"{0} Replace email address", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_EMAIL_ADDRESS_USING_PUT_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable getChannelsByChannelTypeUsingGetOnTheChannelController(String channelType, String includeInactive, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get channels by channel type", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("channelType", channelType).queryParam("includeInactive", includeInactive).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_CHANNELS_BY_CHANNEL_TYPE_USING_GET_ON_THE_CHANNEL_CONTROLLER);
			}
		);
	}

	public static Performable getChannelUsingGetOnTheChannelController(String id){
		return Task.where(
		"{0} Get a channel", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_CHANNEL_USING_GET_ON_THE_CHANNEL_CONTROLLER);
			}
		);
	}

	public static Performable updateChannelUsingPutOnTheChannelController(String id, Object body){
		return Task.where(
		"{0} Update channel", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_CHANNEL_USING_PUT_ON_THE_CHANNEL_CONTROLLER);
			}
		);
	}

	public static Performable getEmailStatusUsingGetOnTheEmailController(String id){
		return Task.where(
		"{0} Get email status", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_EMAIL_STATUS_USING_GET_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable cancelEmailSendUsingDeleteOnTheEmailController(String id){
		return Task.where(
		"{0} Cancel Email Send", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(CANCEL_EMAIL_SEND_USING_DELETE_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable getAuthorizeUrlUsingGetOnTheOAuthController(String provider){
		return Task.where(
		"{0} Get OAuth URL", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("provider", provider).get(GET_AUTHORIZE_URL_USING_GET_ON_THE_O_AUTH_CONTROLLER);
			}
		);
	}

	public static Performable findAllVerifiedAndActiveEmailAddressesUsingGetOnTheUserPreferencesController(String pageSize, String page){
		return Task.where(
		"{0} Get all verified and active email addresses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_ALL_VERIFIED_AND_ACTIVE_EMAIL_ADDRESSES_USING_GET_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}

	public static Performable sendEmailUsingPostOnTheEmailController(Object body){
		return Task.where(
		"{0} Send Email", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(SEND_EMAIL_USING_POST_ON_THE_EMAIL_CONTROLLER);
			}
		);
	}

	public static Performable testAccountProvisioningOnTheEmailRelayAccountController(){
		return Task.where(
		"{0} Test account provisioning", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").get(TEST_ACCOUNT_PROVISIONING_ON_THE_EMAIL_RELAY_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable deleteEmailRelayAccountUsingDeleteOnTheEmailRelayAccountController(){
		return Task.where(
		"{0} Delete email relay account relay specific data", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").delete(DELETE_EMAIL_RELAY_ACCOUNT_USING_DELETE_ON_THE_EMAIL_RELAY_ACCOUNT_CONTROLLER);
			}
		);
	}

	public static Performable createMessageUsingPostOnTheEmailMessageController(Object body){
		return Task.where(
		"{0} Create an EmailMessage", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_MESSAGE_USING_POST_ON_THE_EMAIL_MESSAGE_CONTROLLER);
			}
		);
	}

	public static Performable findAllEmailAddressesUsingGetOnTheUserPreferencesController(String pageSize, String page){
		return Task.where(
		"{0} Get all email addresses", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_ALL_EMAIL_ADDRESSES_USING_GET_ON_THE_USER_PREFERENCES_CONTROLLER);
			}
		);
	}



}
