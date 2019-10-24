package com.vertafore.test.common.models.services.customer.name;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommercialNameV1 extends NameV1 {

    public String businessName;
    public String doingBusinessAs;

}
