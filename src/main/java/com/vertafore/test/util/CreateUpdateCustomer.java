package com.vertafore.test.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.javafaker.Faker;
import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import java.util.List;
import java.util.Random;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class CreateUpdateCustomer {

  public CustomerBasicInfoPostRequest createBasicCustomer(
      String customerType, String nameType, Actor actor) {

    // Faker data library
    Faker faker = new Faker();

    // Number generators to make business unit, exec, and rep data random for each new object
    Integer busUnitSelector = new Random().nextInt(5);
    Integer execSelector = new Random().nextInt(5);
    Integer repSelector = new Random().nextInt(5);

    // Request models
    CustomerBasicInfoPostRequest newCustomer = new CustomerBasicInfoPostRequest();
    CustomerNamePostRequest customerName = new CustomerNamePostRequest();
    AddressPostRequest customerAddress = new AddressPostRequest();
    CustomerPhoneNumberPostRequest phoneNumbers = new CustomerPhoneNumberPostRequest();
    BusinessUnitShortNamePostRequest businessUnit = new BusinessUnitShortNamePostRequest();
    AgencyPersonnelPostRequest agencyPersonnel = new AgencyPersonnelPostRequest();

    UseEmployeeTo employeeAPI = new UseEmployeeTo();

    // Make request to employee/business-units to get div, dept, group, branch names for customer
    // object
    actor.attemptsTo(employeeAPI.GETEmployeeBusinessUnitsOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BusinessUnitDetailResponse buResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class)
            .get(busUnitSelector);

    // Set business unit values into customer object
    businessUnit.setDivisionShortName(buResponse.getDivisionInfo().getShortName());
    businessUnit.setBranchShortName(buResponse.getBranchInfo().getShortName());
    businessUnit.setDepartmentShortName(buResponse.getDepartmentInfo().getShortName());
    businessUnit.setGroupShortName(buResponse.getGroupInfo().getShortName());
    newCustomer.setBusinessUnit(businessUnit);

    // Make request to employee/employee to get Exec as well as Rep short names for customer object
    actor.attemptsTo(employeeAPI.GETEmployeeEmployeesOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<EmployeeResponse> employees =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", EmployeeResponse.class);

    // Set agency personnel data into customer object
    agencyPersonnel.setAccountExecShortName(employees.get(execSelector).getShortName());
    agencyPersonnel.setAccountRepShortName(employees.get(repSelector).getShortName());
    newCustomer.setAgencyPersonnel(agencyPersonnel);

    // Customer Name Data for POST
    String firmName = faker.name().fullName();
    String firstName = faker.name().firstName();
    String middleName = faker.name().firstName();
    String lastName = faker.name().lastName();

    // Customer Address Data for POST
    String address1 = faker.address().streetName();
    String address2 = faker.address().buildingNumber();
    String city = faker.address().city();
    String state = faker.address().stateAbbr();
    String zipCode = faker.address().zipCode();
    String country = faker.address().countryCode();

    // Customer Email Data for POST
    String primaryEmail = faker.internet().emailAddress();
    String secondaryEmail = faker.internet().emailAddress();

    // Customer Phone Data for POST
    String residencePhone = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String businessPhone = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String cell = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String fax = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String pager = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String other = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");

    // Set customer type
    newCustomer.setCustomerType(customerType);

    // Set customer name data into model
    customerName.setNameType(nameType);
    customerName.setFirmName(firmName);
    customerName.setFirstName(firstName);
    customerName.setMiddleName(middleName);
    customerName.setLastName(lastName);
    newCustomer.setCustomerName(customerName);

    // Set customer address data into model
    customerAddress.setAddressLine1(address1);
    customerAddress.setAddressLine2(address2);
    customerAddress.setZipCode(zipCode);
    customerAddress.setCity(city);
    customerAddress.setState(state);
    customerAddress.setCountry(country);
    newCustomer.setCustomerAddress(customerAddress);

    // Set customer emails
    newCustomer.setPrimaryEmail(primaryEmail);
    newCustomer.secondaryEmail(secondaryEmail);

    // Set phone data into model
    phoneNumbers.setResidencePhone(residencePhone);
    phoneNumbers.setBusinessPhone(businessPhone);
    phoneNumbers.setFax(fax);
    phoneNumbers.setCell(cell);
    phoneNumbers.setPager(pager);
    phoneNumbers.setOther(other);
    newCustomer.setPhoneNumbers(phoneNumbers);

    return newCustomer;
  }

  public CustomerBasicInfoPutRequest updateBasicCustomer(
      Integer customerNumber,
      String customerId,
      String customerType,
      String nameType,
      Actor actor) {

    // Faker data library
    Faker faker = new Faker();

    // Number generators to make business unit, exec, and rep data random for each new object
    Integer newBusUnitSelector = new Random().nextInt(5);
    Integer newExecSelector = new Random().nextInt(5);
    Integer newRepSelector = new Random().nextInt(5);

    CustomerBasicInfoPutRequest updatedCustomer = new CustomerBasicInfoPutRequest();
    BusinessUnitShortNamePutRequest newBusinessUnit = new BusinessUnitShortNamePutRequest();
    AgencyPersonnelPutRequest newAgencyPersonnel = new AgencyPersonnelPutRequest();
    CustomerNamePostRequest newCustomerName = new CustomerNamePostRequest();
    AddressPostRequest newCustomerAddress = new AddressPostRequest();
    CustomerPhoneNumberPostRequest newPhoneNumbers = new CustomerPhoneNumberPostRequest();

    UseEmployeeTo employeeAPI = new UseEmployeeTo();

    // Make request to employee/business-units to get new set of div, dept, group, branch names for
    // PUT customer object
    actor.attemptsTo(employeeAPI.GETEmployeeBusinessUnitsOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    BusinessUnitDetailResponse buResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class)
            .get(newBusUnitSelector);

    // Set new business unit values into customer object
    newBusinessUnit.setDivisionShortName(buResponse.getDivisionInfo().getShortName());
    newBusinessUnit.setBranchShortName(buResponse.getBranchInfo().getShortName());
    newBusinessUnit.setDepartmentShortName(buResponse.getDepartmentInfo().getShortName());
    newBusinessUnit.setGroupShortName(buResponse.getGroupInfo().getShortName());
    updatedCustomer.setBusinessUnit(newBusinessUnit);

    // Make request to employee/employee to get new exec as well as rep short names for PUT customer
    // object
    actor.attemptsTo(employeeAPI.GETEmployeeEmployeesOnTheEmployeesController());
    assertThat(SerenityRest.lastResponse().getStatusCode()).isEqualTo(200);

    List<EmployeeResponse> employees =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", EmployeeResponse.class);

    // Set new agency personnel data into customer object
    newAgencyPersonnel.setAccountExecShortName(employees.get(newExecSelector).getShortName());
    newAgencyPersonnel.setAccountRepShortName(employees.get(newRepSelector).getShortName());
    updatedCustomer.setAgencyPersonnel(newAgencyPersonnel);

    // Customer Name Data for PUT
    String newFirmName = faker.name().fullName();
    String newFirstName = faker.name().firstName();
    String newMiddleName = faker.name().firstName();
    String newLastName = faker.name().lastName();

    // Customer Address Data for PUT
    String newAddress1 = faker.address().streetName();
    String newAddress2 = faker.address().buildingNumber();
    String newCity = faker.address().city();
    String newState = faker.address().stateAbbr();
    String newZipCode = faker.address().zipCode();
    String newCountry = faker.address().countryCode();

    // Customer Email Data for PUT
    String newPrimaryEmail = faker.internet().emailAddress();
    String newSecondaryEmail = faker.internet().emailAddress();

    // Customer Phone Data for PUT
    String newResidencePhone = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String newBusinessPhone = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String newCell = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String newFax = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String newPager = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");
    String newOther = faker.phoneNumber().cellPhone().replaceFirst("^1-", "");

    // Set customer number and id into model
    updatedCustomer.setCustomerNumber(customerNumber);
    updatedCustomer.setCustomerId(customerId);

    // Set new customer type
    updatedCustomer.setCustomerType(customerType);

    // Set new customer name data into model
    newCustomerName.setNameType(nameType);
    newCustomerName.setFirmName(newFirmName);
    newCustomerName.setFirstName(newFirstName);
    newCustomerName.setMiddleName(newMiddleName);
    newCustomerName.setLastName(newLastName);
    updatedCustomer.setCustomerName(newCustomerName);

    // Set new customer address data into model
    newCustomerAddress.setAddressLine1(newAddress1);
    newCustomerAddress.setAddressLine2(newAddress2);
    newCustomerAddress.setZipCode(newZipCode);
    newCustomerAddress.setCity(newCity);
    newCustomerAddress.setState(newState);
    newCustomerAddress.setCountry(newCountry);
    updatedCustomer.setCustomerAddress(newCustomerAddress);

    // Set new customer emails
    updatedCustomer.setPrimaryEmail(newPrimaryEmail);
    updatedCustomer.secondaryEmail(newSecondaryEmail);

    // Set new phone data into model
    newPhoneNumbers.setResidencePhone(newResidencePhone);
    newPhoneNumbers.setBusinessPhone(newBusinessPhone);
    newPhoneNumbers.setFax(newFax);
    newPhoneNumbers.setCell(newCell);
    newPhoneNumbers.setPager(newPager);
    newPhoneNumbers.setOther(newOther);
    updatedCustomer.setPhoneNumbers(newPhoneNumbers);

    return updatedCustomer;
  }
}
