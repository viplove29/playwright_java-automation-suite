package com.vertafore.test.common.servicewrappers.auth;

import com.vertafore.test.common.models.services.auth.EntityV1;
import com.vertafore.test.common.util.ServiceUtils;
import io.restassured.response.Response;
import java.io.IOException;

public class AuthService {

  private ServiceUtils serviceUtils;

  public AuthService() throws IOException {
    serviceUtils = new ServiceUtils();
  }

  // endpoints
  public static final String BASE_PATH = "/auth/v1/{productId}/{tenantId}/entities/{entityId}";

  public static final String CREATE_OR_UPDATE_ENTITY = "/entities/{entityIdToWrite}";

  public static final String GET_ENTITY_ANCESTOR =
      "/entities/{entityId}?ancestor_levels=all&ancestor_properties=all";

  public EntityV1 getAgencyDataFromAuth() {
    Response response = serviceUtils.sendGetRequest(GET_ENTITY_ANCESTOR);
    // return the EntityV1 object
    return response.getBody().jsonPath().getObject("content", EntityV1.class);
  }

  public EntityV1 updateEntityByPut(String url, EntityV1 entityToSend) {
    String entityId = serviceUtils.getCurrentEntityId();
    Response response =
        serviceUtils.sendPutRequest(url.replace("{entityIdToWrite}", entityId), entityToSend);
    return response.getBody().jsonPath().getObject("content", EntityV1.class);
  }
}
