package com.vertafore.test.common.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.vertafore.autotest.serenitybase.rest.requestcalls.BaseCalls;
import com.vertafore.test.common.models.general.PatchBody;
import com.vertafore.test.common.models.general.PatchOperation;
import com.vertafore.test.common.util.titanbase.TitanRequestSpecBuilder;
import com.vertafore.test.common.util.titanbase.steps.teststeps.CommonServiceTestSteps;
import com.vertafore.test.common.util.titanbase.testdata.DataHandler;
import com.vertafore.test.common.util.titanbase.testdata.UserData;
import io.restassured.response.Response;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.serenitybdd.core.Serenity;
import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.assertj.core.api.AssertionsForClassTypes;

public class ServiceUtils {

  private DataHandler dataHandler;
  private TitanRequestSpecBuilder builder;

  public ServiceUtils() throws IOException {
    dataHandler = DataHandler.getInstance(null);
  }

  /** Rest Calls */

  // responsible for sending each post request and validating the response
  public <T> Response sendPostRequest(String url, T requestBody) {
    builder = dataHandler.getBuilder();
    builder.setBody(requestBody);
    Response response = BaseCalls.makePostCall(builder, url);
    checkResponseForSuccessCodeOrThrowError(response);
    return response;
  }

  // set up to consume a map <string, generic type>
  // Right now, we are expecting the only generic type to be a File
  // and all other values in the map will be written as a string.
  public <T> Response sendMultiPartPostRequest(HashMap<String, T> multiPartBody, String url) {
    builder = dataHandler.getBuilder();
    builder.setContentType("multipart/form-data");
    for (Map.Entry<String, T> entry : multiPartBody.entrySet()) {
      String k = entry.getKey();
      T v = entry.getValue();

      if (k.equals("file")) {
        assert v instanceof File;
        builder.addMultiPart(k, (File) v);
      } else {
        builder.addMultiPart(k, v.toString());
      }
    }
    // send response
    Response response = BaseCalls.makePostCall(builder, url);
    // check for success status code
    checkResponseForSuccessCodeOrThrowError(response);

    return response;
  }
  // responsible for sending patch requests and checking response for success
  public <T> Response sendPatchRequest(String url, T patchRequestBody) {
    builder = dataHandler.getBuilder();
    builder.setBody(patchRequestBody);
    builder.setContentType("application/json-patch+json");
    Response response = BaseCalls.makePatchCall(builder, url);
    checkResponseForSuccessCodeOrThrowError(response);
    return response;
  }

  //  Generic Patch Builder that takes a hydrated model (ex: FormFieldV1)
  //  and builds a valid patch body request
  public <T> ArrayList<PatchBody> buildPatchRequestFromModel(T hydratedModel)
      throws IllegalAccessException {
    ArrayList<PatchBody> result = new ArrayList<>();

    for (Field f : hydratedModel.getClass().getDeclaredFields()) {
      java.lang.Object value = f.get(hydratedModel);
      java.lang.Object key = f.getName();

      // because we probably aren't going to path an ID/createdOn/updateOn
      if (!key.equals("id") && !key.equals("createdOn") && !key.equals("updatedOn")) {
        result.add(buildSinglePatchBody("/" + key.toString(), value));
      }
    }
    return result;
  }

  private <T> PatchBody buildSinglePatchBody(String path, T value) {
    PatchBody singlePatchBody = new PatchBody();
    singlePatchBody.setOperation(PatchOperation.REPLACE);
    singlePatchBody.setPath(path);
    singlePatchBody.setValue(value);
    return singlePatchBody;
  }

  // responsible for sending get requests and checking response for success
  public Response sendGetRequest(String url) {
    builder = dataHandler.getBuilder();
    Response response = BaseCalls.makeGetCall(builder, url);
    checkResponseForSuccessCodeOrThrowError(response);
    return response;
  }

  // responsible for sending put requests and checking response for success
  public <T> Response sendPutRequest(String url, T requestBody) {
    builder = dataHandler.getBuilder();
    builder.setBody(requestBody);
    builder.setContentType("application/json");
    Response response = BaseCalls.makePutCall(builder, url);
    checkResponseForSuccessCodeOrThrowError(response);
    return response;
  }

  public <T> Response sendDeleteRequest(String url) {
    builder = dataHandler.getBuilder();
    Response response = BaseCalls.makeDeleteCall(builder, url);
    checkResponseForSuccessCodeOrThrowError(response);
    return response;
  }

