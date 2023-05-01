package com.vertafore.test.services.invoices;

import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.actor.TokenSuperClass;
import com.vertafore.test.servicewrappers.UseInvoicesTo;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import org.junit.Test;

public class GET_InvoicesDirectBillInvoiceCollection extends TokenSuperClass {

  @Test
  public void getInvoicesDirectBillInvoiceCollectionBaselineTest() {

    Actor AADM_User = theActorCalled("AADM_User");
    Actor ORAN_App = theActorCalled("ORAN_App");
    Actor VADM_Admin = theActorCalled("VADM_Admin");

    UseInvoicesTo invoiceApi = new UseInvoicesTo();
    String collectionId = "00000000-0000-0000-0000-000000000000";

    AADM_User.attemptsTo(
        invoiceApi.GETInvoicesDirectBillInvoiceCollectionOnTheInvoicesController(collectionId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    ORAN_App.attemptsTo(
        invoiceApi.GETInvoicesDirectBillInvoiceCollectionOnTheInvoicesController(collectionId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    VADM_Admin.attemptsTo(
        invoiceApi.GETInvoicesDirectBillInvoiceCollectionOnTheInvoicesController(collectionId, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(403);
  }
}
