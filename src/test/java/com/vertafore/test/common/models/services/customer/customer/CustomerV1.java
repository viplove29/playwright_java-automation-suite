package com.vertafore.test.common.models.services.customer.customer;

import com.vertafore.test.common.models.AddressV1;
import com.vertafore.test.common.models.PhoneNumberV1;
import com.vertafore.test.common.models.services.customer.subentities.ContactV1;
import com.vertafore.test.common.models.services.customer.subentities.DisqualificationV1;
import com.vertafore.test.common.models.services.customer.subentities.EmailAddressV1;
import com.vertafore.test.common.models.services.customer.subentities.EmployeeServiceMemberV1;
import com.vertafore.test.common.models.services.customer.subentities.LocationV1;
import com.vertafore.test.common.models.services.customer.subentities.NamedInsuredV1;
import com.vertafore.test.common.models.services.customer.subentities.SocialMediaV1;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public abstract class CustomerV1 {

  public List<AddressV1> addresses = new ArrayList<>();
  public List<ContactV1> contacts = new ArrayList<>();
  public String convertedBy;
  public String convertedOn;
  public Instant createdOn;
  public String currentStatus;
  public List<EmailAddressV1> emailAddresses = new ArrayList<>();
  public String entityId;
  public String followUpDate;
  public String id;
  public DisqualificationV1 disqualification;
  public String leadSource;
  public List<LocationV1> locations = new ArrayList<>();
  public List<NamedInsuredV1> namedInsureds = new ArrayList<>();
  public String notes;
  public String number;
  public List<PhoneNumberV1> phoneNumbers = new ArrayList<>();
  public EmployeeServiceMemberV1 producer;
  public List<EmployeeServiceMemberV1> servicingTeam = new ArrayList<>();
  public List<SocialMediaV1> socialMedia = new ArrayList<>();
  public String transferDate;
  public String type;
  public Instant updatedOn;
}
