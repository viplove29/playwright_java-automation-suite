package com.vertafore.test.common.models.services.invoice.invoiceLineItem;

import com.ibm.icu.math.BigDecimal;
import org.joda.time.Instant;

public class InvoiceLineItemV1 {

  public String id;
  public String invoiceId;
  public String chargeType;
  public BigDecimal amount = BigDecimal.ZERO;
  public String description;
  public Instant createdOn;
  public Instant updatedOn;
}
