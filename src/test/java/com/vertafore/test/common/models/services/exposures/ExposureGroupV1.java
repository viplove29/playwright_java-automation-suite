package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.test.common.models.LineOfBusinessV1;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExposureGroupV1 {
  public String id;

  public Instant createdOn;

  public Instant updatedOn;

  public List<ExposureV1> exposures = new ArrayList();

  public List<LineOfBusinessV1> linesOfBusiness = new ArrayList();

  public List<ExposureLocationV1> locations = new ArrayList();

  public List<ExposureGroupAdditionalInterestV1> additionalInterests = new ArrayList();

  public List<AdditionalInterestRelationshipV1> additionalInterestRelationships = new ArrayList();

  public ExposureGroupV1() {}
}
