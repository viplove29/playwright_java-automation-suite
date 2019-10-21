package com.vertafore.test.common.models.services.accounting.transaction;

import com.vertafore.test.common.models.services.accounting.accountingEnums.JournalEntryType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class TransactionV1 {

    public String id;
    public String journalEntryId;
    public String accountId;
    public String journalId;
    public Integer accountCode;
    public String accountName;
    public BigDecimal amount;
    public String description;
    public String memo;
    public boolean isDebit;
    public JournalEntryType journalEntryType;
    public Integer transactionNumber;
    public Instant reconciled;
    public String createdBy;
    public String modifiedBy;
    public Instant createdOn;
    public Instant updatedOn;
    public LocalDate postedOn;

}
