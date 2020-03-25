package com.vertafore.test.tasks.servicewrappers.fileintake;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;

/**
* This class was automatically generated on 2020/03/25 13:40:46

*/
public class UseFileIntakeServiceTo {

	private static final String THIS_SERVICE = "file-intake";
	private static final String FIND_RECORDS_BY_INTAKE_ID_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER = "files/{fileIntakeId}/records";
	private static final String CREATE_FILE_INTAKE_USING_POST_ON_THE_FILE_INTAKE_CONTROLLER = "files";
	private static final String FIND_FILE_HEADER_MAPPINGS_BY_FILE_INTAKE_ID_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER = "files/{id}/mappings";
	private static final String UPDATE_FILE_RECORD_USING_PATCH_ON_THE_FILE_INTAKE_CONTROLLER = "files/{fileId}/records/{recordId}";
	private static final String GET_AL3_DOCUMENT_USING_GET_ON_THE_AL3_CONTROLLER = "al3documents/{id}";
	private static final String GET_FILE_INTAKE_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER = "files/{id}";
	private static final String DELETE_FILE_INTAKE_USING_DELETE_ON_THE_FILE_INTAKE_CONTROLLER = "files/{id}";
	private static final String CREATE_TRANSLATIONS_USING_POST_ON_THE_AL3_CONTROLLER = "translations";
	private static final String CREATE_AL3_DOCUMENT_USING_POST_ON_THE_AL3_CONTROLLER = "al3documents";
	private static final String GET_FILE_INTAKE_ERRORS_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER = "files/{fileIntakeId}/errors";
	private static final String FIND_AL3_TRANSACTIONS_BY_CARRIER_UPDATE_ID_USING_GET_ON_THE_AL3_CONTROLLER = "al3documents/{id}/al3transactions";
	private static final String UPDATE_FILE_HEADER_MAPPINGS_USING_PUT_ON_THE_FILE_INTAKE_CONTROLLER = "files/{fileIntakeId}/mappings";

	public static Performable findRecordsByIntakeIdUsingGetOnTheFileIntakeController(String fileIntakeId, String skipImport, String pageSize, String page){
		return Task.where(
		"{0} Find File Records by File intake ID", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("fileIntakeId", fileIntakeId).queryParam("skipImport", skipImport).queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_RECORDS_BY_INTAKE_ID_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable createFileIntakeUsingPostOnTheFileIntakeController(String dataType, File file, String withHeaders){
		String mime = URLConnection.guessContentTypeFromName(file.getName());return Task.where(
		"{0} Create File Intake", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("multipart/form-data").queryParam("dataType", dataType).multiPart("file", file , mime).queryParam("withHeaders", withHeaders).post(CREATE_FILE_INTAKE_USING_POST_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable findFileHeaderMappingsByFileIntakeIdUsingGetOnTheFileIntakeController(String id){
		return Task.where(
		"{0} Get File Intake Header Mapping", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(FIND_FILE_HEADER_MAPPINGS_BY_FILE_INTAKE_ID_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable updateFileRecordUsingPatchOnTheFileIntakeController(String fileId, String recordId, Object body){
		return Task.where(
		"{0} Replace File Record", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("fileId", fileId).pathParam("recordId", recordId).body(body).patch(UPDATE_FILE_RECORD_USING_PATCH_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable getAl3DocumentUsingGetOnTheAl3Controller(String id){
		return Task.where(
		"{0} Get AL3 Document Metadata", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_AL3_DOCUMENT_USING_GET_ON_THE_AL3_CONTROLLER);
			}
		);
	}

	public static Performable getFileIntakeUsingGetOnTheFileIntakeController(String id){
		return Task.where(
		"{0} Get File Intake", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_FILE_INTAKE_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable deleteFileIntakeUsingDeleteOnTheFileIntakeController(String id){
		return Task.where(
		"{0} Delete File Intake", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_FILE_INTAKE_USING_DELETE_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable createTranslationsUsingPostOnTheAl3Controller(File file){
		String mime = URLConnection.guessContentTypeFromName(file.getName());return Task.where(
		"{0} Parse AL3 File only and return translated output", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("multipart/form-data").multiPart("al3File", al3File , mime).post(CREATE_TRANSLATIONS_USING_POST_ON_THE_AL3_CONTROLLER);
			}
		);
	}

	public static Performable createAl3DocumentUsingPostOnTheAl3Controller(String al3File){
		String mime = URLConnection.guessContentTypeFromName(file.getName());return Task.where(
		"{0} Create Al3 Document Request", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("multipart/form-data").multiPart("al3File", al3File ).post(CREATE_AL3_DOCUMENT_USING_POST_ON_THE_AL3_CONTROLLER);
			}
		);
	}

	public static Performable getFileIntakeErrorsUsingGetOnTheFileIntakeController(String fileIntakeId){
		return Task.where(
		"{0} Find Mapped Errors", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("fileIntakeId", fileIntakeId).get(GET_FILE_INTAKE_ERRORS_USING_GET_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}

	public static Performable findAl3TransactionsByCarrierUpdateIdUsingGetOnTheAl3Controller(String id, String pageSize, String page){
		return Task.where(
		"{0} Retrieve AL3 Transactions by AL3 Document Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_AL3_TRANSACTIONS_BY_CARRIER_UPDATE_ID_USING_GET_ON_THE_AL3_CONTROLLER);
			}
		);
	}

	public static Performable updateFileHeaderMappingsUsingPutOnTheFileIntakeController(String fileIntakeId, Object body){
		return Task.where(
		"{0} Replace File Header Mappings", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("fileIntakeId", fileIntakeId).body(body).put(UPDATE_FILE_HEADER_MAPPINGS_USING_PUT_ON_THE_FILE_INTAKE_CONTROLLER);
			}
		);
	}



}
