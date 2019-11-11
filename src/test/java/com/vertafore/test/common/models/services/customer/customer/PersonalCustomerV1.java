package com.vertafore.test.common.models.services.customer.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vertafore.test.common.models.services.customer.name.PersonalNameV1;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalCustomerV1 extends CustomerV1 {

  public String dateOfBirth;
  public String gender;
  public String maritalStatus;
  public String socialSecurityNumber;
  public PersonalNameV1 name;
}