  // general success response method
  private void checkResponseForSuccessCodeOrThrowError(Response response) {
    assertThat(response.getStatusCode()).isBetween(200, 299);
  }

  /** Generic Utils -- maybe should live in a different file? */
  // generic method to find resources to upload
  public File getFileByFileName(String pathName, String fileName, String fileExtension) {

    return new File(
        Objects.requireNonNull(
                getClass()
                    .getClassLoader()
                    .getResource("files/" + pathName + "/" + fileName + fileExtension))
            .getFile());
  }

  /** Login and session variable helpers */

  // general helpers to change contexts/services/logging in
  public void loginWithServiceAndContext(
      String user, String service, String overrideBaseUri, String contextName) throws IOException {

    new CommonServiceTestSteps()
        .prepareUserTokenAndContext(user, service, overrideBaseUri, contextName);

    // just make sure the service actually got changed.
    if (service != null && !getCurrentService().equals(service)) {
      throw new IllegalArgumentException(
          service + " service was not changed to the current service..");
    }
  }

  public void changeServiceTo(String service) {
    Serenity.setSessionVariable("service").to(service);
    if (Serenity.sessionVariableCalled("service") != service) {
      throw new IllegalArgumentException("service was not reset...");
    }
  }

  public String getCurrentEntityId() {
    CaseInsensitiveMap currentContext = Serenity.sessionVariableCalled("currentContext");
    return currentContext.get("entityid").toString();
  }

  public String getCurrentTenantId() {
    CaseInsensitiveMap currentContext = Serenity.sessionVariableCalled("currentContext");
    return currentContext.get("tenantid").toString();
  }

  public String getCurrentProductId() {
    CaseInsensitiveMap currentContext = Serenity.sessionVariableCalled("currentContext");
    return currentContext.get("productid").toString();
  }

  public String getCurrentUserName() {
    CaseInsensitiveMap currentContext = Serenity.sessionVariableCalled("currentContext");
    return currentContext.get("name").toString();
  }

  public String getCurrentService() {
    return Serenity.sessionVariableCalled("service");
  }

  // check current user and service if not what we need then we login.
  public void checkLoginOrLogIn(
      String userName, String service, String overrideDefaultUri, String userContextName)
      throws IOException {
    if (!service.equals(getCurrentService()) && !getCurrentUserName().equals(userName)) {
      loginWithServiceAndContext(userName, service, overrideDefaultUri, userContextName);
    }
  }

  // manually add contexts to a user
  public void manuallyAddAgencyEntityContextInfo(
      String userCommonName,
      String entityIdToSet,
      String contextCommonNameToSaveAs,
      String productId,
      String tenantId) {
    UserData userData = dataHandler.getUserByName(userCommonName);
    userData.addContext(contextCommonNameToSaveAs, productId, tenantId, entityIdToSet);
  }
  /** Validation Helpers */
  public static void validatePostRequest(Object req, Object res) throws IllegalAccessException {
    Class<?> reqClass = req.getClass();
    Class<?> resClass = res.getClass();

    Field[] fields = reqClass.getDeclaredFields();
    for (Field field : fields) {
      String name = field.getName();
      Object reqValue = FieldUtils.readDeclaredField(req, name, true);
      Object resValue = FieldUtils.readDeclaredField(res, name, true);
      String fieldTypeClassName = field.getType().toString();
      Boolean fieldTypeGeneric =
          fieldTypeClassName.contains("java.lang")
              || fieldTypeClassName.contains("java.time")
              || fieldTypeClassName.contains("java.math");
      Boolean isEnum = field.getType().isEnum();

      if (reqValue != null) {
        if (fieldTypeGeneric || isEnum)
          AssertionsForClassTypes.assertThat(reqValue).isEqualTo(resValue);
        else {
          if (fieldTypeClassName.contains("java.util.List")) {
            ArrayList requestList = ((ArrayList) reqValue);
            ArrayList responseList = ((ArrayList) resValue);
            // Make sure both lists have the same size
            int size = requestList.size();
            AssertionsForClassTypes.assertThat(size).isEqualTo(responseList.size());
            for (int i = 0; i < size; i++) {
              validatePostRequest(requestList.get(i), responseList.get(i));
            }
          } else {
            validatePostRequest(reqValue, resValue);
          }
        }
      }
    }
  }

  public static void validateGetRequestFromPostResponse(Object req, Object res) {
    AssertionsForClassTypes.assertThat(req).isEqualTo(res);
  }
}
