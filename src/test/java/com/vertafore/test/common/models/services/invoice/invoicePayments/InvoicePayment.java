package com.vertafore.test.common.models.services.invoice.invoicePayments;

import com.ibm.icu.math.BigDecimal;
import org.joda.time.Instant;

public class InvoicePayment {

    public String id;
    public String invoiceId;
    public String paymentId;
    public BigDecimal amount = new BigDecimal(0);
    public String currencyCode;
    public Instant createdOn;
    public Instant updatedOn;

}
