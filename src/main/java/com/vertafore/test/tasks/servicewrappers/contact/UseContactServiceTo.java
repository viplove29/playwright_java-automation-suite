package com.vertafore.test.tasks.servicewrappers.contact;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:51 */
public class UseContactServiceTo {

  private static final String THIS_SERVICE = "contact";

  public static Performable updateContactUsingPatchOnTheContactController(String id, Object body) {
    return Task.where(
        "{0} Update Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("contacts/{id}");
        });
  }

  public static Performable getAllEmailsUsingGetOnTheEmailController(
      String pageSize, String page, String contactId) {
    return Task.where(
        "{0} GET all emails",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("contactId", contactId)
              .get("contacts/{contactId}/emails");
        });
  }

  public static Performable deleteEmailUsingDeleteOnTheEmailController(
      String contactId, String emailId) {
    return Task.where(
        "{0} Delete Email",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("contactId", contactId)
              .pathParam("emailId", emailId)
              .delete("contacts/{contactId}/emails/{emailId}");
        });
  }

  public static Performable updateAddressUsingPatchOnTheAddressController(
      String contactId, String addressId, Object body) {
    return Task.where(
        "{0} PATCH address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("contactId", contactId)
              .pathParam("addressId", addressId)
              .body(body)
              .patch("contacts/{contactId}/addresses/{addressId}");
        });
  }

  public static Performable createEmailUsingPostOnTheEmailController(
      Object body, String contactId) {
    return Task.where(
        "{0} Create Email",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("contactId", contactId)
              .post("contacts/{contactId}/emails");
        });
  }

  public static Performable deleteContactUsingDeleteOnTheContactController(String id) {
    return Task.where(
        "{0} Delete Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("contacts/{id}");
        });
  }

  public static Performable findContactsByContextualIdsUsingGetOnTheContactController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve many Contacts by contextualIds",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("contacts?filter=byContextualIds");
        });
  }

  public static Performable searchContactsUsingGetOnTheContactController(
      String term, String pageSize, String page, String search) {
    return Task.where(
        "{0} Retrieve Contacts that match the provided search term",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("term", term)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("search", search)
              .get("contacts?search=byTerm{&term,pageSize,page}");
        });
  }

  public static Performable getAllContactsUsingGetOnTheContactController(
      String pageSize, String page) {
    return Task.where(
        "{0} GET all contacts",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("contacts");
        });
  }

  public static Performable getSocialMediaByIdUsingGetOnTheSocialMediaController(
      String contactId, String id) {
    return Task.where(
        "{0} Get a Social Media object by ID",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("contactId", contactId)
              .pathParam("id", id)
              .get("contacts/{contactId}/socialMedia/{id}");
        });
  }

  public static Performable deleteAddressUsingDeleteOnTheAddressController(
      String contactId, String addressId) {
    return Task.where(
        "{0} Delete Address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("contactId", contactId)
              .pathParam("addressId", addressId)
              .delete("contacts/{contactId}/addresses/{addressId}");
        });
  }

  public static Performable deletePhoneNumberUsingDeleteOnThePhoneNumberController(
      String contactId, String phoneNumberId) {
    return Task.where(
        "{0} Delete PhoneNumber",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("contactId", contactId)
              .pathParam("phoneNumberId", phoneNumberId)
              .delete("contacts/{contactId}/phoneNumbers/{phoneNumberId}");
        });
  }

  public static Performable putContactUsingPutOnTheContactController(String id, Object body) {
    return Task.where(
        "{0} Replace Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("contacts/{id}");
        });
  }

  public static Performable deleteSocialMediaUsingDeleteOnTheSocialMediaController(
      String contactId, String id) {
    return Task.where(
        "{0} Delete SocialMedia",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("contactId", contactId)
              .pathParam("id", id)
              .delete("contacts/{contactId}/socialMedia/{id}");
        });
  }

  public static Performable getSocialMediaByIdsUsingGetOnTheSocialMediaController(
      String contactId, String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get a Social Media object by many IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("contactId", contactId)
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("contacts/{contactId}/socialMedia?filter=byIds");
        });
  }

  public static Performable createSocialMediaUsingPostOnTheSocialMediaController(
      Object body, String contactId) {
    return Task.where(
        "{0} Create Social Media",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("contactId", contactId)
              .post("contacts/{contactId}/socialMedia");
        });
  }

  public static Performable searchForContactsUsingGetOnTheContactController(
      String pageSize, String page, String searchTerm, String types, String filter, String scope) {
    return Task.where(
        "{0} Search for contacts",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("searchTerm", searchTerm)
              .queryParam("types", types)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("contacts?filter=byTerm&scope=descendants");
        });
  }

  public static Performable getContactUsingGetOnTheContactController(String id) {
    return Task.where(
        "{0} Get Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("contacts/{id}");
        });
  }

  public static Performable createAddressUsingPostOnTheAddressController(
      Object body, String contactId) {
    return Task.where(
        "{0} Create Address",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("contactId", contactId)
              .post("contacts/{contactId}/addresses");
        });
  }

  public static Performable createContactUsingPostOnTheContactController(Object body) {
    return Task.where(
        "{0} Create Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("contacts");
        });
  }

  public static Performable createPhoneNumberUsingPostOnThePhoneNumberController(
      Object body, String contactId) {
    return Task.where(
        "{0} Create Phone Number",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("contactId", contactId)
              .post("contacts/{contactId}/phoneNumbers");
        });
  }

  public static Performable getAllAddressesUsingGetOnTheAddressController(
      String pageSize, String page, String contactId) {
    return Task.where(
        "{0} GET all addresses",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("contactId", contactId)
              .get("contacts/{contactId}/addresses");
        });
  }

  public static Performable getAllPhoneNumbersUsingGetOnThePhoneNumberController(
      String pageSize, String page, String contactId) {
    return Task.where(
        "{0} GET all Phone Numbers",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("contactId", contactId)
              .get("contacts/{contactId}/phoneNumbers");
        });
  }

  public static Performable updatePhoneNumberUsingPatchOnThePhoneNumberController(
      String contactId, String phoneNumberId, Object body) {
    return Task.where(
        "{0} PATCH phoneNumber",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("contactId", contactId)
              .pathParam("phoneNumberId", phoneNumberId)
              .body(body)
              .patch("contacts/{contactId}/phoneNumbers/{phoneNumberId}");
        });
  }

  public static Performable updateEmailUsingPatchOnTheEmailController(
      String contactId, String emailId, Object body) {
    return Task.where(
        "{0} PATCH email",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("contactId", contactId)
              .pathParam("emailId", emailId)
              .body(body)
              .patch("contacts/{contactId}/emails/{emailId}");
        });
  }

  public static Performable updateSocialMediaUsingPatchOnTheSocialMediaController(
      String id, String contactId, Object body) {
    return Task.where(
        "{0} Update SocialMedia",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .pathParam("contactId", contactId)
              .body(body)
              .patch("contacts/{contactId}/socialMedia/{id}");
        });
  }

  public static Performable findContactsByIdsUsingGetOnTheContactController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Contact by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("contacts?filter=byIds");
        });
  }
}
