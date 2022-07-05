package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.BrokerResponse;
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
    brokerApi.GETBrokersOnTheBrokersController(skipOffset, topRecords, totalRecords, "");

    PagingResponseBrokerResponse pagingResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseBrokerResponse.class);

    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

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
}
