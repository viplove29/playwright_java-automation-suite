package com.vertafore.test.common.models.services.customer.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeServiceMemberV1 {

    public String authUserId;
    public String tenantId;
    public String entityId;
    public String memberType;

}
