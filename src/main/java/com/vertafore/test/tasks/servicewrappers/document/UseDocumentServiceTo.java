package com.vertafore.test.tasks.servicewrappers.document;

import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseDocumentServiceTo {

  private static final String THIS_SERVICE = "document";
  private static final String DOWNLOAD_BY_IDS_USING_GET_ON_THE_BYTES_CONTROLLER =
      "bytes?filter=byIds";
  private static final String GET_HIERARCHY_USING_GET_ON_THE_FOLDER_CONTENTS_CONTROLLER =
      "hierarchy?filter=byPath";
  private static final String UPDATES_A_DOCUMENT_ON_THE_DOCUMENT_CONTROLLER = "documents/{id}";
  private static final String CREATE_A_FILE_USING_A_MULTIPART_FORM_POST_ON_THE_DOCUMENT_CONTROLLER =
      "documents";
  private static final String GET_IMAGE_USING_GET_ON_THE_BRANDING_CONTROLLER =
      "brandings/{id}/bytes";
  private static final String GET_DOCUMENT_VERSIONS_USING_GET_ON_THE_VERSION_CONTROLLER =
      "documents/{id}/versions";
  private static final String CREATES_A_FOLDER_ON_THE_FOLDER_CONTROLLER = "folders";
  private static final String SEARCH_USING_GET_ON_THE_FOLDER_CONTENTS_CONTROLLER =
      "search?filter=byPathAndSearchTerm";
  private static final String
      CREATE_A_SIGNATURE_USING_A_MULTIPART_FROM_POST_ON_THE_SIGNATURE_CONTROLLER = "signatures";
  private static final String GET_CONTENTS_USING_GET_ON_THE_FOLDER_CONTENTS_CONTROLLER =
      "contents?filter=byPath";
  private static final String
      GETS_THE_METADATA_OF_THE_DOCUMENT_FOR_THE_GIVEN_ID_ON_THE_DOCUMENT_CONTROLLER =
          "documents/{id}";
  private static final String DELETES_A_DOCUMENT_BY_ID_ON_THE_DOCUMENT_CONTROLLER =
      "documents/{id}";
  private static final String GET_IMAGES_USING_GET_ON_THE_BRANDING_CONTROLLER = "brandings/bytes";
  private static final String
      RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_SIGNATURE_ON_THE_SIGNATURE_CONTROLLER =
          "signatures/{id}/bytes";
  private static final String GET_BRANDINGS_USING_GET_ON_THE_BRANDING_CONTROLLER = "brandings";
  private static final String GET_USING_GET_ON_THE_FOLDER_CONTROLLER = "folders/{id}/";
  private static final String UPDATES_A_FOLDER_ON_THE_FOLDER_CONTROLLER = "folders/{id}/";
  private static final String GET_BY_ID_USING_GET_ON_THE_BRANDING_CONTROLLER = "brandings/{id}";
  private static final String DELETE_BY_ID_USING_DELETE_ON_THE_BRANDING_CONTROLLER =
      "brandings/{id}";
  private static final String
      GETS_THE_METADATA_OF_THE_SIGNATURE_FOR_THE_GIVEN_ID_ON_THE_SIGNATURE_CONTROLLER =
          "signatures/{id}";
  private static final String DELETES_A_SIGNATURE_BY_ID_ON_THE_SIGNATURE_CONTROLLER =
      "signatures/{id}";
  private static final String GET_SIGNATURES_USING_GET_ON_THE_SIGNATURE_CONTROLLER = "signatures";
  private static final String DELETE_USING_DELETE_ON_THE_FOLDER_CONTROLLER = "folders/{id}";
  private static final String UPDATES_A_SIGNATURE_ON_THE_SIGNATURE_CONTROLLER = "signatures/{id}";
  private static final String UPDATE_USING_PUT_ON_THE_BRANDING_CONTROLLER = "brandings/{id}";
  private static final String CREATE_USING_POST_ON_THE_BRANDING_CONTROLLER = "brandings";
  private static final String DOWNLOAD_BY_VERSION_USING_GET_ON_THE_BYTES_CONTROLLER =
      "bytes/{documentId}/versions/{versionId}";
  private static final String GET_DOCUMENT_VERSION_BY_ID_USING_GET_ON_THE_VERSION_CONTROLLER =
      "documents/{documentId}/versions/{id}";

  public static Performable downloadByIdsUsingGetOnTheBytesController(String filter, String ids) {
    return Task.where(
        "{0} Retrieves the bytes for the specified paths. Will return a zip file if more than one is given.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("filter", filter)
              .queryParam("ids", ids)
              .get(DOWNLOAD_BY_IDS_USING_GET_ON_THE_BYTES_CONTROLLER);
        });
  }

  public static Performable getHierarchyUsingGetOnTheFolderContentsController(
      String filter, String path) {
    return Task.where(
        "{0} Gets the hierarchy for a path.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("filter", filter)
              .queryParam("path", path)
              .get(GET_HIERARCHY_USING_GET_ON_THE_FOLDER_CONTENTS_CONTROLLER);
        });
  }

  public static Performable updatesADocumentOnTheDocumentController(
      String document, File file, String id) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Updates a document.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .queryParam("document", document)
              .multiPart("file", file, mime)
              .pathParam("id", id)
              .put(UPDATES_A_DOCUMENT_ON_THE_DOCUMENT_CONTROLLER);
        });
  }

  public static Performable createAFileUsingAMultipartFormPostOnTheDocumentController(
      String document, File file) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Create a file using a multi-part form POST.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .queryParam("document", document)
              .multiPart("file", file, mime)
              .post(CREATE_A_FILE_USING_A_MULTIPART_FORM_POST_ON_THE_DOCUMENT_CONTROLLER);
        });
  }

  public static Performable getImageUsingGetOnTheBrandingController(String id, String imageType) {
    return Task.where(
        "{0} Retrieves the bytes for the specified branding.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("imageType", imageType)
              .get(GET_IMAGE_USING_GET_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable getDocumentVersionsUsingGetOnTheVersionController(
      String id, String pageSize, String page) {
    return Task.where(
        "{0} Get all of the metadata for a specific document",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(GET_DOCUMENT_VERSIONS_USING_GET_ON_THE_VERSION_CONTROLLER);
        });
  }

  public static Performable createsAFolderOnTheFolderController(Object body) {
    return Task.where(
        "{0} Creates a folder.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post(CREATES_A_FOLDER_ON_THE_FOLDER_CONTROLLER);
        });
  }

  public static Performable searchUsingGetOnTheFolderContentsController(
      String searchTerm,
      String nodeType,
      String path,
      String pageSize,
      String page,
      String filter) {
    return Task.where(
        "{0} Search Nodes by Name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("nodeType", nodeType)
              .queryParam("path", path)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(SEARCH_USING_GET_ON_THE_FOLDER_CONTENTS_CONTROLLER);
        });
  }

  public static Performable createASignatureUsingAMultipartFromPostOnTheSignatureController(
      String signature, File file) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Create a signature using a multi-part from POST",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .queryParam("signature", signature)
              .multiPart("file", file, mime)
              .post(CREATE_A_SIGNATURE_USING_A_MULTIPART_FROM_POST_ON_THE_SIGNATURE_CONTROLLER);
        });
  }

  public static Performable getContentsUsingGetOnTheFolderContentsController(
      String searchTerm,
      String nodeType,
      String pageSize,
      String page,
      String filter,
      String path) {
    return Task.where(
        "{0} Gets the contents of a path.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("nodeType", nodeType)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .queryParam("path", path)
              .get(GET_CONTENTS_USING_GET_ON_THE_FOLDER_CONTENTS_CONTROLLER);
        });
  }

  public static Performable getsTheMetadataOfTheDocumentForTheGivenIdOnTheDocumentController(
      String id) {
    return Task.where(
        "{0} Gets the metadata of the document for the given ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GETS_THE_METADATA_OF_THE_DOCUMENT_FOR_THE_GIVEN_ID_ON_THE_DOCUMENT_CONTROLLER);
        });
  }

  public static Performable deletesADocumentByIdOnTheDocumentController(String id) {
    return Task.where(
        "{0} Deletes a document by ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETES_A_DOCUMENT_BY_ID_ON_THE_DOCUMENT_CONTROLLER);
        });
  }

  public static Performable getImagesUsingGetOnTheBrandingController(String imageType) {
    return Task.where(
        "{0} Gets images content per context.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("imageType", imageType)
              .get(GET_IMAGES_USING_GET_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable retrievesTheBytesForTheSpecifiedSignatureOnTheSignatureController(
      String id, String imageType) {
    return Task.where(
        "{0} Retrieves the bytes for the specified signature.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("imageType", imageType)
              .get(RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_SIGNATURE_ON_THE_SIGNATURE_CONTROLLER);
        });
  }

  public static Performable getBrandingsUsingGetOnTheBrandingController() {
    return Task.where(
        "{0} Gets the metadata of the brandings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get(GET_BRANDINGS_USING_GET_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable getUsingGetOnTheFolderController(String id) {
    return Task.where(
        "{0} Gets a folder with the specified ID (metadata only).",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_USING_GET_ON_THE_FOLDER_CONTROLLER);
        });
  }

  public static Performable updatesAFolderOnTheFolderController(String id, Object body) {
    return Task.where(
        "{0} Updates a folder",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put(UPDATES_A_FOLDER_ON_THE_FOLDER_CONTROLLER);
        });
  }

  public static Performable getByIdUsingGetOnTheBrandingController(String id) {
    return Task.where(
        "{0} Gets the metadata of the branding for the given ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GET_BY_ID_USING_GET_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable deleteByIdUsingDeleteOnTheBrandingController(String id) {
    return Task.where(
        "{0} Deletes a branding by ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETE_BY_ID_USING_DELETE_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable getsTheMetadataOfTheSignatureForTheGivenIdOnTheSignatureController(
      String id) {
    return Task.where(
        "{0} Gets the metadata of the signature for the given ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get(GETS_THE_METADATA_OF_THE_SIGNATURE_FOR_THE_GIVEN_ID_ON_THE_SIGNATURE_CONTROLLER);
        });
  }

  public static Performable deletesASignatureByIdOnTheSignatureController(String id) {
    return Task.where(
        "{0} Deletes a signature by ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETES_A_SIGNATURE_BY_ID_ON_THE_SIGNATURE_CONTROLLER);
        });
  }

  public static Performable getSignaturesUsingGetOnTheSignatureController() {
    return Task.where(
        "{0} Gets the metadata of the signatures",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get(GET_SIGNATURES_USING_GET_ON_THE_SIGNATURE_CONTROLLER);
        });
  }

  public static Performable deleteUsingDeleteOnTheFolderController(String id) {
    return Task.where(
        "{0} Deletes a single folder by path.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete(DELETE_USING_DELETE_ON_THE_FOLDER_CONTROLLER);
        });
  }

  public static Performable updatesASignatureOnTheSignatureController(
      String signature, File file, String id) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Updates a signature.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .queryParam("signature", signature)
              .multiPart("file", file, mime)
              .pathParam("id", id)
              .put(UPDATES_A_SIGNATURE_ON_THE_SIGNATURE_CONTROLLER);
        });
  }

  public static Performable updateUsingPutOnTheBrandingController(
      String branding, File file, String id) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Updates a branding.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .queryParam("branding", branding)
              .multiPart("file", file, mime)
              .pathParam("id", id)
              .put(UPDATE_USING_PUT_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable createUsingPostOnTheBrandingController(String branding, File file) {
    String mime = URLConnection.guessContentTypeFromName(file.getName());
    return Task.where(
        "{0} Create a branding using a multi-part from POST",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("multipart/form-data")
              .queryParam("branding", branding)
              .multiPart("file", file, mime)
              .post(CREATE_USING_POST_ON_THE_BRANDING_CONTROLLER);
        });
  }

  public static Performable downloadByVersionUsingGetOnTheBytesController(
      String documentId, String versionId) {
    return Task.where(
        "{0} Retrieves the bytes for the specified document and version.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("documentId", documentId)
              .pathParam("versionId", versionId)
              .get(DOWNLOAD_BY_VERSION_USING_GET_ON_THE_BYTES_CONTROLLER);
        });
  }

  public static Performable getDocumentVersionByIdUsingGetOnTheVersionController(
      String documentId, String id) {
    return Task.where(
        "{0} Get all of the metadata for a specific document by version",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("documentId", documentId)
              .pathParam("id", id)
              .get(GET_DOCUMENT_VERSION_BY_ID_USING_GET_ON_THE_VERSION_CONTROLLER);
        });
  }
}
