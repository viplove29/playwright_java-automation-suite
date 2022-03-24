package com.vertafore.test.util;

import com.github.javafaker.Faker;
import com.vertafore.test.models.ems.BasicPolicyInfoResponse;
import com.vertafore.test.models.ems.BusinessUnitDetailResponse;
import com.vertafore.test.models.ems.CustomerInfoResponse;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class CSVUtil {

  public static Faker faker = new Faker();

  // Global Change BU templates
  private static String currentBUPolicyHeaders =
      "Current Policy Division,Current Policy Branch,Current Policy Department,Current Policy Group";
  private static String newBUPolicyHeaders =
      "New Policy Division,New Policy Branch,New Policy Department,New Policy Group";
  private static String currentBUCustomerHeaders =
      "Current Customer Division,Current Customer Branch,Current Customer Department,Current Customer Group";
  private static String newBUCustomerHeaders =
      "New Customer Division,New Customer Branch,New Customer Department,New Customer Group";

  // Converts CSV String to Byte[] for requests. It is stored in a String due to issues with passing
  // a Byte[] in a request
  public static String toByteArray(String csv) {
    return new String(Base64.getEncoder().encode(csv.getBytes()));
  }

  public static String generateUniqueFilename(String prefix) {
    return prefix + "-" + faker.number().digits(8) + ".csv";
  }

  // Extracts BU info from a response and creates the CSV row segment
  private static String formatBusinessUnitForCSVRow(BusinessUnitDetailResponse buDetails) {
    return String.join(
        ",",
        Arrays.asList(
            buDetails.getDivisionInfo().getName(),
            buDetails.getBranchInfo().getName(),
            buDetails.getDepartmentInfo().getName(),
            buDetails.getGroupInfo().getName()));
  }

  /* Constructs the CSV headers according to Global Change BU type. The reverse can be
  used to get the CSV for reverting the global change*/
  public static String generateGlobalChangeBUCSVHeaders(String type, Boolean reverse) {
    String firstColumnHeader;
    String currentBUHeaders;
    String newBUHeaders;
    switch (type.toUpperCase()) {
      case "POLICY":
        firstColumnHeader = "Policy Id";
        currentBUHeaders = currentBUPolicyHeaders;
        newBUHeaders = newBUPolicyHeaders;
        break;
      case "CUSTOMER":
        firstColumnHeader = "Customer Number";
        currentBUHeaders = currentBUCustomerHeaders;
        newBUHeaders = newBUCustomerHeaders;
        break;
      default:
        throw new IllegalArgumentException("Type must be POLICY or CUSTOMER.");
    }
    // The headers can be flipped to revert Global Change Business Unit
    if (reverse) {
      return firstColumnHeader
          + ","
          + newBUHeaders
          + ","
          + currentBUHeaders
          + System.lineSeparator();
    }
    return firstColumnHeader + "," + currentBUHeaders + "," + newBUHeaders + System.lineSeparator();
  }

  /* Given a policy object, get the matching BU as well as a non-matching BU to serve as the
  / destination */
  public static String generateGlobalChangeBUCSVRowFromPolicy(
      Actor actor, BasicPolicyInfoResponse policy) {
    String policyId = policy.getPolicyId();
    String groupCode = policy.getGlGroupCode();

    UseEmployeeTo employeeApi = new UseEmployeeTo();
    actor.attemptsTo(employeeApi.GETEmployeeBusinessUnitsOnTheEmployeesController());

    List<BusinessUnitDetailResponse> buResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class);

    BusinessUnitDetailResponse fromBUDetails =
        buResponse
            .stream()
            .filter(divName -> divName.getGroupInfo().getCode().equals(groupCode))
            .collect(Collectors.toList())
            .get(0);

    BusinessUnitDetailResponse toBUDetails =
        buResponse
            .stream()
            .filter(divName -> !divName.getGroupInfo().getCode().equals(groupCode))
            .collect(Collectors.toList())
            .get(0);

    String fromBU = formatBusinessUnitForCSVRow(fromBUDetails);
    String toBU = formatBusinessUnitForCSVRow(toBUDetails);

    return policyId + "," + fromBU + "," + toBU;
  }

  /* Given a customer object, get the matching BU as well as a non-matching BU to serve as the
  / destination */
  public static String generateGlobalChangeBUCSVRowFromCustomer(
      Actor actor, CustomerInfoResponse customer) {
    Integer customerNumber = customer.getCustomerNumber();
    String groupShortName = customer.getBusinessUnits().getGroupShortName();

    UseEmployeeTo employeeApi = new UseEmployeeTo();
    actor.attemptsTo(employeeApi.GETEmployeeBusinessUnitsOnTheEmployeesController());

    List<BusinessUnitDetailResponse> buResponse =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", BusinessUnitDetailResponse.class);

    BusinessUnitDetailResponse fromBUDetails =
        buResponse
            .stream()
            .filter(divName -> divName.getGroupInfo().getShortName().equals(groupShortName))
            .collect(Collectors.toList())
            .get(0);

    BusinessUnitDetailResponse toBUDetails =
        buResponse
            .stream()
            .filter(divName -> !divName.getGroupInfo().getShortName().equals(groupShortName))
            .collect(Collectors.toList())
            .get(0);

    String fromBU = formatBusinessUnitForCSVRow(fromBUDetails);
    String toBU = formatBusinessUnitForCSVRow(toBUDetails);

    return customerNumber.toString() + "," + fromBU + "," + toBU;
  }
}
