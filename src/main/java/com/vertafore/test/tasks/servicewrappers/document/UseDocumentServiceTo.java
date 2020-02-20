package com.vertafore.test.tasks.servicewrappers.document;

import static com.vertafore.test.abilities.CallTitanApi.as;
import static net.serenitybdd.rest.SerenityRest.rest;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.io.File;

public class UseDocumentServiceTo {

	private static final String DOWNLOAD_BY_IDS_USING_GET = "bytes?filter=byIds{&ids}";
	private static final String GET_HIERARCHY_USING_GET = "hierarchy?filter=byPath{&path}";
	private static final String UPDATE_USING_PUT_1 = "documents/{id}";
	private static final String CREATE_USING_POST_1 = "documents";
	private static final String GET_IMAGE_USING_GET = "brandings/{id}/bytes";
	private static final String GET_DOCUMENT_VERSIONS_USING_GET = "documents/{id}/versions";
	private static final String CREATE_USING_POST_2 = "folders";
	private static final String SEARCH_USING_GET = "search?filter=byPathAndSearchTerm{&searchTerm,nodeType,path,pageSize,page}";
	private static final String CREATE_USING_POST_3 = "signatures";
	private static final String GET_CONTENTS_USING_GET = "contents?filter=byPath{&searchTerm,nodeType,pageSize,page,path}";
	private static final String GET_BY_ID_USING_GET_1 = "documents/{id}";
	private static final String DELETE_BY_ID_USING_DELETE_1 = "documents/{id}";
	private static final String GET_IMAGES_USING_GET = "brandings/bytes";
	private static final String GET_IMAGE_USING_GET_1 = "signatures/{id}/bytes";
	private static final String GET_BRANDINGS_USING_GET = "brandings";
	private static final String GET_USING_GET = "folders/{id}/";
	private static final String UPDATE_USING_PUT_2 = "folders/{id}/";
	private static final String GET_BY_ID_USING_GET = "brandings/{id}";
	private static final String DELETE_BY_ID_USING_DELETE = "brandings/{id}";
	private static final String GET_BY_ID_USING_GET_2 = "signatures/{id}";
	private static final String DELETE_BY_ID_USING_DELETE_2 = "signatures/{id}";
	private static final String GET_SIGNATURES_USING_GET = "signatures";
	private static final String DELETE_USING_DELETE = "folders/{id}";
	private static final String UPDATE_USING_PUT_3 = "signatures/{id}";
	private static final String UPDATE_USING_PUT = "brandings/{id}";
	private static final String CREATE_USING_POST = "brandings";
	private static final String DOWNLOAD_BY_VERSION_USING_GET = "bytes/{documentId}/versions/{versionId}";
	private static final String GET_DOCUMENT_VERSION_BY_ID_USING_GET = "documents/{documentId}/versions/{id}";

	public static Performable downloadByIdsUsingGet(String filter, String ids){
		return Task.where(
		"{0} Retrieves the bytes for the specified paths. Will return a zip file if more than one is given.", 
			actor -> {
				rest().with().contentType("application/json").queryParam("filter", filter).queryParam("ids", ids).get(as(actor).toEndpoint(DOWNLOAD_BY_IDS_USING_GET));
			}
		);
	}

	public static Performable getHierarchyUsingGet(String filter, String path){
		return Task.where(
		"{0} Gets the hierarchy for a path.", 
			actor -> {
				rest().with().contentType("application/json").queryParam("filter", filter).queryParam("path", path).get(as(actor).toEndpoint(GET_HIERARCHY_USING_GET));
			}
		);
	}

	public static Performable updateUsingPut_1(String document, File file, String id){
		return Task.where(
		"{0} Updates a document.", 
			actor -> {
				rest().with().contentType("multipart/form-data").queryParam("document", document).multiPart("file", file).pathParam("id", id).put(as(actor).toEndpoint(UPDATE_USING_PUT_1));
			}
		);
	}

	public static Performable createUsingPost_1(String document, File file){
		return Task.where(
		"{0} Create a file using a multi-part form POST.", 
			actor -> {
				rest().with().contentType("multipart/form-data").queryParam("document", document).multiPart("file", file).post(as(actor).toEndpoint(CREATE_USING_POST_1));
			}
		);
	}

