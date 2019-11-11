package com.vertafore.test.common.models.services.invoice.settings;

import com.ibm.icu.math.BigDecimal;
import org.joda.time.Instant;

public class ThresholdSettingsV1 {

  public BigDecimal refundThreshold;
  public BigDecimal invoiceThreshold;
  public Instant createdOn;
  public Instant updatedOn;
}
