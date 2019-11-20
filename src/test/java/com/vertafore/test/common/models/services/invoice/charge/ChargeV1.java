package com.vertafore.test.common.models.services.invoice.charge;

import com.vertafore.test.common.models.general.ContextualIdV1;

import java.time.Instant;

public class ChargeV1 {

    public String id;
    public String title;
    public String paidTo;
    public ContextualIdV1 creditAccountId;
    public ContextualIdV1 debitAccountId;
    public ChargeAmountV1 amount;
    public String priority;
    public String commissionType;
    public String billType;
    public String status;
    public Instant createdOn;
    public Instant updatedOn;

}
