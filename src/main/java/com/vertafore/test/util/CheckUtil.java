package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseChecksTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class CheckUtil {

  public static List<PendingCheckSearchResponse> getAllPendingChecks(Actor actor) {
    UseChecksTo checksAPI = new UseChecksTo();

    SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions
        pendingChecksSearchPostRequest =
            new SortedPagingRequestPendingCheckSearchPostRequestPendingCheckSortOptions();
    PendingCheckSearchPostRequest pendingChecksSearchPostRequestModel =
        new PendingCheckSearchPostRequest();
    SortOptionPendingCheckSortOptions sortOptions = new SortOptionPendingCheckSortOptions();
    sortOptions.setFieldSort("checkDate");
    sortOptions.setIsDescendingOrder(true);
    pendingChecksSearchPostRequest.setSortOption(sortOptions);
    pendingChecksSearchPostRequest.setModel(pendingChecksSearchPostRequestModel);

    actor.attemptsTo(
        checksAPI.POSTChecksPendingSearchOnTheChecksController(pendingChecksSearchPostRequest, ""));

    PagingResponsePendingCheckSearchResponse pagingResponsePendingCheckSearchResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponsePendingCheckSearchResponse.class);

    assertThat(pagingResponsePendingCheckSearchResponse).isNotNull();
    assertThat(pagingResponsePendingCheckSearchResponse.getResponse()).isNotNull();

    return pagingResponsePendingCheckSearchResponse.getResponse();
  }

  public static int getNumberOfPendingChecks(Actor actor) {
    return getAllPendingChecks(actor).size();
  }

  public static PendingCheckSearchResponse getRandomPendingCheck(Actor actor) {
    List<PendingCheckSearchResponse> pendingChecks = getAllPendingChecks(actor);
    assertThat(pendingChecks).withFailMessage("No pending checks found.").isNotEmpty();
    int randomNum = new Random().nextInt(pendingChecks.size());
    return pendingChecks.get(randomNum);
  }

  public static boolean checkWithPayeeNameIsPending(Actor actor, String payee) {
    List<PendingCheckSearchResponse> matchingPendingChecks =
        getAllPendingChecks(actor)
            .stream()
            .filter(check -> check.getCheckPayee().contains(payee))
            .collect(Collectors.toList());
    return !matchingPendingChecks.isEmpty();
  }

  public static PendingCheckSearchResponse getRandomPendingCheckWithPayeeNameContaining(
      Actor actor, String payee) {
    List<PendingCheckSearchResponse> matchingPendingChecks =
        getAllPendingChecks(actor)
            .stream()
            .filter(check -> check.getCheckPayee().contains(payee))
            .collect(Collectors.toList());
    assertThat(matchingPendingChecks)
        .withFailMessage("No pending checks found containing payee name: \"" + payee + "\".")
        .isNotEmpty();
    int randomNum = new Random().nextInt(matchingPendingChecks.size());
    return matchingPendingChecks.get(randomNum);
  }

  public static List<PendingCheckSearchResponse>
      getMultipleRandomPendingChecksWithPayeeNameContaining(
          Actor actor, int numberOfPendingChecks, String payee) {
    List<PendingCheckSearchResponse> randomPendingCheckList = new ArrayList<>();
    int pendingChecksLeft = numberOfPendingChecks;
    int tries = 0;
    while (pendingChecksLeft > 0) {
      if (tries > 10) { // prevent infinite loop caused by not enough matching checks
        break;
      }
      PendingCheckSearchResponse matchingPendingCheck =
          getRandomPendingCheckWithPayeeNameContaining(actor, payee);
      if (randomPendingCheckList
          .stream()
          .noneMatch(
              check ->
                  check
                      .getCashDisbursementId()
                      .equals(matchingPendingCheck.getCashDisbursementId()))) {
        randomPendingCheckList.add(matchingPendingCheck);
        pendingChecksLeft--;
        tries = 0;
      } else {
        tries++;
      }
    }
    assertThat(randomPendingCheckList.size())
        .withFailMessage(
            "Not enough pending checks found containing payee name: \""
                + payee
                + "\".\nNeeded "
                + numberOfPendingChecks
                + " but found "
                + randomPendingCheckList.size()
                + ".")
        .isEqualTo(numberOfPendingChecks);
    return randomPendingCheckList;
  }
}
