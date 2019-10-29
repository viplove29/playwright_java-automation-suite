package com.vertafore.test.common.models.services.carrier;

import com.vertafore.test.common.models.services.carrier.carrierEnums.Department;

import java.time.Instant;

public class CarrierLocationPhoneNumberV1 {

    public String id;
    public String carrierLocationId;
    public String phoneNumber;
    public String extension;
    public Boolean receivesFax;
    public Department department;
    public Boolean isPrimaryPhoneNumber;
    public Instant createdOn;
    public Instant updatedOn;

}
