package com.vertafore.test.common.models.services.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.AddressV1;
import com.vertafore.test.common.models.PhoneNumberV1;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityV1 {
  public String id;
  public String name;
  public EntityType entityType;
  public String parentEntityId;
  public ArrayList<AttributeV1> attributes;
  public String emailAddress;
  public PhoneNumberV1 phoneNumber;
  public String webAddress;
  public String zoneId;
  public AddressV1 address;

  public EntityV1() {
    attributes = new ArrayList<>();
  }
}
