package com.vertafore.test.common.models.services.exposures;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vertafore.models.v1.*;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalAutoDriverV1 implements com.vertafore.models.v1.PersonalAutoDriverV1 {
  public String firstName;
  public String middleName;
  public String lastName;
  public String maritalStatusOption;
  public String gender;
  public String relationOption;
  public LocalDate dateOfBirth;
  public String ssnEncrypted;
  public Boolean isExcluded;
  public String phoneNumber;
  public String emailAddress;
  public Boolean shouldGetPolicyInfoByEmail;
  public String occupationOption;
  public Integer yearsAtOccupation;
  public Integer monthsAtCurrentAddress;
  public String driverLicenseNo;
  public String licenseStateCode;
  public Integer ageWhenFirstLicensed;
  public Integer yearsLicensedInState;
  public String licenseStatusOption;
  public Integer numberOfIncidentsLastSevenYears;
  public Integer mvrPoints;
  public LocalDate dateOfSuspension;
  public LocalDate dateOfRevocation;
  public Boolean doesRequireSr22;
  public String sr22CaseNumber;
  public Boolean hasCompletedDriverTrainingInLastThreeYears;
  public LocalDate completionDateAccidentPrevention;
  public String creditOption;
  public String educationLevelOption;
  public Boolean hasGoodStudentDiscount;
  public Boolean hasSeniorDiscount;
  public Boolean hasGroupDiscount;
  public String residenceTypeOption;
  public Boolean isActiveOrRetiredFromMilitary;
  public Boolean hasPriorCoverage;
  public PersonalAutoDriverPriorCoverageV1 priorCoverage;
  public String reasonForNoPriorCoverage;
  public Boolean hasCoverageCancelledLastThreeYears;
  public Integer numberOfContinuousCoverageWithoutLapse;
  public Integer numberOfLapseCoveragePriorTwelveMonths;

  public PersonalAutoDriverV1() {}

  @Override
  public EntityRelationV1 getRelationOption() {
    return null;
  }

  @Override
  public String getSsn() {
    return null;
  }

  @Override
  public Boolean getIsExcluded() {
    return null;
  }

  @Override
  public Boolean getShouldGetPolicyInfoByEmail() {
    return null;
  }

  @Override
  public OccupationV1 getOccupationOption() {
    return null;
  }

  @Override
  public Integer getYearsAtOccupation() {
    return null;
  }

  @Override
  public Integer getMonthsAtCurrentAddress() {
    return null;
  }

  @Override
  public String getDriverLicenseNo() {
    return null;
  }

  @Override
  public StateProvinceV1 getLicenseRegion() {
    return null;
  }

  @Override
  public Integer getAgeWhenFirstLicensed() {
    return null;
  }

  @Override
  public Integer getYearsLicensedInState() {
    return null;
  }

  @Override
  public DriverLicenseStatusV1 getLicenseStatusOption() {
    return null;
  }

  @Override
  public LocalDate getDateOfSuspension() {
    return null;
  }

  @Override
  public Integer getNumberOfIncidentsLastSevenYears() {
    return null;
  }

  @Override
  public Integer getMvrPoints() {
    return null;
  }

  @Override
  public Boolean getDoesRequireSr22() {
    return null;
  }

  @Override
  public String getSr22CaseNumber() {
    return null;
  }

  @Override
  public Boolean getHasCompletedDriverTrainingInLastThreeYears() {
    return null;
  }

  @Override
  public LocalDate getCompletionDateAccidentPrevention() {
    return null;
  }

  @Override
  public DriverCreditStatusV1 getCreditOption() {
    return null;
  }

  @Override
  public EducationLevelV1 getEducationLevelOption() {
    return null;
  }

  @Override
  public Boolean getHasGoodStudentDiscount() {
    return null;
  }

  @Override
  public Boolean getHasSeniorDiscount() {
    return null;
  }

  @Override
  public Boolean getHasGroupDiscount() {
    return null;
  }

  @Override
  public ResidenceTypeV1 getResidenceTypeOption() {
    return null;
  }

  @Override
  public Boolean getIsActiveOrRetiredFromMilitary() {
    return null;
  }

  @Override
  public PersonalNameV1 getName() {
    return null;
  }

  @Override
  public List<? extends AddressV1> getAddresses() {
    return null;
  }

  @Override
  public List<? extends EmailAddressV1> getEmailAddresses() {
    return null;
  }

  @Override
  public List<? extends PhoneNumberV1> getPhoneNumbers() {
    return null;
  }

  @Override
  public List<? extends SocialMediaV1> getSocialMedia() {
    return null;
  }

  @Override
  public MaritalStatusV1 getMaritalStatus() {
    return null;
  }

  @Override
  public GenderV1 getGender() {
    return null;
  }

  @Override
  public LocalDate getDateOfBirth() {
    return null;
  }
}
