package com.vertafore.test.common.models.services.accounting.accountGroup;

import com.vertafore.test.common.models.services.accounting.accountingEnums.AccountType;
import java.time.Instant;

public class AccountGroupV1 {

  public String id;
  public Integer code;
  public String label;
  public String parentId;
  public AccountType accountType;
  public String journalId;
  public Instant createdOn;
  public String createdBy;
  public Instant updatedOn;
  public String modifiedBy;

  public static AccountGroupV1 getGenericAccountGroup() {
    AccountGroupV1 accountGroup = new AccountGroupV1();
    accountGroup.label = "generic account group";
    accountGroup.code = 123987;
    accountGroup.accountType = AccountType.ASSET;
    return accountGroup;
  }
}
