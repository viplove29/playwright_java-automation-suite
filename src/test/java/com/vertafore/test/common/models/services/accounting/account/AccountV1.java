package com.vertafore.test.common.models.services.accounting.account;

import com.vertafore.test.common.models.services.accounting.accountingEnums.AccountType;
import java.time.Instant;

public class AccountV1 {

  public AccountType type;

  public Boolean isArchived;
  public Boolean debitHolding;
  public Boolean isTrust;
  public Boolean isDeferred;
  public Boolean isSystemAccount;

  public Integer code;

  public String id;
  public String label;
  public String journalId;
  public String groupId;
  public String createdBy;
  public String modifiedBy;
  public String potentialUse;

  public Instant createdOn;
  public Instant updatedOn;
  public Instant archivedOn;
}
