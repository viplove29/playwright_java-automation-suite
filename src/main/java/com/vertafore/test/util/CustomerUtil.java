package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseCustomerTo;
import com.vertafore.test.servicewrappers.UseCustomersTo;
import com.vertafore.test.servicewrappers.UseCustomersWithContactsDependentsTo;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.joda.time.DateTime;

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
  public static String fullName;
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
  public static UseCustomerTo customerAPI = new UseCustomerTo();
  public static UseCustomersWithContactsDependentsTo contactDependentsApi =
      new UseCustomersWithContactsDependentsTo();
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

  public static CustomerNameExpandedPostRequest createCustomerNameExpandedModel() {

    String[] nameTypes = {"Individual", "Family"};
    CustomerNameExpandedPostRequest customerName = new CustomerNameExpandedPostRequest();

    // Customer Name Data
    firmName = faker.name().fullName();
    firstName = faker.name().firstName();
    middleName = faker.name().firstName();
    lastName = faker.name().lastName();
    nameType = nameTypes[new Random().nextInt(nameTypes.length)];

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

  public static CustomerIdResponse createBrokerCustomer(Actor actor, String brokerShortName) {

    CustomerNameExpandedPostRequest customerNameModel = createCustomerNameExpandedModel();
    createBUModel(actor, "POST");

    AgencyPersonnelPostRequestWithBroker agencyPersonnelModel =
        new AgencyPersonnelPostRequestWithBroker();

    agencyPersonnelModel.setIsBrokerCustomer(true);
    agencyPersonnelModel.setBrokerShortName(brokerShortName);
    agencyPersonnelModel.setAccountExecShortName(EmployeeUtil.getRandomExec(actor).getShortName());
    agencyPersonnelModel.setAccountRepShortName(EmployeeUtil.getRandomRep(actor).getShortName());

    CustomerInfoPostRequest customerInfoPostRequest = new CustomerInfoPostRequest();
    customerInfoPostRequest.setBusinessUnit(businessUnitPOST);
    customerInfoPostRequest.setAgencyPersonnel(agencyPersonnelModel);
    customerInfoPostRequest.setCustomerType("Customer");
    customerInfoPostRequest.setCustomerName(customerNameModel);
    customerInfoPostRequest.setCustomerAddedDate(new DateTime().toString());

    actor.attemptsTo(customerAPI.POSTCustomerOnTheCustomersController(customerInfoPostRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    CustomerIdResponse response =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    return response;
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
    if (pageResponse.getResponse().size() > 0) {
      return pageResponse.getResponse().get(0);
    } else {
      return null;
    }
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

  public static CustomerIdResponse stageARandomCustomer(Actor actor) {
    CustomerBasicInfoPostRequest customerToStageModel =
        createBasicCustomer("Customer", "Individual", actor);
    actor.attemptsTo(
        customerAPI.POSTCustomerBasicInfoOnTheCustomersController(customerToStageModel, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode())
        .as(SerenityRest.lastResponse().toString())
        .isEqualTo(200);

    CustomerIdResponse stagedCustomerResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", CustomerIdResponse.class);

    assertThat(stagedCustomerResponse).isNotNull();
    assertThat(stagedCustomerResponse.getCustomerId()).isNotNull();

    return stagedCustomerResponse;
  }

  public static void setBasicCustomerValidationHelpers(CustomerBasicInfoResponse customer) {
    customerId = customer.getCustomerId();
    customerNumber = customer.getCustomerNumber();
    customerType = customer.getCustomerType();
    fullName = customer.getFullName();
    firmName = customer.getFirmName();
    firstName = customer.getFirstName();
    lastName = customer.getLastName();
    middleName = customer.getMiddleName();
    addressLine1 = customer.getAddressLine1();
    addressLine2 = customer.getAddressLine2();
    zipCode = customer.getZipCode();
    city = customer.getCity();
    state = customer.getState();
    country = customer.getCountry();
    primaryEmail = customer.getPrimaryEmail();
    secondaryEmail = customer.getSecondaryEmail();
    residencePhone = customer.getResidencePhone();
    businessPhone = customer.getBusinessPhone();
    cell = customer.getMobilePhone();
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

  public static List<String> getAllSecuredCustomerIds(Actor actor) {
    List<SecuredCustomerBasicInfoResponse> allSecuredCustomers = getAllSecuredCustomers(actor);

    // get list of secured customer id strings from last response
    return allSecuredCustomers
        .stream()
        .map(securedCustomer -> securedCustomer.getCustomerId())
        .collect(Collectors.toList());
  }

  // only AADM has access
  public static SecuredCustomerBasicInfoResponse getRandomSecuredCustomer(Actor actor) {
    List<SecuredCustomerBasicInfoResponse> securedCustomers = getAllSecuredCustomers(actor);

    return securedCustomers.get(new Random().nextInt(securedCustomers.size()));
  }

  public static CustomerBasicInfoResponse selectRandomCustomer(Actor actor, String customerType) {
    customerSearch.setIncludeCustomers(false);
    customerSearch.setIncludeProspects(false);
    customerSearch.setIncludeSuspects(false);
    switch (customerType) {
      case "customer":
        customerSearch.setIncludeCustomers(true);
        break;
      case "prospect":
        customerSearch.setIncludeProspects(true);
        break;
      case "suspect":
        customerSearch.setIncludeSuspects(true);
        break;
      case "all":
        customerSearch.setIncludeCustomers(true);
        customerSearch.setIncludeProspects(true);
        customerSearch.setIncludeSuspects(true);
        break;
      default:
        System.out.println("No customers exist of type " + customerType + " in this page response");
    }
    pageSearch.setModel(customerSearch);

    actor.attemptsTo(
        customersAPI.POSTCustomersSearchOnTheCustomersController(pageSearch, "string"));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<CustomerBasicInfoResponse> allCustomers =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class)
            .getResponse();

    int customerIndex = new Random().nextInt(allCustomers.size());

    return allCustomers.get(customerIndex);
  }

  public static CustomerInfoResponse getCustomerInfoByCustomerId(Actor actor, String customerId) {
    actor.attemptsTo(customerAPI.GETCustomerOnTheCustomersController(customerId, null, "string"));

    if (SerenityRest.lastResponse().getStatusCode() == 200) {
      return LastResponse.received()
          .answeredBy(actor)
          .getBody()
          .jsonPath()
          .getObject("", CustomerInfoResponse.class);
    } else {
      return null;
    }
  }

  public static List<CustomerContactDependentResponse> getAllCustomerContactDependents(
      Actor actor, String division) {

    // format body for post search request
    CustomersWithContactsFilterPostRequest customersWithContactsFilterPostRequest =
        new CustomersWithContactsFilterPostRequest();
    customersWithContactsFilterPostRequest.setDivisionCode(division);
    PagingRequestCustomersWithContactsFilterPostRequest
        pagingRequestCustomersWithContactsFilterPostRequest =
            new PagingRequestCustomersWithContactsFilterPostRequest();
    pagingRequestCustomersWithContactsFilterPostRequest.setModel(
        customersWithContactsFilterPostRequest);

    actor.attemptsTo(
        contactDependentsApi.POSTCustomersWithContactsDependentsSearchOnTheCustomersController(
            pagingRequestCustomersWithContactsFilterPostRequest, ""));

    if (SerenityRest.lastResponse().getStatusCode() == 200) {
      PagingResponseCustomerContactDependentResponse response =
          LastResponse.received()
              .answeredBy(actor)
              .getBody()
              .jsonPath()
              .getObject("", PagingResponseCustomerContactDependentResponse.class);
      return response.getResponse();
    } else {
      return null;
    }
  }

  public static CustomerContactDependentResponse getRandomCustomerWithContacts(
      Actor actor, String division) {
    List<CustomerContactDependentResponse> contactDependentResponseList =
        CustomerUtil.getAllCustomerContactDependents(actor, division);
    assert contactDependentResponseList != null;
    contactDependentResponseList =
        contactDependentResponseList
            .stream()
            .filter(customer -> !customer.getCustomerContacts().isEmpty())
            .collect(Collectors.toList());

    return contactDependentResponseList.get(
        new Random().nextInt(contactDependentResponseList.size()));
  }

  public static CustomerContactDependentResponse getRandomCustomerWithDependents(
      Actor actor, String division) {
    List<CustomerContactDependentResponse> contactDependentResponseList =
        CustomerUtil.getAllCustomerContactDependents(actor, division);
    assert contactDependentResponseList != null;
    contactDependentResponseList =
        contactDependentResponseList
            .stream()
            .filter(customer -> !customer.getCustomerDependents().isEmpty())
            .collect(Collectors.toList());

    return contactDependentResponseList.get(
        new Random().nextInt(contactDependentResponseList.size()));
  }

  public static CustomerContactDependentResponse getCustomerContactDependentByCustomerId(
      Actor actor, String division, String custId) {
    List<CustomerContactDependentResponse> customerContactDependents =
        getAllCustomerContactDependents(actor, division);

    // filter list to only contain item that has the custId
    customerContactDependents =
        customerContactDependents
            .stream()
            .filter(customer -> customer.getCustomerId().contains(custId))
            .collect(Collectors.toList());

    if (customerContactDependents.isEmpty()) {
      return null;
    } else {
      return customerContactDependents.get(0);
    }
  }

  public static List<CustomerBasicInfoResponse> getAllCustomers(Actor actor) {
    UseCustomersTo customersApi = new UseCustomersTo();
    CustomerFilterPostRequest customerFilterPostRequest = new CustomerFilterPostRequest();
    customerFilterPostRequest.setIncludeCustomers(true);
    customerFilterPostRequest.setAnyStatus(true);

    PagingRequestCustomerFilterPostRequest pagingRequest =
        new PagingRequestCustomerFilterPostRequest();
    pagingRequest.setModel(customerFilterPostRequest);
    pagingRequest.setTop(1000);

    actor.attemptsTo(customersApi.POSTCustomersSearchOnTheCustomersController(pagingRequest, ""));
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);
    List<CustomerBasicInfoResponse> customerResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getObject("", PagingResponseCustomerBasicInfoResponse.class)
            .getResponse();
    return customerResponse;
  }
}
