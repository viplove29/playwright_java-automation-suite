package com.vertafore.test.common.models.services.carrier;

import com.vertafore.core.svc.model.ContextualId;
import com.vertafore.test.common.models.services.carrier.carrierEnums.Department;

import java.time.Instant;

public class BusinessContact {

    public String id;
    public Boolean isPrimaryContact;
    public String carrierLocationId;
    public ContextualId contactContextualId;
    public String titleOrDescriptor;
    public Department department;
    public Instant createdOn;
    public Instant updatedOn;

}
