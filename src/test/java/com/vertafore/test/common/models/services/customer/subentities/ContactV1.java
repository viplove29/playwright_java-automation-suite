package com.vertafore.test.common.models.services.customer.subentities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vertafore.test.common.models.AddressV1;
import com.vertafore.test.common.models.PhoneNumberV1;
import com.vertafore.test.common.models.services.customer.name.NameV1;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContactV1 {
    public List<AddressV1> addresses;
    public List<EmailAddressV1> emailAddressess;
    public NameV1 name;
    public List<PhoneNumberV1> phoneNumbers;
    public List<SocialMediaV1> socialMedia;
    public String type;


}
