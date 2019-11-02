package com.vertafore.test.common.models.services.customer.customer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vertafore.test.common.models.services.customer.name.CommercialNameV1;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommercialCustomerV1 extends CustomerV1 {

  public String businessType;
  public String descriptionOfBusiness;
  public String federalEmployerIdentificationNumber;
  public Integer fullTimeEmployees;
  public String industry;
  public String industryNaics;
  public String industrySic;
  public Integer partTimeEmployees;
  public String payroll;
  public String revenue;
  public String webURL;
  public Integer yearBusinessStarted;
  public Number percentSubcontracted;
  public CommercialNameV1 name;
}
