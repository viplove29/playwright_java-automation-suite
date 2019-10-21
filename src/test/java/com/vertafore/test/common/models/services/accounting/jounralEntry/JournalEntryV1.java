package com.vertafore.test.common.models.services.accounting.jounralEntry;

import com.vertafore.test.common.models.services.accounting.accountingEnums.JournalEntryType;
import com.vertafore.test.common.models.services.accounting.transaction.TransactionV1;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public class JournalEntryV1 {

    public String id;
    public JournalEntryType type;
    public String description;
    public String journalId;
    public String createdBy;
    public String modifiedBy;
    public Instant createdOn;
    public Instant updatedOn;
    public LocalDate postedOn;
    public List<TransactionV1> transactions;


}
