package com.vertafore.test.common.models.services.carrier;

import com.vertafore.test.common.models.services.carrier.carrierEnums.CarrierLocationEmailV1;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CarrierLocationV1 {

  public String id;
  public String locationName;
  public String appointmentId;
  public Boolean isPrimaryLocation;
  public Instant createdOn;
  public Instant updatedOn;
  public CarrierLocationAddressV1 carrierLocationAddress;
  public List<CarrierLocationPhoneNumberV1> carrierLocationPhoneNumbers = new ArrayList<>();
  public List<CarrierLocationEmailV1> carrierLocationEmails = new ArrayList<>();
  public List<BusinessContact> businessContacts = new ArrayList<>();
}
