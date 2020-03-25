package com.vertafore.test.tasks.servicewrappers.render;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;
import java.io.File;
import java.net.URLConnection;

/**
* This class was automatically generated on 2020/03/25 13:40:53

*/
public class UseRenderServiceTo {

	private static final String THIS_SERVICE = "render";
	private static final String FIND_ALL_EMAIL_TEMPLATES_USING_GET_ON_THE_EMAIL_TEMPLATE_CONTROLLER = "email-templates";
	private static final String GET_EMAIL_TEMPLATE_USING_GET_ON_THE_EMAIL_TEMPLATE_CONTROLLER = "email-templates/{id}";
	private static final String DELETE_EMAIL_TEMPLATE_USING_DELETE_ON_THE_EMAIL_TEMPLATE_CONTROLLER = "email-templates/{id}";
	private static final String UPDATE_EMAIL_TEMPLATE_USING_PUT_ON_THE_EMAIL_TEMPLATE_CONTROLLER = "email-templates/{id}";
	private static final String GET_SIGNATURE_FOR_CURRENT_USER_USING_GET_ON_THE_SIGNATURE_CONTROLLER = "/render/v1/{productId}/{tenantId}/entities/";
	private static final String SEARCH_EMAIL_TEMPLATES_USING_GET_ON_THE_EMAIL_TEMPLATE_CONTROLLER = "email-templates?filter=byEmailPurposeAndTemplateName";
	private static final String RENDER_TEMPLATE_USING_POST_ON_THE_DOCUMENT_CONTROLLER = "documents?scope=ancestors";
	private static final String RENDER_A_TEMPLATE_ON_THE_DOCUMENT_CONTROLLER = "documents";
	private static final String CREATE_EMAIL_TEMPLATE_USING_POST_ON_THE_EMAIL_TEMPLATE_CONTROLLER = "email-templates";
	private static final String CREATE_TEMPLATE_USING_POST_ON_THE_TEMPLATE_CONTROLLER = "templates";
	private static final String CREATE_SIGNATURE_USING_POST_ON_THE_SIGNATURE_CONTROLLER = "signatures";
	private static final String GET_SIGNATURE_USING_GET_ON_THE_SIGNATURE_CONTROLLER = "signatures/{id}";
	private static final String DELETE_SIGNATURE_USING_DELETE_ON_THE_SIGNATURE_CONTROLLER = "signatures/{id}";
	private static final String UPDATE_SIGNATURE_USING_PUT_ON_THE_SIGNATURE_CONTROLLER = "signatures/{id}";
	private static final String GET_PDF_BY_TEMPLATE_ID_USING_GET_ON_THE_TEMPLATE_CONTROLLER = "templates/{id}";

	public static Performable findAllEmailTemplatesUsingGetOnTheEmailTemplateController(String pageSize, String page){
		return Task.where(
		"{0} Get all email templates", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(FIND_ALL_EMAIL_TEMPLATES_USING_GET_ON_THE_EMAIL_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable getEmailTemplateUsingGetOnTheEmailTemplateController(String id){
		return Task.where(
		"{0} Get an email template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_EMAIL_TEMPLATE_USING_GET_ON_THE_EMAIL_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable deleteEmailTemplateUsingDeleteOnTheEmailTemplateController(String id){
		return Task.where(
		"{0} Delete email template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_EMAIL_TEMPLATE_USING_DELETE_ON_THE_EMAIL_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable updateEmailTemplateUsingPutOnTheEmailTemplateController(String id, Object body){
		return Task.where(
		"{0} Update email template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_EMAIL_TEMPLATE_USING_PUT_ON_THE_EMAIL_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable getSignatureForCurrentUserUsingGetOnTheSignatureController(String filterBy){
		return Task.where(
		"{0} Get Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("filterBy", filterBy).get(GET_SIGNATURE_FOR_CURRENT_USER_USING_GET_ON_THE_SIGNATURE_CONTROLLER);
			}
		);
	}

	public static Performable searchEmailTemplatesUsingGetOnTheEmailTemplateController(String pageSize, String page, String emailPurpose, String templateName, String filter){
		return Task.where(
		"{0} Search by template name and filter by email purpose", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("emailPurpose", emailPurpose).queryParam("templateName", templateName).queryParam("filter", filter).get(SEARCH_EMAIL_TEMPLATES_USING_GET_ON_THE_EMAIL_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable renderTemplateUsingPostOnTheDocumentController(Object body, String scope){
		return Task.where(
		"{0} Render a template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).queryParam("scope", scope).post(RENDER_TEMPLATE_USING_POST_ON_THE_DOCUMENT_CONTROLLER);
			}
		);
	}

	public static Performable renderATemplateOnTheDocumentController(Object body){
		return Task.where(
		"{0} Render a template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(RENDER_A_TEMPLATE_ON_THE_DOCUMENT_CONTROLLER);
			}
		);
	}

	public static Performable createEmailTemplateUsingPostOnTheEmailTemplateController(Object body){
		return Task.where(
		"{0} Create Email Template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_EMAIL_TEMPLATE_USING_POST_ON_THE_EMAIL_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable createTemplateUsingPostOnTheTemplateController(String template, File file){
		String mime = URLConnection.guessContentTypeFromName(file.getName());return Task.where(
		"{0} Create Template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("multipart/form-data").queryParam("template", template).multiPart("file", file , mime).post(CREATE_TEMPLATE_USING_POST_ON_THE_TEMPLATE_CONTROLLER);
			}
		);
	}

	public static Performable createSignatureUsingPostOnTheSignatureController(Object body){
		return Task.where(
		"{0} Create Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_SIGNATURE_USING_POST_ON_THE_SIGNATURE_CONTROLLER);
			}
		);
	}

	public static Performable getSignatureUsingGetOnTheSignatureController(String id){
		return Task.where(
		"{0} Get Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_SIGNATURE_USING_GET_ON_THE_SIGNATURE_CONTROLLER);
			}
		);
	}

	public static Performable deleteSignatureUsingDeleteOnTheSignatureController(String id){
		return Task.where(
		"{0} Delete signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete(DELETE_SIGNATURE_USING_DELETE_ON_THE_SIGNATURE_CONTROLLER);
			}
		);
	}

	public static Performable updateSignatureUsingPutOnTheSignatureController(String id, Object body){
		return Task.where(
		"{0} Replace Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_SIGNATURE_USING_PUT_ON_THE_SIGNATURE_CONTROLLER);
			}
		);
	}

	public static Performable getPdfByTemplateIdUsingGetOnTheTemplateController(String id){
		return Task.where(
		"{0} Get PDF by Template Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_PDF_BY_TEMPLATE_ID_USING_GET_ON_THE_TEMPLATE_CONTROLLER);
			}
		);
	}



}
