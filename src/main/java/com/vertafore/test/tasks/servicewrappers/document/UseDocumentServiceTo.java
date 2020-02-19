package com.vertafore.test.tasks.servicewrappers.document;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;

import java.io.File;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class UseDocumentServiceTo {

  private static final String
      RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_PATHS_WILL_RETURN_A_ZIP_FILE_IF_MORE_THAN_ONE_IS_GIVEN =
          "bytes?filter=byIds{&ids}";
  private static final String GETS_THE_HIERARCHY_FOR_A_PATH = "hierarchy?filter=byPath{&path}";
  private static final String UPDATES_A_DOCUMENT = "documents/{id}";
  private static final String CREATE_A_FILE_USING_A_MULTIPART_FORM_POST = "documents";
  private static final String RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_BRANDING =
      "brandings/{id}/bytes";
  private static final String GET_ALL_OF_THE_METADATA_FOR_A_SPECIFIC_DOCUMENT =
      "documents/{id}/versions";
  private static final String CREATES_A_FOLDER = "folders";
  private static final String SEARCH_NODES_BY_NAME =
      "search?filter=byPathAndSearchTerm{&searchTerm,nodeType,path,pageSize,page}";
  private static final String CREATE_A_SIGNATURE_USING_A_MULTIPART_FROM_POST = "signatures";
  private static final String GETS_THE_CONTENTS_OF_A_PATH =
      "contents?filter=byPath{&searchTerm,nodeType,pageSize,page,path}";
  private static final String GETS_THE_METADATA_OF_THE_DOCUMENT_FOR_THE_GIVEN_ID = "documents/{id}";
  private static final String DELETES_A_DOCUMENT_BY_ID = "documents/{id}";
  private static final String GETS_IMAGES_CONTENT_PER_CONTEXT = "brandings/bytes";
  private static final String RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_SIGNATURE =
      "signatures/{id}/bytes";
  private static final String GETS_THE_METADATA_OF_THE_BRANDINGS = "brandings";
  private static final String GETS_A_FOLDER_WITH_THE_SPECIFIED_ID_METADATA_ONLY = "folders/{id}/";
  private static final String UPDATES_A_FOLDER = "folders/{id}/";
  private static final String GETS_THE_METADATA_OF_THE_BRANDING_FOR_THE_GIVEN_ID = "brandings/{id}";
  private static final String DELETES_A_BRANDING_BY_ID = "brandings/{id}";
  private static final String GETS_THE_METADATA_OF_THE_SIGNATURE_FOR_THE_GIVEN_ID =
      "signatures/{id}";
  private static final String DELETES_A_SIGNATURE_BY_ID = "signatures/{id}";
  private static final String GETS_THE_METADATA_OF_THE_SIGNATURES = "signatures";
  private static final String DELETES_A_SINGLE_FOLDER_BY_PATH = "folders/{id}";
  private static final String UPDATES_A_SIGNATURE = "signatures/{id}";
  private static final String UPDATES_A_BRANDING = "brandings/{id}";
  private static final String CREATE_A_BRANDING_USING_A_MULTIPART_FROM_POST = "brandings";
  private static final String RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_DOCUMENT_AND_VERSION =
      "bytes/{documentId}/versions/{versionId}";
  private static final String GET_ALL_OF_THE_METADATA_FOR_A_SPECIFIC_DOCUMENT_BY_VERSION =
      "documents/{documentId}/versions/{id}";

  public static Performable
      retrievesTheBytesForTheSpecifiedPathsWillReturnAZipFileIfMoreThanOneIsGiven(
          String filter, String ids) {
    return Task.where(
        "{0} Retrieves the bytes for the specified paths. Will return a zip file if more than one is given.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("filter", filter)
              .queryParam("ids", ids)
              .get(
                  as(actor)
                      .toEndpoint(
                          RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_PATHS_WILL_RETURN_A_ZIP_FILE_IF_MORE_THAN_ONE_IS_GIVEN));
        });
  }

  public static Performable getsTheHierarchyForAPath(String filter, String path) {
    return Task.where(
        "{0} Gets the hierarchy for a path.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("filter", filter)
              .queryParam("path", path)
              .get(as(actor).toEndpoint(GETS_THE_HIERARCHY_FOR_A_PATH));
        });
  }

  public static Performable updatesADocument(String document, File file, String id) {
    return Task.where(
        "{0} Updates a document.",
        actor -> {
          rest()
              .with()
              .contentType("multipart/form-data")
              .queryParam("document", document)
              .multiPart("file", file)
              .pathParam("id", id)
              .put(as(actor).toEndpoint(UPDATES_A_DOCUMENT));
        });
  }

  public static Performable createAFileUsingAMultiPartFormPost(String document, File file) {
    return Task.where(
        "{0} Create a file using a multi-part form POST.",
        actor -> {
          rest()
              .with()
              .contentType("multipart/form-data")
              .queryParam("document", document)
              .multiPart("file", file)
              .post(as(actor).toEndpoint(CREATE_A_FILE_USING_A_MULTIPART_FORM_POST));
        });
  }

  public static Performable retrievesTheBytesForTheSpecifiedBranding(String id, String imageType) {
    return Task.where(
        "{0} Retrieves the bytes for the specified branding.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("imageType", imageType)
              .get(as(actor).toEndpoint(RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_BRANDING));
        });
  }

  public static Performable getAllOfTheMetadataForASpecificDocument(
      String id, String pageSize, String page) {
    return Task.where(
        "{0} Get all of the metadata for a specific document",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get(as(actor).toEndpoint(GET_ALL_OF_THE_METADATA_FOR_A_SPECIFIC_DOCUMENT));
        });
  }

  public static Performable createsAFolder(Object body) {
    return Task.where(
        "{0} Creates a folder.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .body(body)
              .post(as(actor).toEndpoint(CREATES_A_FOLDER));
        });
  }

  public static Performable searchNodesByName(
      String searchTerm,
      String nodeType,
      String path,
      String pageSize,
      String page,
      String filter) {
    return Task.where(
        "{0} Search Nodes by Name",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("nodeType", nodeType)
              .queryParam("path", path)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get(as(actor).toEndpoint(SEARCH_NODES_BY_NAME));
        });
  }

  public static Performable createASignatureUsingAMultiPartFromPost(String signature, File file) {
    return Task.where(
        "{0} Create a signature using a multi-part from POST",
        actor -> {
          rest()
              .with()
              .contentType("multipart/form-data")
              .queryParam("signature", signature)
              .multiPart("file", file)
              .post(as(actor).toEndpoint(CREATE_A_SIGNATURE_USING_A_MULTIPART_FROM_POST));
        });
  }

  public static Performable getsTheContentsOfAPath(
      String searchTerm,
      String nodeType,
      String pageSize,
      String page,
      String filter,
      String path) {
    return Task.where(
        "{0} Gets the contents of a path.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("searchTerm", searchTerm)
              .queryParam("nodeType", nodeType)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .queryParam("path", path)
              .get(as(actor).toEndpoint(GETS_THE_CONTENTS_OF_A_PATH));
        });
  }

  public static Performable getsTheMetadataOfTheDocumentForTheGivenId(String id) {
    return Task.where(
        "{0} Gets the metadata of the document for the given ID.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(GETS_THE_METADATA_OF_THE_DOCUMENT_FOR_THE_GIVEN_ID));
        });
  }

  public static Performable deletesADocumentById(String id) {
    return Task.where(
        "{0} Deletes a document by ID.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETES_A_DOCUMENT_BY_ID));
        });
  }

  public static Performable getsImagesContentPerContext(String imageType) {
    return Task.where(
        "{0} Gets images content per context.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .queryParam("imageType", imageType)
              .get(as(actor).toEndpoint(GETS_IMAGES_CONTENT_PER_CONTEXT));
        });
  }

  public static Performable retrievesTheBytesForTheSpecifiedSignature(String id, String imageType) {
    return Task.where(
        "{0} Retrieves the bytes for the specified signature.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .queryParam("imageType", imageType)
              .get(as(actor).toEndpoint(RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_SIGNATURE));
        });
  }

  public static Performable getsTheMetadataOfTheBrandings() {
    return Task.where(
        "{0} Gets the metadata of the brandings",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GETS_THE_METADATA_OF_THE_BRANDINGS));
        });
  }

  public static Performable getsAFolderWithTheSpecifiedIdMetadataOnly(String id) {
    return Task.where(
        "{0} Gets a folder with the specified ID (metadata only).",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(GETS_A_FOLDER_WITH_THE_SPECIFIED_ID_METADATA_ONLY));
        });
  }

  public static Performable updatesAFolder(String id, Object body) {
    return Task.where(
        "{0} Updates a folder",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put(as(actor).toEndpoint(UPDATES_A_FOLDER));
        });
  }

  public static Performable getsTheMetadataOfTheBrandingForTheGivenId(String id) {
    return Task.where(
        "{0} Gets the metadata of the branding for the given ID.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(GETS_THE_METADATA_OF_THE_BRANDING_FOR_THE_GIVEN_ID));
        });
  }

  public static Performable deletesABrandingById(String id) {
    return Task.where(
        "{0} Deletes a branding by ID.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETES_A_BRANDING_BY_ID));
        });
  }

  public static Performable getsTheMetadataOfTheSignatureForTheGivenId(String id) {
    return Task.where(
        "{0} Gets the metadata of the signature for the given ID.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .get(as(actor).toEndpoint(GETS_THE_METADATA_OF_THE_SIGNATURE_FOR_THE_GIVEN_ID));
        });
  }

  public static Performable deletesASignatureById(String id) {
    return Task.where(
        "{0} Deletes a signature by ID.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETES_A_SIGNATURE_BY_ID));
        });
  }

  public static Performable getsTheMetadataOfTheSignatures() {
    return Task.where(
        "{0} Gets the metadata of the signatures",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .get(as(actor).toEndpoint(GETS_THE_METADATA_OF_THE_SIGNATURES));
        });
  }

  public static Performable deletesASingleFolderByPath(String id) {
    return Task.where(
        "{0} Deletes a single folder by path.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("id", id)
              .delete(as(actor).toEndpoint(DELETES_A_SINGLE_FOLDER_BY_PATH));
        });
  }

  public static Performable updatesASignature(String signature, File file, String id) {
    return Task.where(
        "{0} Updates a signature.",
        actor -> {
          rest()
              .with()
              .contentType("multipart/form-data")
              .queryParam("signature", signature)
              .multiPart("file", file)
              .pathParam("id", id)
              .put(as(actor).toEndpoint(UPDATES_A_SIGNATURE));
        });
  }

  public static Performable updatesABranding(String branding, File file, String id) {
    return Task.where(
        "{0} Updates a branding.",
        actor -> {
          rest()
              .with()
              .contentType("multipart/form-data")
              .queryParam("branding", branding)
              .multiPart("file", file)
              .pathParam("id", id)
              .put(as(actor).toEndpoint(UPDATES_A_BRANDING));
        });
  }

  public static Performable createABrandingUsingAMultiPartFromPost(String branding, File file) {
    return Task.where(
        "{0} Create a branding using a multi-part from POST",
        actor -> {
          rest()
              .with()
              .contentType("multipart/form-data")
              .queryParam("branding", branding)
              .multiPart("file", file)
              .post(as(actor).toEndpoint(CREATE_A_BRANDING_USING_A_MULTIPART_FROM_POST));
        });
  }

  public static Performable retrievesTheBytesForTheSpecifiedDocumentAndVersion(
      String documentId, String versionId) {
    return Task.where(
        "{0} Retrieves the bytes for the specified document and version.",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("documentId", documentId)
              .pathParam("versionId", versionId)
              .get(
                  as(actor).toEndpoint(RETRIEVES_THE_BYTES_FOR_THE_SPECIFIED_DOCUMENT_AND_VERSION));
        });
  }

  public static Performable getAllOfTheMetadataForASpecificDocumentByVersion(
      String documentId, String id) {
    return Task.where(
        "{0} Get all of the metadata for a specific document by version",
        actor -> {
          rest()
              .with()
              .contentType("application/json")
              .pathParam("documentId", documentId)
              .pathParam("id", id)
              .get(
                  as(actor).toEndpoint(GET_ALL_OF_THE_METADATA_FOR_A_SPECIFIC_DOCUMENT_BY_VERSION));
        });
  }
}
