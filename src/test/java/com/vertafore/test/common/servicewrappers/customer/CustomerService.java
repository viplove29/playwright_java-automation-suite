package com.vertafore.test.common.servicewrappers.customer;

import com.vertafore.test.common.models.services.customer.customer.PersonalCustomerV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;

public class CustomerService {

  // import dependencies
  ServiceUtils serviceUtils;

  // empty constructor
  public CustomerService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // endpoints
  public static final String CREATE_CUSTOMER_URL = "/customers";
  public static final String CUSTOMER_URL = "/customers/{customerId}";

  public PersonalCustomerV1 postPersonalCustomer(PersonalCustomerV1 requestBody) {
    Response response = serviceUtils.sendPostRequest(CREATE_CUSTOMER_URL, requestBody);
    return response.getBody().jsonPath().getObject("content", PersonalCustomerV1.class);
  }
}
