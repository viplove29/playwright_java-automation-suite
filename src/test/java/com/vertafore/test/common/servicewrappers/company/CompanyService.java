package com.vertafore.test.common.servicewrappers.company;

import com.vertafore.test.common.models.services.compnay.CompanyV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;

public class CompanyService {

    private ServiceUtils serviceUtils;

    private final String GET_COMPANIES_BY_NAME = "/companies?filter=byName";
    private final String GET_ALL_COMPANIES = "/companies";

    public CompanyService() throws IOException {
        serviceUtils = new ServiceUtils();
    }

    public List<CompanyV1> getAllCompanies(){
        Response response = serviceUtils.sendGetRequest(GET_ALL_COMPANIES);
        return response.body().jsonPath().getList("content", CompanyV1.class);
    }
}
