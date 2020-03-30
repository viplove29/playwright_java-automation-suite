package com.vertafore.test.tasks.servicewrappers.document;

import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:52 */
public class UseDocumentServiceTo {

  private static final String THIS_SERVICE = "document";

  public static Performable deletesADocumentByIdOnTheDocumentController(String id) {
    return Task.where(
        "{0} Deletes a document by ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("documents/{id}");
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
              .post("signatures");
        });
  }

  public static Performable getSignaturesUsingGetOnTheSignatureController() {
    return Task.where(
        "{0} Gets the metadata of the signatures",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("signatures");
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
              .get("documents/{id}/versions");
        });
  }

  public static Performable downloadByIdsUsingGetOnTheBytesController(String filter, String ids) {
    return Task.where(
        "{0} Retrieves the bytes for the specified paths. Will return a zip file if more than one is given.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("filter", filter)
              .queryParam("ids", ids)
              .get("bytes?filter=byIds");
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
              .post("brandings");
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
              .get("signatures/{id}/bytes");
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
              .post("documents");
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
              .put("documents/{id}");
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
              .get("brandings/{id}/bytes");
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
              .get("hierarchy?filter=byPath");
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
              .get("search?filter=byPathAndSearchTerm");
        });
  }

  public static Performable getUsingGetOnTheFolderController(String id) {
    return Task.where(
        "{0} Gets a folder with the specified ID (metadata only).",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("folders/{id}/");
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
              .get("documents/{documentId}/versions/{id}");
        });
  }

  public static Performable createsAFolderOnTheFolderController(Object body) {
    return Task.where(
        "{0} Creates a folder.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("folders");
        });
  }

  public static Performable deleteUsingDeleteOnTheFolderController(String id) {
    return Task.where(
        "{0} Deletes a single folder by path.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("folders/{id}");
        });
  }

  public static Performable getImagesUsingGetOnTheBrandingController(String imageType) {
    return Task.where(
        "{0} Gets images content per context.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("imageType", imageType)
              .get("brandings/bytes");
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
              .get("contents?filter=byPath");
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
              .get("signatures/{id}");
        });
  }

  public static Performable deleteByIdUsingDeleteOnTheBrandingController(String id) {
    return Task.where(
        "{0} Deletes a branding by ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("brandings/{id}");
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
              .put("folders/{id}/");
        });
  }

  public static Performable getBrandingsUsingGetOnTheBrandingController() {
    return Task.where(
        "{0} Gets the metadata of the brandings",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .get("brandings");
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
              .get("documents/{id}");
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
              .put("signatures/{id}");
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
              .get("bytes/{documentId}/versions/{versionId}");
        });
  }

  public static Performable deletesASignatureByIdOnTheSignatureController(String id) {
    return Task.where(
        "{0} Deletes a signature by ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .delete("signatures/{id}");
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
              .put("brandings/{id}");
        });
  }

  public static Performable getByIdUsingGetOnTheBrandingController(String id) {
    return Task.where(
        "{0} Gets the metadata of the branding for the given ID.",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("brandings/{id}");
        });
  }
}
