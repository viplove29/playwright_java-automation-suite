package com.vertafore.test.common.models.services.accounting.accountBalance;

import java.math.BigDecimal;
import java.util.List;

public class AccountBalanceV1 {

    public String id;
    public String parentId;
    public BigDecimal credits;
    public BigDecimal debits;
    public BigDecimal balance;
    public Integer code;
    public Boolean isDebitHolding;
    public String label;
    public List<AccountBalanceV1> childAccounts;

}
