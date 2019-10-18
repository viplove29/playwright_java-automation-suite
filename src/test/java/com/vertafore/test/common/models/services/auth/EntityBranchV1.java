package com.vertafore.test.common.models.services.auth;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.AddressV1;
import com.vertafore.test.common.models.PhoneNumberV1;
import java.util.ArrayList;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityBranchV1 {
  public String id;
  public String name;
  public String parentEntityId;
  public String emailAddress;
  public PhoneNumberV1 phoneNumber;
  public String webAddress;
  public String zoneId;
  public EntityType entityType;
  public AddressV1 address;
  public Map<String, EntityV1> ancestors;
  public Map<String, EntityV1> descendants;
  public ArrayList<AttributeV1> attributes;

  public EntityBranchV1() {}
}
