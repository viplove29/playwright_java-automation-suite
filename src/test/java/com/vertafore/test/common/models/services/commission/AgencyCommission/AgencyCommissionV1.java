package com.vertafore.test.common.models.services.commission.AgencyCommission;

import com.vertafore.test.common.models.services.commission.commission.CommissionV1;
import com.vertafore.test.common.models.services.commission.commissionEnums.AgencyCommissionLineOfBusiness;

public class AgencyCommissionV1 extends CommissionV1 {

    public String carrierId;
    public AgencyCommissionLineOfBusiness lob;

}
