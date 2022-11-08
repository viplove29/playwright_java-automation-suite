package com.vertafore.test.services.general_ledger;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.models.ems.GeneralLedgerAccountResponse;
import com.vertafore.test.servicewrappers.UseGeneralLedgerTo;
import java.util.List;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GET_GeneralLedgerAccounts extends TokenSuperClass {

  @Test
  public void getGeneralLedgerAccountsRetrievesAllDetailAccounts() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    boolean shouldReturnSystemAccounts = true;
    boolean shouldReturnInUseAccounts = true;
    String accountTypeFilter = "D"; // detail(D) or group(G) type accounts;
    Integer levelNumber = 4;

    UseGeneralLedgerTo generalLedgerApi = new UseGeneralLedgerTo();

    VADM_Admin.attemptsTo(
        generalLedgerApi.GETGeneralLedgerAccountsOnTheGeneralledgerController(
            shouldReturnSystemAccounts,
            shouldReturnInUseAccounts,
            accountTypeFilter,
            levelNumber,
            ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        generalLedgerApi.GETGeneralLedgerAccountsOnTheGeneralledgerController(
            shouldReturnSystemAccounts,
            shouldReturnInUseAccounts,
            accountTypeFilter,
            levelNumber,
            ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        generalLedgerApi.GETGeneralLedgerAccountsOnTheGeneralledgerController(
            shouldReturnSystemAccounts,
            shouldReturnInUseAccounts,
            accountTypeFilter,
            levelNumber,
            ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<GeneralLedgerAccountResponse> generalLedgerAccounts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", GeneralLedgerAccountResponse.class);

    assertThat(generalLedgerAccounts).isNotNull();
    assertThat(generalLedgerAccounts.size()).isGreaterThan(0);
    assertThat(generalLedgerAccounts.get(0).getGeneralLedgerNumber()).isNotNull();
    assertThat(generalLedgerAccounts.get(0).getGeneralLedgerNumber().matches("\\d{8}"))
        .isTrue(); // matches pattern of an 8-digit account number
  }

  @Test
  public void getGeneralLedgerAccountsRetrievesAllGroupAccounts() {
    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    boolean shouldReturnSystemAccounts = true;
    boolean shouldReturnInUseAccounts = true;
    String accountTypeFilter = "G"; // detail(D) or group(G) type accounts;
    Integer levelNumber = 4;

    UseGeneralLedgerTo generalLedgerApi = new UseGeneralLedgerTo();

    VADM_Admin.attemptsTo(
        generalLedgerApi.GETGeneralLedgerAccountsOnTheGeneralledgerController(
            shouldReturnSystemAccounts,
            shouldReturnInUseAccounts,
            accountTypeFilter,
            levelNumber,
            ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);

    ORAN_App.attemptsTo(
        generalLedgerApi.GETGeneralLedgerAccountsOnTheGeneralledgerController(
            shouldReturnSystemAccounts,
            shouldReturnInUseAccounts,
            accountTypeFilter,
            levelNumber,
            ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    AADM_User.attemptsTo(
        generalLedgerApi.GETGeneralLedgerAccountsOnTheGeneralledgerController(
            shouldReturnSystemAccounts,
            shouldReturnInUseAccounts,
            accountTypeFilter,
            levelNumber,
            ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<GeneralLedgerAccountResponse> generalLedgerAccounts =
        LastResponse.received()
            .answeredBy(AADM_User)
            .getBody()
            .jsonPath()
            .getList("", GeneralLedgerAccountResponse.class);

    assertThat(generalLedgerAccounts).isNotNull();
    assertThat(generalLedgerAccounts.size()).isGreaterThan(0);
    assertThat(generalLedgerAccounts.get(0).getGeneralLedgerNumber()).isNotNull();
    assertThat(generalLedgerAccounts.get(0).getGeneralLedgerNumber().matches("\\d{8}"))
        .isTrue(); // matches pattern of an 8-digit account number
  }
}
