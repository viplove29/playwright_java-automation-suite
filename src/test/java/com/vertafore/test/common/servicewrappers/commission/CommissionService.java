package com.vertafore.test.common.servicewrappers.commission;

import com.vertafore.test.common.models.services.commission.AgencyCommission.AgencyCommissionV1;
import com.vertafore.test.common.models.services.commission.commissionEnums.AgencyCommissionLineOfBusiness;
import com.vertafore.test.common.models.services.commission.producerCommission.ProducerCommissionV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;

import java.io.IOException;

public class CommissionService {

    // commission controller constants
    public final String GET_AGENCY_COMMISSION_BY_ID_AND_LOB = "/commissions/carriers/{id}/lob/{lob}";
    public final String GET_PRODUCER_COMMISSION_BY_ID = "/commissions/producers/{id}";

    private ServiceUtils serviceUtils;

    public CommissionService() throws IOException {
        serviceUtils = new ServiceUtils();
    }

    // commission API calls
    public AgencyCommissionV1 getAgencyCommissionByIdAndLOB(String id, String lob){
        String hydratedURL = hydrateURL(GET_AGENCY_COMMISSION_BY_ID_AND_LOB, "{id}", id);
        hydratedURL = hydrateURL(hydratedURL, "{lob}", lob);
        Response response = serviceUtils.sendGetRequest(hydratedURL);
        return response.getBody().jsonPath().getObject("content", AgencyCommissionV1.class);
    }

    public ProducerCommissionV1 getProducerCommissionById(String id){
        String hydratedURL = hydrateURL(GET_PRODUCER_COMMISSION_BY_ID, "{id}", id);
        Response response = serviceUtils.sendGetRequest(hydratedURL);
        return response.getBody().jsonPath().getObject("content", ProducerCommissionV1.class);
    }

    // helper methods
    private String hydrateURL(String url, String target, String value) {
        return url.replace(target, value);
    }
}