	public static Performable getImageUsingGet(String id, String imageType){
		return Task.where(
		"{0} Retrieves the bytes for the specified branding.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).queryParam("imageType", imageType).get(as(actor).toEndpoint(GET_IMAGE_USING_GET));
			}
		);
	}

	public static Performable getDocumentVersionsUsingGet(String id, String pageSize, String page){
		return Task.where(
		"{0} Get all of the metadata for a specific document", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get(as(actor).toEndpoint(GET_DOCUMENT_VERSIONS_USING_GET));
			}
		);
	}

	public static Performable createUsingPost_2(Object body){
		return Task.where(
		"{0} Creates a folder.", 
			actor -> {
				rest().with().contentType("application/json").body(body).post(as(actor).toEndpoint(CREATE_USING_POST_2));
			}
		);
	}

	public static Performable searchUsingGet(String searchTerm, String nodeType, String path, String pageSize, String page, String filter){
		return Task.where(
		"{0} Search Nodes by Name", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("nodeType", nodeType).queryParam("path", path).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(as(actor).toEndpoint(SEARCH_USING_GET));
			}
		);
	}

	public static Performable createUsingPost_3(String signature, File file){
		return Task.where(
		"{0} Create a signature using a multi-part from POST", 
			actor -> {
				rest().with().contentType("multipart/form-data").queryParam("signature", signature).multiPart("file", file).post(as(actor).toEndpoint(CREATE_USING_POST_3));
			}
		);
	}

	public static Performable getContentsUsingGet(String searchTerm, String nodeType, String pageSize, String page, String filter, String path){
		return Task.where(
		"{0} Gets the contents of a path.", 
			actor -> {
				rest().with().contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("nodeType", nodeType).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).queryParam("path", path).get(as(actor).toEndpoint(GET_CONTENTS_USING_GET));
			}
		);
	}

	public static Performable getByIdUsingGet_1(String id){
		return Task.where(
		"{0} Gets the metadata of the document for the given ID.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_BY_ID_USING_GET_1));
			}
		);
	}

	public static Performable deleteByIdUsingDelete_1(String id){
		return Task.where(
		"{0} Deletes a document by ID.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_BY_ID_USING_DELETE_1));
			}
		);
	}

	public static Performable getImagesUsingGet(String imageType){
		return Task.where(
		"{0} Gets images content per context.", 
			actor -> {
				rest().with().contentType("application/json").queryParam("imageType", imageType).get(as(actor).toEndpoint(GET_IMAGES_USING_GET));
			}
		);
	}

	public static Performable getImageUsingGet_1(String id, String imageType){
		return Task.where(
		"{0} Retrieves the bytes for the specified signature.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).queryParam("imageType", imageType).get(as(actor).toEndpoint(GET_IMAGE_USING_GET_1));
			}
		);
	}

	public static Performable getBrandingsUsingGet(){
		return Task.where(
		"{0} Gets the metadata of the brandings", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(GET_BRANDINGS_USING_GET));
			}
		);
	}

	public static Performable getUsingGet(String id){
		return Task.where(
		"{0} Gets a folder with the specified ID (metadata only).", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_USING_GET));
			}
		);
	}

	public static Performable updateUsingPut_2(String id, Object body){
		return Task.where(
		"{0} Updates a folder", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).body(body).put(as(actor).toEndpoint(UPDATE_USING_PUT_2));
			}
		);
	}

	public static Performable getByIdUsingGet(String id){
		return Task.where(
		"{0} Gets the metadata of the branding for the given ID.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_BY_ID_USING_GET));
			}
		);
	}

	public static Performable deleteByIdUsingDelete(String id){
		return Task.where(
		"{0} Deletes a branding by ID.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_BY_ID_USING_DELETE));
			}
		);
	}

	public static Performable getByIdUsingGet_2(String id){
		return Task.where(
		"{0} Gets the metadata of the signature for the given ID.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).get(as(actor).toEndpoint(GET_BY_ID_USING_GET_2));
			}
		);
	}

	public static Performable deleteByIdUsingDelete_2(String id){
		return Task.where(
		"{0} Deletes a signature by ID.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_BY_ID_USING_DELETE_2));
			}
		);
	}

	public static Performable getSignaturesUsingGet(){
		return Task.where(
		"{0} Gets the metadata of the signatures", 
			actor -> {
				rest().with().contentType("application/json").get(as(actor).toEndpoint(GET_SIGNATURES_USING_GET));
			}
		);
	}

	public static Performable deleteUsingDelete(String id){
		return Task.where(
		"{0} Deletes a single folder by path.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("id", id).delete(as(actor).toEndpoint(DELETE_USING_DELETE));
			}
		);
	}

	public static Performable updateUsingPut_3(String signature, File file, String id){
		return Task.where(
		"{0} Updates a signature.", 
			actor -> {
				rest().with().contentType("multipart/form-data").queryParam("signature", signature).multiPart("file", file).pathParam("id", id).put(as(actor).toEndpoint(UPDATE_USING_PUT_3));
			}
		);
	}

	public static Performable updateUsingPut(String branding, File file, String id){
		return Task.where(
		"{0} Updates a branding.", 
			actor -> {
				rest().with().contentType("multipart/form-data").queryParam("branding", branding).multiPart("file", file).pathParam("id", id).put(as(actor).toEndpoint(UPDATE_USING_PUT));
			}
		);
	}

	public static Performable createUsingPost(String branding, File file){
		return Task.where(
		"{0} Create a branding using a multi-part from POST", 
			actor -> {
				rest().with().contentType("multipart/form-data").queryParam("branding", branding).multiPart("file", file).post(as(actor).toEndpoint(CREATE_USING_POST));
			}
		);
	}

	public static Performable downloadByVersionUsingGet(String documentId, String versionId){
		return Task.where(
		"{0} Retrieves the bytes for the specified document and version.", 
			actor -> {
				rest().with().contentType("application/json").pathParam("documentId", documentId).pathParam("versionId", versionId).get(as(actor).toEndpoint(DOWNLOAD_BY_VERSION_USING_GET));
			}
		);
	}

	public static Performable getDocumentVersionByIdUsingGet(String documentId, String id){
		return Task.where(
		"{0} Get all of the metadata for a specific document by version", 
			actor -> {
				rest().with().contentType("application/json").pathParam("documentId", documentId).pathParam("id", id).get(as(actor).toEndpoint(GET_DOCUMENT_VERSION_BY_ID_USING_GET));
			}
		);
	}

}
