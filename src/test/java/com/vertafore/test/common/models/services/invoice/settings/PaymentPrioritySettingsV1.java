package com.vertafore.test.common.models.services.invoice.settings;

import com.vertafore.test.common.models.services.invoice.invoiceEnums.PaymentPrioritySettingsPaymentPriority;
import org.joda.time.Instant;

public class PaymentPrioritySettingsV1 {

  public PaymentPrioritySettingsPaymentPriority paymentPriority;
  public Instant createdOn;
  public Instant updatedOn;
}
