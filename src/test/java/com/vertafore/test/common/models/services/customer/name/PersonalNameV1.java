package com.vertafore.test.common.models.services.customer.name;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalNameV1 extends NameV1 {

    public String familyName;
    public String givenName;
    public String honorificPrefix;
    public String honorificSuffix;
    public String middleName;
    public String preferredName;

}
