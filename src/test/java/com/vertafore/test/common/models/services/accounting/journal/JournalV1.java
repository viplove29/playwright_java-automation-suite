package com.vertafore.test.common.models.services.accounting.journal;

import com.vertafore.test.common.models.services.accounting.accountingEnums.JournalType;
import java.time.Instant;

public class JournalV1 {

  public Integer fiscalMonth;
  public String id;
  public String label;
  public JournalType journalType;
  public Instant createdOn;
  public String createdBy;
  public Instant updatedOn;
  public String modifiedBy;

  public static JournalV1 getGenericJournal() {
    JournalV1 journal = new JournalV1();
    journal.journalType = JournalType.ACCRUAL;
    journal.label = "generic journal label";
    journal.fiscalMonth = 5;
    return journal;
  }
}
