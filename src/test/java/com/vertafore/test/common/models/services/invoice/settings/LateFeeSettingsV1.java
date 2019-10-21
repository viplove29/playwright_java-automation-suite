package com.vertafore.test.common.models.services.invoice.settings;

import com.ibm.icu.math.BigDecimal;
import com.vertafore.test.common.models.services.invoice.invoiceEnums.LateFeeSettingsCalculationType;
import org.joda.time.Instant;

public class LateFeeSettingsV1 {

    public Integer dayCount;
    public BigDecimal balance;
    public String memo;
    public LateFeeSettingsCalculationType calculationType;
    public BigDecimal fee;
    public Integer percentage;
    public Instant createdOn;
    public Instant updatedOn;

}
