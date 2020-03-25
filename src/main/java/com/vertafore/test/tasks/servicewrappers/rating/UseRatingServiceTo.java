package com.vertafore.test.tasks.servicewrappers.rating;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:52

*/
public class UseRatingServiceTo {

	private static final String THIS_SERVICE = "rating";
	private static final String REQUEST_RATE_USING_POST_ON_THE_RATING_CONTROLLER = "rates";
	private static final String GET_RATE_RESPONSE_BY_CORRELATION_ID_AND_RATING_UNIT_ID_USING_GET_ON_THE_RATING_CONTROLLER = "rates?filter=byCorrelationIdAndRatingUnitId";
	private static final String GET_RATE_RESPONSES_BY_CORRELATION_ID_USING_GET_ON_THE_RATING_CONTROLLER = "rates?filter=byCorrelationId";

	public static Performable requestRateUsingPostOnTheRatingController(Object body){
		return Task.where(
		"{0} Request Rate", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(REQUEST_RATE_USING_POST_ON_THE_RATING_CONTROLLER);
			}
		);
	}

	public static Performable getRateResponseByCorrelationIdAndRatingUnitIdUsingGetOnTheRatingController(String correlationId, String ratingUnitId, String filter){
		return Task.where(
		"{0} Retrieve Rate Responses by Correlation ID and Rating Unit ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("correlationId", correlationId).queryParam("ratingUnitId", ratingUnitId).queryParam("filter", filter).get(GET_RATE_RESPONSE_BY_CORRELATION_ID_AND_RATING_UNIT_ID_USING_GET_ON_THE_RATING_CONTROLLER);
			}
		);
	}

	public static Performable getRateResponsesByCorrelationIdUsingGetOnTheRatingController(String correlationId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve Rate Responses by Correlation ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("correlationId", correlationId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_RATE_RESPONSES_BY_CORRELATION_ID_USING_GET_ON_THE_RATING_CONTROLLER);
			}
		);
	}



}
