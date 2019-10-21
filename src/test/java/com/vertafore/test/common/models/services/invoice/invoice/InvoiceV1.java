package com.vertafore.test.common.models.services.invoice.invoice;

import com.ibm.icu.math.BigDecimal;
import com.vertafore.test.common.models.services.invoice.invoiceEnums.InvoiceStatus;
import com.vertafore.test.common.models.services.invoice.invoiceLineItem.InvoiceLineItemV1;
import io.vavr.collection.List;
import org.joda.time.Instant;

public class InvoiceV1 {

    public String id;
    public String documentId;
    public BigDecimal amount;
    public String policyId;
    public Instant dueDate;
    public BigDecimal amountDue;
    public String notes;
    public Integer invoiceNumber;
    public String currencyCode;
    public InvoiceStatus status;
    public Instant paidOn;
    public List<InvoiceLineItemV1> lineItems;
    public Instant renderedOn;
    public Instant createdOn;
    public Instant updatedOn;

}
