package com.vertafore.test.common.models.services.carrier;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AppointmentV1 {

  public String id;
  public String entityId;
  public String carrierId;
  public Boolean serviceAgreement;
  public Boolean downloadsSetup;
  public String comments;
  public Instant createdOn;
  public Instant updatedOn;
  public List<CarrierLocationV1> carrierLocations = new ArrayList<>();
  public List<BillingOptionV1> billingOptions = new ArrayList<>();
  public String carrierPortalURL;
  public String carrierWebsite;
}
