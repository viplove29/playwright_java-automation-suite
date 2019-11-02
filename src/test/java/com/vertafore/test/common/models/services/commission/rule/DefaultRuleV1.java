package com.vertafore.test.common.models.services.commission.rule;

import com.vertafore.test.common.models.services.commission.commissionEnums.CommissionType;
import java.time.Instant;

public class DefaultRuleV1 {
  public double newPolicyCommissionRate;
  public double renewPolicyCommissionRate;
  public double rewritePolicyCommissionRate;
  public CommissionType type;
  public Instant createdOn;
  public Instant updatedOn;
}
