package com.vertafore.test.tasks.servicewrappers.notification;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:57 */
public class UseNotificationServiceTo {

  private static final String THIS_SERVICE = "notification";

  public static Performable deleteEmailAddressUsingDeleteOnTheUserPreferencesController(String id) {
    return Task.where(
        "{0} Delete email address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("email-addresses/{id}");
        });
  }

  public static Performable patchMessageUsingPatchOnTheEmailMessageController(
      String id, Object body) {
    return Task.where(
        "{0} Update a EmailMessage identified by the provided id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .patch("email-messages/{id}");
        });
  }

  public static Performable getEmailAddressUsingGetOnTheUserPreferencesController(String id) {
    return Task.where(
        "{0} Get an email address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("email-addresses/{id}");
        });
  }

  public static Performable getEmailStatusUsingGetOnTheEmailController(String id) {
    return Task.where(
        "{0} Get email status",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("emails/{id}");
        });
  }

  public static Performable validateVerificationCodeUsingPutOnTheUserPreferencesController(
      String id, Object body) {
    return Task.where(
        "{0} Validate verification code for email address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("email-addresses/{id}/verification-code");
        });
  }

  public static Performable findAllEmailAddressesUsingGetOnTheUserPreferencesController(
      String pageSize, String page) {
    return Task.where(
        "{0} Get all email addresses",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("email-addresses");
        });
  }

  public static Performable generateVerificationCodeAndEmailUsingPostOnTheUserPreferencesController(
      String id) {
    return Task.where(
        "{0} Generate verification code for email address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .post("email-addresses/{id}/verification-code");
        });
  }

  public static Performable syncEmailFoldersUsingPostOnTheEmailMessageController() {
    return Task.where(
        "{0} Sync email folder by folderId",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .post("sync-folder/sync");
        });
  }

  public static Performable getFoldersUsingGetOnTheEmailMessageController() {
    return Task.where(
        "{0} Get User's Primary Email Folders",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("email-folders");
        });
  }

  public static Performable getAllChannelsUsingGetOnTheChannelController(
      String includeInactive, String pageSize, String page) {
    return Task.where(
        "{0} Get all channels",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("includeInactive", includeInactive)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("channels");
        });
  }

  public static Performable selectSyncFolderUsingPutOnTheEmailMessageController(Object body) {
    return Task.where(
        "{0} Upsert User's selected Folder for their Primary Email to be used by Mailroom",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .put("sync-folder");
        });
  }

  public static Performable deleteMessageByIdUsingDeleteOnTheEmailMessageController(String id) {
    return Task.where(
        "{0} Delete a Message identified by the provided id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("email-messages/{id}");
        });
  }

  public static Performable createEmailAddressUsingPostOnTheUserPreferencesController(Object body) {
    return Task.where(
        "{0} Create an email address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("email-addresses");
        });
  }

  public static Performable
      findAllVerifiedAndActiveEmailAddressesUsingGetOnTheUserPreferencesController(
          String pageSize, String page) {
    return Task.where(
        "{0} Get all verified and active email addresses",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("email-addresses/active");
        });
  }

  public static Performable getSelectedSyncFolderUsingGetOnTheEmailMessageController() {
    return Task.where(
        "{0} Get User's Sync Folder for their Primary Email",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("sync-folder");
        });
  }

  public static Performable processCallbackWithContextUsingPostOnTheOAuthController(
      String provider, String code, String state) {
    return Task.where(
        "{0} Process OAuth Callback",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("provider", provider)
              .queryParam("code", code)
              .queryParam("state", state)
              .post("oauth/{provider}/callback");
        });
  }

  public static Performable updateEmailAddressUsingPutOnTheUserPreferencesController(
      String id, Object body) {
    return Task.where(
        "{0} Replace email address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("email-addresses/{id}");
        });
  }

  public static Performable cancelEmailSendUsingDeleteOnTheEmailController(String id) {
    return Task.where(
        "{0} Cancel Email Send",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("emails/{id}");
        });
  }

  public static Performable refreshTokenUsingPutOnTheOAuthController(
      String provider, String baseEmailAddressId) {
    return Task.where(
        "{0} Refresh OAuth Token",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("provider", provider)
              .queryParam("baseEmailAddressId", baseEmailAddressId)
              .put("oauth/{provider}/token/refresh");
        });
  }

  public static Performable testAccountProvisioningOnTheEmailRelayAccountController() {
    return Task.where(
        "{0} Test account provisioning",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("provision-sendgrid-accounts");
        });
  }

  public static Performable getChannelUsingGetOnTheChannelController(String id) {
    return Task.where(
        "{0} Get a channel",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("channels/{id}");
        });
  }

  public static Performable deleteEmailRelayAccountUsingDeleteOnTheEmailRelayAccountController() {
    return Task.where(
        "{0} Delete email relay account relay specific data",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .delete("email-relays");
        });
  }

  public static Performable getChannelsByChannelTypeUsingGetOnTheChannelController(
      String channelType, String includeInactive, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get channels by channel type",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("channelType", channelType)
              .queryParam("includeInactive", includeInactive)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("channels?filter=byChannelType");
        });
  }

  public static Performable getAuthorizeUrlUsingGetOnTheOAuthController(String provider) {
    return Task.where(
        "{0} Get OAuth URL",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("provider", provider)
              .get("oauth/{provider}/authorize-url");
        });
  }

  public static Performable updateMessageUsingPutOnTheEmailMessageController(
      String id, Object body) {
    return Task.where(
        "{0} Update a EmailMessage identified by the provided id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("email-messages/{id}");
        });
  }

  public static Performable matchMessageUsingPostOnTheMessageController(
      String messageId, Object body) {
    return Task.where(
        "{0} Create a CustomerMatch for a Message",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("messageId", messageId)
              .body(body)
              .post("messages/{messageId}/match");
        });
  }

  public static Performable deleteSelectedSyncFolderUsingDeleteOnTheEmailMessageController() {
    return Task.where(
        "{0} Delete User's Sync Folder for their Primary Email",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .delete("sync-folder");
        });
  }

  public static Performable sendEmailUsingPostOnTheEmailController(Object body) {
    return Task.where(
        "{0} Send Email",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("emails");
        });
  }

  public static Performable searchEmailMessagesUsingGetOnTheEmailMessageController(
      String pageSize,
      String page,
      String isDescending,
      String searchTerm,
      String sortField,
      String status,
      String filterRead,
      String filter) {
    return Task.where(
        "{0} Search EmailMessages by search terms and filters",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("isDescending", isDescending)
              .queryParam("searchTerm", searchTerm)
              .queryParam("sortField", sortField)
              .queryParam("status", status)
              .queryParam("filterRead", filterRead)
              .queryParam("filter", filter)
              .get("email-messages?filter=byEmailMessages");
        });
  }

  public static Performable getMessageByIdUsingGetOnTheEmailMessageController(String id) {
    return Task.where(
        "{0} Update a EmailMessage identified by the provided id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("email-messages/{id}");
        });
  }

  public static Performable createMessageUsingPostOnTheEmailMessageController(Object body) {
    return Task.where(
        "{0} Create an EmailMessage",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("email-messages");
        });
  }

  public static Performable updateChannelUsingPutOnTheChannelController(String id, Object body) {
    return Task.where(
        "{0} Update channel",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("channels/{id}");
        });
  }
}
