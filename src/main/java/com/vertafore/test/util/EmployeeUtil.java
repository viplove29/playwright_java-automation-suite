package com.vertafore.test.util;

import com.vertafore.test.models.ems.*;
import com.vertafore.test.servicewrappers.UseEmployeeTo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class EmployeeUtil {
  private static UseEmployeeTo employeeApi = new UseEmployeeTo();

  public static EmployeeResponse getRandomEmployee(Actor actor) {
    List<EmployeeResponse> employeeList = getAllEmployees(actor);
    int randomNum = new Random().nextInt(employeeList.size());
    return employeeList.get(randomNum);
  }

  public static EmployeeResponse getRandomExec(Actor actor) {
    List<EmployeeResponse> employeeList =
        getAllEmployees(actor)
            .stream()
            .filter(employee -> employee.getIsProd().equals("Y"))
            .collect(Collectors.toList());
    int randomNum = new Random().nextInt(employeeList.size());
    return employeeList.get(randomNum);
  }

  public static EmployeeResponse getRandomRep(Actor actor) {
    List<EmployeeResponse> employeeList =
        getAllEmployees(actor)
            .stream()
            .filter(employee -> employee.getIsRep().equals("Y") && employee.getIsProd().equals("N"))
            .collect(Collectors.toList());
    int randomNum = new Random().nextInt(employeeList.size());
    return employeeList.get(randomNum);
  }

  public static List<EmployeeResponse> getAllEmployees(Actor actor) {
    actor.attemptsTo(employeeApi.GETEmployeeEmployeesOnTheEmployeesController());
    List<EmployeeResponse> employeeList =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", EmployeeResponse.class);

    return employeeList;
  }

  public static List<SecuredCustomerBasicInfoResponse> getAllSecuredCustomersUnderAnEmployee(
      Actor actor, String empCode) {
    actor.attemptsTo(
        employeeApi.GETEmployeeSecuredCustomersOnTheEmployeesController(empCode, "string"));
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

  public static int insertSecuredCustomerAccessForEmployee(
      Actor actor, String empCode, String customerId) {
    CustomerEmployeeSecurityPutRequest securedCustomerPutRequest =
        new CustomerEmployeeSecurityPutRequest();
    securedCustomerPutRequest.setEmployeeCode(empCode);
    securedCustomerPutRequest.setCustomerId(customerId);

    // format put request into a list for the endpoint
    List<CustomerEmployeeSecurityPutRequest> listPutRequest = new ArrayList<>();
    listPutRequest.add(securedCustomerPutRequest);

    actor.attemptsTo(
        employeeApi.PUTEmployeeSecuredCustomersCustomerEmployeeSecurityOnTheEmployeesController(
            listPutRequest, "string"));
    return SerenityRest.lastResponse().getStatusCode();
  }

  public static int insertMultipleSecuredCustomerAccessesForEmployee(
      Actor actor, String empCode, List<String> customerIds) {
    List<CustomerEmployeeSecurityPutRequest> customersToInsert = new ArrayList<>();

    // format put request and insert all customer ids into list
    for (String custId : customerIds) {
      CustomerEmployeeSecurityPutRequest securedCustomerPutRequest =
          new CustomerEmployeeSecurityPutRequest();
      securedCustomerPutRequest.setCustomerId(custId);
      securedCustomerPutRequest.setEmployeeCode(empCode);
      customersToInsert.add(securedCustomerPutRequest);
    }

    actor.attemptsTo(
        employeeApi.PUTEmployeeSecuredCustomersCustomerEmployeeSecurityOnTheEmployeesController(
            customersToInsert, "string"));
    return SerenityRest.lastResponse().getStatusCode();
  }

  public static int deleteSecuredCustomerAccessForEmployee(
      Actor actor, String empCode, String customerId) {
    CustomerEmployeeSecurityPostRequest securedCustomerDeletePostRequest =
        new CustomerEmployeeSecurityPostRequest();
    securedCustomerDeletePostRequest.setEmployeeCode(empCode);
    securedCustomerDeletePostRequest.setCustomerId(customerId);

    // format post request into a list for the endpoint
    List<CustomerEmployeeSecurityPostRequest> listPostRequest = new ArrayList<>();
    listPostRequest.add(securedCustomerDeletePostRequest);

    actor.attemptsTo(
        employeeApi
            .POSTEmployeeSecuredCustomersCustomerEmployeeSecurityDeleteOnTheEmployeesController(
                listPostRequest, "string"));

    return SerenityRest.lastResponse().getStatusCode();
  }

  public static int deleteMultipleSecuredCustomerAccessForEmployee(
      Actor actor, String empCode, List<String> customerIds) {
    List<CustomerEmployeeSecurityPostRequest> customersToDelete = new ArrayList<>();

    // format post request and insert all customer ids into list
    for (String custId : customerIds) {
      CustomerEmployeeSecurityPostRequest securedCustomerPostRequest =
          new CustomerEmployeeSecurityPostRequest();
      securedCustomerPostRequest.setCustomerId(custId);
      securedCustomerPostRequest.setEmployeeCode(empCode);
      customersToDelete.add(securedCustomerPostRequest);
    }

    actor.attemptsTo(
        employeeApi
            .POSTEmployeeSecuredCustomersCustomerEmployeeSecurityDeleteOnTheEmployeesController(
                customersToDelete, "string"));
    return SerenityRest.lastResponse().getStatusCode();
  }

  public static boolean doesEmployeeHaveAccessToSecuredCustomer(
      Actor actor, String empCode, String customerId) {

    // get all secured customers employee has access to and filter by the desired custId
    List<SecuredCustomerBasicInfoResponse> securedCustomersUnderEmployee =
        getAllSecuredCustomersUnderAnEmployee(actor, empCode)
            .stream()
            .filter(securedCustomer -> securedCustomer.getCustomerId().contains(customerId))
            .collect(Collectors.toList());

    return securedCustomersUnderEmployee.size() > 0;
  }

  public static int DeleteUserAuthGroup(Actor actor, String authGroupId, String empCode) {

    // Format post body
    UserAuthGroupsDeletePostRequest deletePostRequest = new UserAuthGroupsDeletePostRequest();
    deletePostRequest.setAuthGroupId(authGroupId);
    deletePostRequest.setEmpCode(empCode);

    List<UserAuthGroupsDeletePostRequest> listDeletePostRequest = new ArrayList<>();
    listDeletePostRequest.add(deletePostRequest);

    actor.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsDeleteOnTheEmployeesController(
            listDeletePostRequest, "string"));
    return SerenityRest.lastResponse().getStatusCode();
  }

  public static int PostAuthGroupUser(
      Actor actor, AuthGroupResponse authGroupToPut, String empCode) {

    // Get random auth group id to assign it to the service Employee.
    String randomAGrpId = authGroupToPut.getaGrpId();

    // Format the singular post request
    UserAuthGroupsPostRequest postRequest = new UserAuthGroupsPostRequest();
    postRequest.setEmpCode(empCode);
    postRequest.setAuthGroupId(randomAGrpId);

    // Insert Post request into a list for formatting
    List<UserAuthGroupsPostRequest> listRequest = new ArrayList<>();
    listRequest.add(postRequest);

    actor.attemptsTo(
        employeeApi.POSTEmployeeUserAuthGroupsOnTheEmployeesController(listRequest, "string"));

    return SerenityRest.lastResponse().getStatusCode();
  }

  public static boolean doesEmployeeHaveAuthGroupAccess(
      Actor actor, String authGroupID, String empCode) {
    actor.attemptsTo(
        employeeApi.GETEmployeeUserAuthGroupsOnTheEmployeesController(empCode, "string"));

    // get all authgroups under employee
    List<AuthGroupUser> authGroupsUnderEmployee =
        LastResponse.received()
            .answeredBy(actor)
            .getBody()
            .jsonPath()
            .getList("", AuthGroupUser.class);

    // filter by desired authgroup id
    List<AuthGroupUser> desiredAuthGroup =
        authGroupsUnderEmployee
            .stream()
            .filter(authGroup -> authGroup.getAuthGroupId().equals(authGroupID))
            .collect(Collectors.toList());

    return desiredAuthGroup.size() > 0;
  }

  public static EmployeeResponse getEmployeeDetailsByEmpCode(Actor actor, String empCode) {
    List<EmployeeResponse> employees = getAllEmployees(actor);
    List<EmployeeResponse> filteredEmployees =
        employees
            .stream()
            .filter(employee -> employee.getEmpCode().equals(empCode))
            .collect(Collectors.toList());
    if (filteredEmployees.size() > 0) {
      return filteredEmployees.get(0);
    }
    return null;
  }
}
