package com.vertafore.test.common.models.services.carrier;

import com.vertafore.test.common.models.services.carrier.carrierEnums.BillingMethod;
import java.time.Instant;

public class BillingOptionV1 {
  public String id;
  public Instant createdOn;
  public Instant updatedOn;
  public String groupId;
  public String lineOfBusiness;
  public BillingMethod billingMethod;
  public Boolean cashAccountingOverride;
}
