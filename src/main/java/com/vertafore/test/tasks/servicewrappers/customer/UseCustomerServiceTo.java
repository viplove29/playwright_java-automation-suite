package com.vertafore.test.tasks.servicewrappers.customer;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:51 */
public class UseCustomerServiceTo {

  private static final String THIS_SERVICE = "customer";

  public static Performable createCustomerUsingPostOnTheCustomerController(Object body) {
    return Task.where(
        "{0} Create Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("customers");
        });
  }

  public static Performable getServiceTeamMembersPerAccountUsingGetOnTheServiceTeamMemberController(
      String accountId, String pageSize, String page) {
    return Task.where(
        "{0} Get a Page of ServiceTeamMembers per Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("accounts/{accountId}/service-team-members");
        });
  }

  public static Performable patchAccountUsingPatchOnTheAccountController(Object body, String id) {
    return Task.where(
        "{0} Patch Account (Customer/Lead)",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("id", id)
              .patch("accounts/{id}");
        });
  }

  public static Performable getLeadsByIdsUsingGetOnTheLeadController(
      String pageSize, String page, String ids, String filter, String scope) {
    return Task.where(
        "{0} Get Leads by IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("ids", ids)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("leads?filter=byIds&scope=descendants");
        });
  }

  public static Performable
      deleteDisqualificationReasonUsingDeleteOnTheDisqualificationReasonController(String id) {
    return Task.where(
        "{0} Delete Disqualification Reason",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("disqualification-reasons/{id}");
        });
  }

  public static Performable updateDisqualificationOnLeadUsingPutOnTheDisqualificationController(
      String leadId, Object body) {
    return Task.where(
        "{0} Update a disqualification on a Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("leadId", leadId)
              .body(body)
              .put("leads/{leadId}/disqualification");
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

  public static Performable updateServiceTeamMemberUsingPutOnTheServiceTeamMemberController(
      String id, Object body) {
    return Task.where(
        "{0} Replace a ServiceTeamMember by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("service-team-members/{id}");
        });
  }

  public static Performable getLeadSourcesByContextUsingGetOnTheLeadSourceController(
      String pageSize, String page) {
    return Task.where(
        "{0} Get lead sources",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("lead-sources");
        });
  }

  public static Performable updateCustomerUsingPutOnTheCustomerController(
      Object body, String customerId) {
    return Task.where(
        "{0} Replace Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("customerId", customerId)
              .put("customers/{customerId}");
        });
  }

  public static Performable updateLocationUsingPutOnTheLocationController(String id, Object body) {
    return Task.where(
        "{0} Replace a Location by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("locations/{id}");
        });
  }

  public static Performable updateLeadSourceUsingPutOnTheLeadSourceController(
      String id, Object body) {
    return Task.where(
        "{0} Replace lead source",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("lead-sources/{id}");
        });
  }

  public static Performable deleteLeadUsingDeleteOnTheLeadController(String leadId) {
    return Task.where(
        "{0} Delete Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("leadId", leadId)
              .delete("leads/{leadId}");
        });
  }

  public static Performable patchLeadUsingPatchOnTheLeadController(Object body, String leadId) {
    return Task.where(
        "{0} Patch Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("leadId", leadId)
              .patch("leads/{leadId}");
        });
  }

  public static Performable
      updateDisqualificationReasonUsingPutOnTheDisqualificationReasonController(
          String id, Object body) {
    return Task.where(
        "{0} Replace disqualification reason",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("disqualification-reasons/{id}");
        });
  }

  public static Performable getAccountsByIdsUsingGetOnTheAccountController(
      String pageSize, String page, String ids, String filter, String scope) {
    return Task.where(
        "{0} Get Accounts by IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("ids", ids)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("accounts?filter=byIds&scope=descendants");
        });
  }

  public static Performable createContactUsingPostOnTheContactController(
      Object body, String accountId) {
    return Task.where(
        "{0} Create Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("accountId", accountId)
              .post("accounts/{accountId}/contacts");
        });
  }

  public static Performable deleteLocationUsingDeleteOnTheLocationController(String id) {
    return Task.where(
        "{0} Delete a Location by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("locations/{id}");
        });
  }

  public static Performable convertLeadAccountToProspectUsingPostOnTheAccountController(
      String leadId) {
    return Task.where(
        "{0} Convert Lead to Prospect",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("leadId", leadId)
              .post("accounts");
        });
  }

  public static Performable getLeadsUsingGetOnTheLeadController(
      String pageSize,
      String page,
      String isDisqualified,
      String searchTerm,
      String isDescending,
      String sortField,
      String ids) {
    return Task.where(
        "{0} Get a Page of Leads",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("isDisqualified", isDisqualified)
              .queryParam("searchTerm", searchTerm)
              .queryParam("isDescending", isDescending)
              .queryParam("sortField", sortField)
              .queryParam("ids", ids)
              .get("leads");
        });
  }

  public static Performable searchLeadsUsingGetOnTheLeadController(
      String searchTerm,
      String isDescending,
      String sortField,
      String isDisqualified,
      String pageSize,
      String page,
      String filter,
      String scope) {
    return Task.where(
        "{0} Get Leads by search term",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("isDescending", isDescending)
              .queryParam("sortField", sortField)
              .queryParam("isDisqualified", isDisqualified)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("leads?filter=bySearchTerm&scope=descendants");
        });
  }

  public static Performable getLeadUsingGetOnTheLeadController(String leadId) {
    return Task.where(
        "{0} Get Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("leadId", leadId)
              .get("leads/{leadId}");
        });
  }

  public static Performable searchAccountsUsingGetOnTheAccountController(
      String searchTerm,
      String isDescending,
      String sortField,
      String isDisqualified,
      String pageSize,
      String page,
      String filter,
      String scope) {
    return Task.where(
        "{0} Get Accounts by search term",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("isDescending", isDescending)
              .queryParam("sortField", sortField)
              .queryParam("isDisqualified", isDisqualified)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("accounts?filter=bySearchTerm&scope=descendants");
        });
  }

  public static Performable convertLeadUsingPostOnTheCustomerController(String convertLead) {
    return Task.where(
        "{0} Convert Lead to Prospect",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("convertLead", convertLead)
              .post("customers");
        });
  }

  public static Performable deleteDisqualificationOnLeadUsingDeleteOnTheDisqualificationController(
      String leadId) {
    return Task.where(
        "{0} Delete a disqualification on a Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("leadId", leadId)
              .delete("leads/{leadId}/disqualification");
        });
  }

  public static Performable createRelatedAccountUsingPostOnTheRelatedAccountController(
      Object body, String accountId) {
    return Task.where(
        "{0} Add Related Account",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("accountId", accountId)
              .post("accounts/{accountId}/related-accounts");
        });
  }

  public static Performable
      patchDisqualificationReasonUsingPatchOnTheDisqualificationReasonController(
          String id, Object body) {
    return Task.where(
        "{0} Patch disqualification reason",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("disqualification-reasons/{id}");
        });
  }

  public static Performable getContactsForAccountByIdUsingGetOnTheContactController(
      String pageSize, String page, String accountId) {
    return Task.where(
        "{0} Get a page of Contacts for Account by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .pathParam("accountId", accountId)
              .get("accounts/{accountId}/contacts");
        });
  }

  public static Performable updateContactUsingPutOnTheContactController(Object body, String id) {
    return Task.where(
        "{0} Replace Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("id", id)
              .put("contacts/{id}");
        });
  }

  public static Performable getLocationsPerAccountUsingGetOnTheLocationController(
      String accountId, String pageSize, String page) {
    return Task.where(
        "{0} Get a Page of Locations per Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("accounts/{accountId}/locations");
        });
  }

  public static Performable getLocationUsingGetOnTheLocationController(String id) {
    return Task.where(
        "{0} Get a single Location by Location Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("locations/{id}");
        });
  }

  public static Performable createLeadSourceUsingPostOnTheLeadSourceController(Object body) {
    return Task.where(
        "{0} Post Lead Source",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("lead-sources");
        });
  }

  public static Performable createDisqualificationUsingPostOnTheDisqualificationController(
      String leadId, Object body) {
    return Task.where(
        "{0} Create a disqualification on a Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("leadId", leadId)
              .body(body)
              .post("leads/{leadId}/disqualification");
        });
  }

  public static Performable createLocationUsingPostOnTheLocationController(
      String accountId, Object body) {
    return Task.where(
        "{0} Post a Location per Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .body(body)
              .post("accounts/{accountId}/locations");
        });
  }

  public static Performable
      getServiceTeamMembersFilteredByMemberTypesUsingGetOnTheServiceTeamMemberController(
          String accountId, String pageSize, String page, String memberTypes, String filter) {
    return Task.where(
        "{0} Get a Page of ServiceTeamMembers per Customer, filtered by member types",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("memberTypes", memberTypes)
              .queryParam("filter", filter)
              .get("accounts/{accountId}/service-team-members?filter=byMemberTypes");
        });
  }

  public static Performable getAccountByIdUsingGetOnTheAccountController(String id) {
    return Task.where(
        "{0} Get Account (Customer/Lead)",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("accounts/{id}");
        });
  }

  public static Performable patchLeadSourceUsingPatchOnTheLeadSourceController(
      String id, Object body) {
    return Task.where(
        "{0} Patch lead source",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("lead-sources/{id}");
        });
  }

  public static Performable createAccountUsingPostOnTheAccountController(Object body) {
    return Task.where(
        "{0} Create Account (Customer/Lead)",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("accounts");
        });
  }

  public static Performable createServiceTeamMemberUsingPostOnTheServiceTeamMemberController(
      String accountId, Object body) {
    return Task.where(
        "{0} Post a ServiceTeamMember per Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .body(body)
              .post("accounts/{accountId}/service-team-members");
        });
  }

  public static Performable updateAccountUsingPutOnTheAccountController(Object body, String id) {
    return Task.where(
        "{0} Replace Account (Customer/Lead)",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("id", id)
              .put("accounts/{id}");
        });
  }

  public static Performable patchContactUsingPatchOnTheContactController(Object body, String id) {
    return Task.where(
        "{0} Patch Contact",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("id", id)
              .patch("contacts/{id}");
        });
  }

  public static Performable deleteAccountUsingDeleteOnTheAccountController(String id) {
    return Task.where(
        "{0} Delete Account (Customer/Lead)",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("accounts/{id}");
        });
  }

  public static Performable deleteRelatedAccountUsingDeleteOnTheRelatedAccountController(
      String id) {
    return Task.where(
        "{0} Delete relationship between customers",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("related-accounts/{id}");
        });
  }

  public static Performable getServiceTeamMemberUsingGetOnTheServiceTeamMemberController(
      String id) {
    return Task.where(
        "{0} Get a single ServiceTeamMember by ServiceTeamMember Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("service-team-members/{id}");
        });
  }

  public static Performable patchServiceTeamMemberUsingPatchOnTheServiceTeamMemberController(
      String id, Object body) {
    return Task.where(
        "{0} Patch a ServiceTeamMember by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("service-team-members/{id}");
        });
  }

  public static Performable getRelatedAccountsUsingGetOnTheRelatedAccountController(
      String accountId, String pageSize, String page) {
    return Task.where(
        "{0} Retrieve Related Accounts for a Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("accountId", accountId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("accounts/{accountId}/related-accounts");
        });
  }

  public static Performable getCustomerUsingGetOnTheCustomerController(String customerId) {
    return Task.where(
        "{0} Get Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("customerId", customerId)
              .get("customers/{customerId}");
        });
  }

  public static Performable updateLeadUsingPutOnTheLeadController(Object body, String leadId) {
    return Task.where(
        "{0} Replace Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .pathParam("leadId", leadId)
              .put("leads/{leadId}");
        });
  }

  public static Performable
      getDisqualificationReasonByIdUsingGetOnTheDisqualificationReasonController(String id) {
    return Task.where(
        "{0} Get a Disqualification Reason",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("disqualification-reasons/{id}");
        });
  }

  public static Performable deleteServiceTeamMemberUsingDeleteOnTheServiceTeamMemberController(
      String id) {
    return Task.where(
        "{0} Delete a ServiceTeamMember by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("service-team-members/{id}");
        });
  }

  public static Performable getLeadSourceByIdUsingGetOnTheLeadSourceController(String id) {
    return Task.where(
        "{0} Get a lead source",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("lead-sources/{id}");
        });
  }

  public static Performable getCustomersBySearchTermUsingGetOnTheCustomerController(
      String searchTerm,
      String isDescending,
      String sortField,
      String pageSize,
      String page,
      String filter,
      String scope) {
    return Task.where(
        "{0} Get Customers by search term",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("isDescending", isDescending)
              .queryParam("sortField", sortField)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("customers?filter=bySearchTerm&scope=descendants");
        });
  }

  public static Performable patchLocationUsingPatchOnTheLocationController(String id, Object body) {
    return Task.where(
        "{0} Patch a Location by Id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .pathParam("id", id)
              .body(body)
              .patch("locations/{id}");
        });
  }

  public static Performable
      getDisqualificationReasonsByContextUsingGetOnTheDisqualificationReasonController(
          String pageSize, String page) {
    return Task.where(
        "{0} Get Disqualification Reasons",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("disqualification-reasons");
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

  public static Performable createLeadUsingPostOnTheLeadController(Object body) {
    return Task.where(
        "{0} Create Lead",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("leads");
        });
  }

  public static Performable deleteLeadSourceUsingDeleteOnTheLeadSourceController(String id) {
    return Task.where(
        "{0} Delete lead source",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("lead-sources/{id}");
        });
  }

  public static Performable
      createDisqualificationReasonUsingPostOnTheDisqualificationReasonController(Object body) {
    return Task.where(
        "{0} Post Disqualification Reason",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("disqualification-reasons");
        });
  }

  public static Performable patchCustomerUsingPatchOnTheCustomerController(
      Object body, String customerId) {
    return Task.where(
        "{0} Patch Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json-patch+json")
              .body(body)
              .pathParam("customerId", customerId)
              .patch("customers/{customerId}");
        });
  }

  public static Performable deleteCustomerUsingDeleteOnTheCustomerController(String customerId) {
    return Task.where(
        "{0} Delete Customer",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("customerId", customerId)
              .delete("customers/{customerId}");
        });
  }

  public static Performable getCustomersByIdsUsingGetOnTheCustomerController(
      String pageSize, String page, String ids, String filter, String scope) {
    return Task.where(
        "{0} Get Customers by IDs",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("ids", ids)
              .queryParam("filter", filter)
              .queryParam("scope", scope)
              .get("customers?filter=byIds&scope=descendants");
        });
  }
}
