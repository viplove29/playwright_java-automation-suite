package com.vertafore.test.common.servicewrappers.customer;

import com.vertafore.test.common.util.ServiceUtils;
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
}
