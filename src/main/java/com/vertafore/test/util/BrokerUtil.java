package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.BrokerResponse;
import com.vertafore.test.models.ems.BrokersSearchPostRequest;
import com.vertafore.test.models.ems.PagingRequestBrokersSearchPostRequest;
import com.vertafore.test.models.ems.PagingResponseBrokerResponse;
import com.vertafore.test.servicewrappers.UseBrokersTo;
import java.util.List;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class BrokerUtil {
  private static UseBrokersTo brokerApi = new UseBrokersTo();

  public static List<BrokerResponse> getAllBrokers(Actor actor) {
    int skipOffset = 0;
    int topRecords = 1000;
    int totalRecords = 1000;
    actor.attemptsTo(
        brokerApi.GETBrokersOnTheBrokersController(skipOffset, topRecords, totalRecords, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseBrokerResponse pagingResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBrokerResponse.class);

    if (pagingResponse != null) {
      return pagingResponse.getResponse();
    }

    return null;
  }

  public static List<BrokerResponse> getActiveBrokers(Actor actor) {
    PagingRequestBrokersSearchPostRequest pagingBrokerRequest =
        new PagingRequestBrokersSearchPostRequest();
    BrokersSearchPostRequest brokerSearchRequest = new BrokersSearchPostRequest();
    brokerSearchRequest.setStatus("A");
    brokerSearchRequest.setIncludeHidden(false);
    pagingBrokerRequest.setModel(brokerSearchRequest);

    actor.attemptsTo(brokerApi.POSTBrokersSearchOnTheBrokersController(pagingBrokerRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    PagingResponseBrokerResponse pagingResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBrokerResponse.class);

    if (pagingResponse != null) {
      return pagingResponse.getResponse();
    }

    return null;
  }

  public static BrokerResponse getRandomBroker(Actor actor) {

    List<BrokerResponse> allBrokers = getAllBrokers(actor);
    if (allBrokers.size() > 0) {
      return allBrokers.get(new Random().nextInt(allBrokers.size()));
    }

    return null;
  }

  public static BrokerResponse getRandomActiveBroker(Actor actor) {

    List<BrokerResponse> allBrokers = getActiveBrokers(actor);
    if (allBrokers.size() > 0) {
      return allBrokers.get(new Random().nextInt(allBrokers.size()));
    }

    return null;
  }
}
