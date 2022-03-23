package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class CustomerUtil {

  // Validation helpers
  public static int customerNumber;
  public static String customerId;
  public static String divisionShortName;
  public static String branchShortName;
  public static String departmentShortName;
  public static String groupShortName;
  public static String accountExecShortName;
  public static String accountRepShortName;
  public static String customerType;
  public static String nameType;
  public static String firmName;
  public static String firstName;
  public static String middleName;
  public static String lastName;
  public static String addressLine1;
  public static String addressLine2;
  public static String city;
  public static String state;
  public static String zipCode;
  public static String county;
  public static String country;
  public static String primaryEmail;
  public static String secondaryEmail;
  public static String residencePhone;
  public static String businessPhone;
  public static String fax;
  public static String cell;
  public static String pager;
  public static String other;

  // Helper APIs and faker
  public static UseEmployeeTo employeeAPI = new UseEmployeeTo();
  public static UseCustomersTo customersAPI = new UseCustomersTo();
  public static Faker faker = new Faker();

  // Global models for the methods that can either be a put or post model depending on the request
  public static BusinessUnitShortNamePostRequest businessUnitPOST =
      new BusinessUnitShortNamePostRequest();
  public static BusinessUnitShortNamePutRequest businessUnitPUT =
      new BusinessUnitShortNamePutRequest();
  public static AgencyPersonnelPostRequest agencyPersonnelPOST = new AgencyPersonnelPostRequest();
  public static AgencyPersonnelPutRequest agencyPersonnelPUT = new AgencyPersonnelPutRequest();
  public static CustomerFilterPostRequest customerSearch = new CustomerFilterPostRequest();
  public static PagingRequestCustomerFilterPostRequest pageSearch =
      new PagingRequestCustomerFilterPostRequest();

  public static void createBUModel(Actor actor, String putOrPost) {

    // Make request to employee/business-units to get div, dept, group, branch names for customer
    // object
    actor.attemptsTo(employeeAPI.GETEmployeeBusinessUnitsOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<BusinessUnitDetailResponse> buResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class);

    // Random int in range of the filtered response to pick random business unit info
    int buSelector = new Random().nextInt(buResponse.size());

    // Assign BU data to global variables
    divisionShortName = buResponse.get(buSelector).getDivisionInfo().getShortName();
    branchShortName = buResponse.get(buSelector).getBranchInfo().getShortName();
    departmentShortName = buResponse.get(buSelector).getDepartmentInfo().getShortName();
    groupShortName = buResponse.get(buSelector).getGroupInfo().getShortName();

    // Set business unit data into customer object depending on it being a put or post request
    switch (putOrPost.toUpperCase()) {
      case "POST":
        businessUnitPOST.setDivisionShortName(divisionShortName);
        businessUnitPOST.setBranchShortName(branchShortName);
        businessUnitPOST.setDepartmentShortName(departmentShortName);
        businessUnitPOST.setGroupShortName(groupShortName);
        break;

      case "PUT":
        businessUnitPUT.setDivisionShortName(divisionShortName);
        businessUnitPUT.setBranchShortName(branchShortName);
        businessUnitPUT.setDepartmentShortName(departmentShortName);
        businessUnitPUT.setGroupShortName(groupShortName);
        break;
    }
  }

  public static void createPersonnelModel(Actor actor, String putOrPost) {

    // Make request to employee/employee to get Exec as well as Rep short names for customer object
    actor.attemptsTo(employeeAPI.GETEmployeeEmployeesOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Filter response to get only the employees that are allowed to be customer personnel
    List<EmployeeResponse> employeeFiltered =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", EmployeeResponse.class)
            .stream()
            .filter(
                execRep ->
                    execRep.getIsProd().contentEquals("Y") & execRep.getIsRep().contentEquals("Y"))
            .collect(Collectors.toList());

    // Random int in range of the filtered response to pick random exec, rep
    int execSelector = new Random().nextInt(employeeFiltered.size());
    int repSelector = new Random().nextInt(employeeFiltered.size());

    accountExecShortName = employeeFiltered.get(execSelector).getShortName();
    accountRepShortName = employeeFiltered.get(repSelector).getShortName();

    // Set agency personnel data into customer object depending on it being a put or post request
    switch (putOrPost.toUpperCase()) {
      case "POST":
        agencyPersonnelPOST.setAccountExecShortName(accountExecShortName);
        agencyPersonnelPOST.setAccountRepShortName(accountRepShortName);
        break;

      case "PUT":
        agencyPersonnelPUT.setAccountExecShortName(accountExecShortName);
        agencyPersonnelPUT.setAccountRepShortName(accountRepShortName);
        break;
    }
  }

  public static CustomerNamePostRequest createCustomerNameModel(String nameTypeTest) {

    CustomerNamePostRequest customerName = new CustomerNamePostRequest();

    // Customer Name Data
    firmName = faker.name().fullName();
    firstName = faker.name().firstName();
    middleName = faker.name().firstName();
    lastName = faker.name().lastName();
    nameType = nameTypeTest;

    // Set customer name data into model
    customerName.setNameType(nameType);
    customerName.setFirmName(firmName);
    customerName.setFirstName(firstName);
    customerName.setMiddleName(middleName);
    customerName.setLastName(lastName);

    return customerName;
  }

  public static AddressPostRequest createCustomerAddressModel() {

    AddressPostRequest customerAddress = new AddressPostRequest();

    // Customer Address Data
    addressLine1 = faker.address().streetName();
    addressLine2 = faker.address().buildingNumber();
    city = faker.address().city();
    state = faker.address().stateAbbr();
    zipCode = faker.address().zipCode();
    country = faker.address().countryCode();

    // Set customer address data into model
    customerAddress.setAddressLine1(addressLine1);
    customerAddress.setAddressLine2(addressLine2);
    customerAddress.setZipCode(zipCode);
    customerAddress.setCity(city);
    customerAddress.setState(state);
    customerAddress.setCountry(country);

    return customerAddress;
  }

  public static CustomerPhoneNumberPostRequest createCustomerPhoneModel() {

    CustomerPhoneNumberPostRequest phoneNumbers = new CustomerPhoneNumberPostRequest();

    // Customer Phone Data
    residencePhone = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    businessPhone = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    cell = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    fax = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    pager = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    other = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");

    // Set phone data into model
    phoneNumbers.setResidencePhone(residencePhone);
    phoneNumbers.setBusinessPhone(businessPhone);
    phoneNumbers.setFax(fax);
    phoneNumbers.setCell(cell);
    phoneNumbers.setPager(pager);
    phoneNumbers.setOther(other);

    return phoneNumbers;
  }

  public static CustomerBasicInfoResponse searchForACustomer(
      String customerIdTest, int customerNumberTest, Actor actor) {

    // Set customerId and customerNumber
    customerId = customerIdTest;
    customerNumber = customerNumberTest;

    // Create POST object for customer search
    customerSearch.setCustomerNumber(customerNumber);
    customerSearch.setCustomerId(customerId);
    pageSearch.setModel(customerSearch);

    // Send search request
    actor.attemptsTo(
        customersAPI.POSTCustomersSearchOnTheCustomersController(pageSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    // Capture POST Customer search response
    PagingResponseCustomerBasicInfoResponse pageResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class);

    // Get first index of response to access customer
    CustomerBasicInfoResponse oneSearchedCustomer = pageResponse.getResponse().get(0);

    return oneSearchedCustomer;
  }

  public static CustomerBasicInfoPostRequest createBasicCustomer(
      String customerTypeTest, String nameType, Actor actor) {

    // New POST model to update customer
    CustomerBasicInfoPostRequest newCustomer = new CustomerBasicInfoPostRequest();

    createBUModel(actor, "POST");

    newCustomer.setBusinessUnit(businessUnitPOST);

    createPersonnelModel(actor, "POST");

    newCustomer.setAgencyPersonnel(agencyPersonnelPOST);

    // Customer email data
    primaryEmail = faker.internet().emailAddress();
    secondaryEmail = faker.internet().emailAddress();

    // Set customer type
    customerType = customerTypeTest;
    newCustomer.setCustomerType(customerType);

    // Set names
    newCustomer.setCustomerName(createCustomerNameModel(nameType));

    // Set address
    newCustomer.setCustomerAddress(createCustomerAddressModel());

    // Set customer emails
    newCustomer.setPrimaryEmail(primaryEmail);
    newCustomer.secondaryEmail(secondaryEmail);

    // Set phone numbers
    newCustomer.setPhoneNumbers(createCustomerPhoneModel());

    return newCustomer;
  }

  public static CustomerBasicInfoPutRequest updateBasicCustomer(
      int customerNumberTest,
      String customerIdTest,
      String customerTypeTest,
      String nameType,
      Actor actor) {

    // New PUT model to update customer
    CustomerBasicInfoPutRequest updatedCustomer = new CustomerBasicInfoPutRequest();

    // Call method to set new BU values into the PUT model
    createBUModel(actor, "PUT");

    // Set new BU values
    updatedCustomer.setBusinessUnit(businessUnitPUT);

    // Call method to set new personnel values into PUT model
    createPersonnelModel(actor, "PUT");

    // Set new personnel values
    updatedCustomer.setAgencyPersonnel(agencyPersonnelPUT);

    // Customer Email Data for PUT
    primaryEmail = faker.internet().emailAddress();
    secondaryEmail = faker.internet().emailAddress();

    // Set customer number and id into model
    customerNumber = customerNumberTest;
    customerId = customerIdTest;
    updatedCustomer.setCustomerNumber(customerNumber);
    updatedCustomer.setCustomerId(customerId);

    // Set new customer type
    customerType = customerTypeTest;
    updatedCustomer.setCustomerType(customerType);

    // Set new customer name data into model
    updatedCustomer.setCustomerName(createCustomerNameModel(nameType));

    // Set new customer address data into model
    updatedCustomer.setCustomerAddress(createCustomerAddressModel());

    // Set new customer emails
    updatedCustomer.setPrimaryEmail(primaryEmail);
    updatedCustomer.secondaryEmail(secondaryEmail);

    // Set new phone data into model
    updatedCustomer.setPhoneNumbers(createCustomerPhoneModel());

    return updatedCustomer;
  }

  public static void validateBasicCustomer(CustomerBasicInfoResponse customer) {

    // Response validations for POST search
    assertThat(customer != null).isTrue();
    assertThat(customer.getClass().getDeclaredFields().length).isEqualTo(22);
    assertThat(customer.getCustomerNumber()).isEqualTo(customerNumber);
    assertThat(customer.getCustomerId()).isEqualTo(customerId);
    assertThat(customer.getCustomerType()).isEqualTo(customerType);
    assertThat(customer.getFullName()).isEqualTo(firmName);
    assertThat(customer.getFirstName()).isEqualTo(firstName);
    assertThat(customer.getMiddleName()).isEqualTo(middleName);
    assertThat(customer.getLastName()).isEqualTo(lastName);
    assertThat(customer.getAddressLine1()).isEqualTo(addressLine1);
    assertThat(customer.getAddressLine2()).isEqualTo(addressLine2);
    assertThat(customer.getZipCode().replaceAll("[\\D]", ""))
        .isEqualTo(zipCode.replaceAll("[\\D]", ""));
    assertThat(customer.getCity()).isEqualTo(city);
    assertThat(customer.getState()).isEqualTo(state);
    assertThat(customer.getCountry()).isEqualTo(country);
    assertThat(customer.getPrimaryEmail()).isEqualTo(primaryEmail);
    assertThat(customer.getSecondaryEmail()).isEqualTo(secondaryEmail);
    assertThat(customer.getResidencePhone().replaceAll("[\\D]", ""))
        .isEqualTo(residencePhone.replaceAll("[\\D]", ""));
    assertThat(customer.getBusinessPhone().replaceAll("[\\D]", ""))
        .isEqualTo(businessPhone.replaceAll("[\\D]", ""));
    assertThat(customer.getMobilePhone().replaceAll("[\\D]", ""))
        .isEqualTo(cell.replaceAll("[\\D]", ""));
  }

  // only AADM has access
  public static List<SecuredCustomerBasicInfoResponse> getAllSecuredCustomers(Actor actor) {
    actor.attemptsTo(customersAPI.GETCustomersSecuredCustomersOnTheCustomersController());
    int statusCode = SerenityRest.lastResponse().getStatusCode();

    if (statusCode == 200) {
      return LastResponse.received()
          .answeredBy(actor)
          .getBody()
          .jsonPath()
          .getList("", SecuredCustomerBasicInfoResponse.class);
    } else {
      return null;
    }
  }

  // only AADM has access
  public static SecuredCustomerBasicInfoResponse getRandomSecuredCustomer(Actor actor) {
    List<SecuredCustomerBasicInfoResponse> securedCustomers = getAllSecuredCustomers(actor);

    return securedCustomers.get(new Random().nextInt(securedCustomers.size()));
  }
}
