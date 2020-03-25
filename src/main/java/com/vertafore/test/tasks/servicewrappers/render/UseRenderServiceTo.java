package com.vertafore.test.tasks.servicewrappers.render;

import net.serenitybdd.screenplay.Performable;
import java.io.File;
import java.net.URLConnection;
import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Task;

/**
* This class was automatically generated on 2020/03/25 14:48:20

*/
public class UseRenderServiceTo {

	private static final String THIS_SERVICE = "render";

	public static Performable renderTemplateUsingPostOnTheDocumentController(Object body, String scope){
		return Task.where(
		"{0} Render a template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).queryParam("scope", scope).post("documents?scope=ancestors");
			}
		);
	}

	public static Performable searchEmailTemplatesUsingGetOnTheEmailTemplateController(String pageSize, String page, String emailPurpose, String templateName, String filter){
		return Task.where(
		"{0} Search by template name and filter by email purpose", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("emailPurpose", emailPurpose).queryParam("templateName", templateName).queryParam("filter", filter).get("email-templates?filter=byEmailPurposeAndTemplateName");
			}
		);
	}

	public static Performable updateEmailTemplateUsingPutOnTheEmailTemplateController(String id, Object body){
		return Task.where(
		"{0} Update email template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("email-templates/{id}");
			}
		);
	}

	public static Performable createEmailTemplateUsingPostOnTheEmailTemplateController(Object body){
		return Task.where(
		"{0} Create Email Template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("email-templates");
			}
		);
	}

	public static Performable getPdfByTemplateIdUsingGetOnTheTemplateController(String id){
		return Task.where(
		"{0} Get PDF by Template Id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("templates/{id}");
			}
		);
	}

	public static Performable getEmailTemplateUsingGetOnTheEmailTemplateController(String id){
		return Task.where(
		"{0} Get an email template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("email-templates/{id}");
			}
		);
	}

	public static Performable getSignatureUsingGetOnTheSignatureController(String id){
		return Task.where(
		"{0} Get Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get("signatures/{id}");
			}
		);
	}

	public static Performable deleteEmailTemplateUsingDeleteOnTheEmailTemplateController(String id){
		return Task.where(
		"{0} Delete email template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("email-templates/{id}");
			}
		);
	}

	public static Performable findAllEmailTemplatesUsingGetOnTheEmailTemplateController(String pageSize, String page){
		return Task.where(
		"{0} Get all email templates", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get("email-templates");
			}
		);
	}

	public static Performable renderATemplateOnTheDocumentController(Object body){
		return Task.where(
		"{0} Render a template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("documents");
			}
		);
	}

	public static Performable updateSignatureUsingPutOnTheSignatureController(String id, Object body){
		return Task.where(
		"{0} Replace Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put("signatures/{id}");
			}
		);
	}

	public static Performable deleteSignatureUsingDeleteOnTheSignatureController(String id){
		return Task.where(
		"{0} Delete signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).delete("signatures/{id}");
			}
		);
	}

	public static Performable getSignatureForCurrentUserUsingGetOnTheSignatureController(String filterBy){
		return Task.where(
		"{0} Get Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("filterBy", filterBy).get("/render/v1/{productId}/{tenantId}/entities/");
			}
		);
	}

	public static Performable createTemplateUsingPostOnTheTemplateController(String template, File file){
		String mime = URLConnection.guessContentTypeFromName(file.getName());return Task.where(
		"{0} Create Template", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("multipart/form-data").queryParam("template", template).multiPart("File", file , mime).post("templates");
			}
		);
	}

	public static Performable createSignatureUsingPostOnTheSignatureController(Object body){
		return Task.where(
		"{0} Create Email signature", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post("signatures");
			}
		);
	}



}
