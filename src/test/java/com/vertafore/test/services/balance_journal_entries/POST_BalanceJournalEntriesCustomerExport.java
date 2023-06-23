package com.vertafore.test.services.balance_journal_entries;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.BusinessUnitPostRequest;
import com.vertafore.test.models.ems.ExportCustomerBeginningBalanceJournalEntriesPostRequest;
import com.vertafore.test.models.ems.PagingRequestExportCustomerBeginningBalanceJournalEntriesPostRequest;
import com.vertafore.test.servicewrappers.UseBalanceJournalEntriesTo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class POST_BalanceJournalEntriesCustomerExport extends TokenSuperClass {

  @Test
  public void postBalanceJournalEntriesCustomerExportBaselineTest() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseBalanceJournalEntriesTo balanceJournalEntriesApi = new UseBalanceJournalEntriesTo();

    PagingRequestExportCustomerBeginningBalanceJournalEntriesPostRequest exportRequest =
        new PagingRequestExportCustomerBeginningBalanceJournalEntriesPostRequest();
    exportRequest.setSkip(0);
    exportRequest.setTop(100);
    exportRequest.setTotalRecords(0);

    ExportCustomerBeginningBalanceJournalEntriesPostRequest customerRequest =
        new ExportCustomerBeginningBalanceJournalEntriesPostRequest();
    customerRequest.setExtractType("Customer");
    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    String date = dateObj.format(formatter);
    customerRequest.setEndDate(date);
    customerRequest.setTypeOfBusiness(List.of("All"));

    BusinessUnitPostRequest businessUnitPostRequest = new BusinessUnitPostRequest();
    businessUnitPostRequest.setDivisionCode("^^^");
    customerRequest.setBusinessUnits(List.of(businessUnitPostRequest));

    exportRequest.setModel(customerRequest);

    VADM_Admin.attemptsTo(
        balanceJournalEntriesApi
            .POSTBalanceJournalEntriesCustomerExportOnTheBalancejournalentriesController(
                exportRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    AADM_User.attemptsTo(
        balanceJournalEntriesApi
            .POSTBalanceJournalEntriesCustomerExportOnTheBalancejournalentriesController(
                exportRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        balanceJournalEntriesApi
            .POSTBalanceJournalEntriesCustomerExportOnTheBalancejournalentriesController(
                exportRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
  }
}
