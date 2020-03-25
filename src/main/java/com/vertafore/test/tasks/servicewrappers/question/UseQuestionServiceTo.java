package com.vertafore.test.tasks.servicewrappers.question;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:50

*/
public class UseQuestionServiceTo {

	private static final String THIS_SERVICE = "question";
	private static final String GET_QUESTION_LIST_BY_SECTION_KEY_USING_GET_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists?filter=bySectionKey";
	private static final String GET_SECTION_BY_KEYS_USING_GET_ON_THE_SECTION_CONTROLLER = "sections?filter=byKeys";
	private static final String UPDATE_QUESTION_USING_PATCH_ON_THE_QUESTION_CONTROLLER = "questions/{key}";
	private static final String GET_QUESTION_USING_GET_ON_THE_QUESTION_CONTROLLER = "questions/{key}";
	private static final String DELETE_QUESTION_USING_DELETE_ON_THE_QUESTION_CONTROLLER = "questions/{key}";
	private static final String CREATE_QUESTION_LIST_USING_POST_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists";
	private static final String SEARCH_SECTIONS_USING_GET_ON_THE_SECTION_CONTROLLER = "sections";
	private static final String UPDATE_QUESTION_LIST_USING_PATCH_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists/{key}";
	private static final String GET_QUESTION_LIST_BY_KEY_USING_GET_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists/{key}";
	private static final String DELETE_QUESTION_LIST_USING_DELETE_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists/{key}";
	private static final String UPDATE_QUESTION_LIST_USING_PUT_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists/{key}";
	private static final String SEARCH_QUESTIONS_USING_GET_ON_THE_QUESTION_CONTROLLER = "questions";
	private static final String CREATE_QUESTION_USING_POST_ON_THE_QUESTION_CONTROLLER = "questions";
	private static final String UPDATE_SECTION_USING_PATCH_ON_THE_SECTION_CONTROLLER = "sections/{key}";
	private static final String GET_SECTION_USING_GET_ON_THE_SECTION_CONTROLLER = "sections/{key}";
	private static final String DELETE_SECTION_USING_DELETE_ON_THE_SECTION_CONTROLLER = "sections/{key}";
	private static final String UPDATE_SECTION_USING_PUT_ON_THE_SECTION_CONTROLLER = "sections/{key}";
	private static final String GET_QUESTION_BY_SECTION_KEY_USING_GET_ON_THE_QUESTION_CONTROLLER = "questions?filter=bySectionKey";
	private static final String GET_QUESTIONS_BY_KEYS_USING_GET_ON_THE_QUESTION_CONTROLLER = "questions?filter=byKeys";
	private static final String GET_QUESTION_LISTS_USING_GET_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists?filter=byKeys";
	private static final String GET_QUESTION_BY_QUESTION_LIST_KEY_USING_GET_ON_THE_QUESTION_CONTROLLER = "questions?filter=byQuestionListKey";
	private static final String CREATE_SECTION_USING_POST_ON_THE_SECTION_CONTROLLER = "sections";
	private static final String GET_QUESTION_LISTS_ON_THE_QUESTION_LIST_CONTROLLER = "question-lists";

	public static Performable getQuestionListBySectionKeyUsingGetOnTheQuestionListController(String sectionKey, String searchTerm, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get question lists by section key.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("sectionKey", sectionKey).queryParam("searchTerm", searchTerm).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_QUESTION_LIST_BY_SECTION_KEY_USING_GET_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getSectionByKeysUsingGetOnTheSectionController(String keys, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get sections.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("keys", keys).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_SECTION_BY_KEYS_USING_GET_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable updateQuestionUsingPatchOnTheQuestionController(String key, Object body){
		return Task.where(
		"{0} Update question.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("key", key).body(body).patch(UPDATE_QUESTION_USING_PATCH_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionUsingGetOnTheQuestionController(String key){
		return Task.where(
		"{0} Get question.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).get(GET_QUESTION_USING_GET_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable deleteQuestionUsingDeleteOnTheQuestionController(String key){
		return Task.where(
		"{0} Delete question.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).delete(DELETE_QUESTION_USING_DELETE_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable createQuestionListUsingPostOnTheQuestionListController(Object body){
		return Task.where(
		"{0} Create question list.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_QUESTION_LIST_USING_POST_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable searchSectionsUsingGetOnTheSectionController(String searchTerm, String pageSize, String page){
		return Task.where(
		"{0} Search sections.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("pageSize", pageSize).queryParam("page", page).get(SEARCH_SECTIONS_USING_GET_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable updateQuestionListUsingPatchOnTheQuestionListController(String key, Object body){
		return Task.where(
		"{0} Update question list.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("key", key).body(body).patch(UPDATE_QUESTION_LIST_USING_PATCH_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionListByKeyUsingGetOnTheQuestionListController(String key){
		return Task.where(
		"{0} Get question list.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).get(GET_QUESTION_LIST_BY_KEY_USING_GET_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable deleteQuestionListUsingDeleteOnTheQuestionListController(String key){
		return Task.where(
		"{0} Delete question list.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).delete(DELETE_QUESTION_LIST_USING_DELETE_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable updateQuestionListUsingPutOnTheQuestionListController(String key, Object body){
		return Task.where(
		"{0} Replace question list.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).body(body).put(UPDATE_QUESTION_LIST_USING_PUT_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable searchQuestionsUsingGetOnTheQuestionController(String searchTerm, String status, String pageSize, String page){
		return Task.where(
		"{0} Search questions.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("status", status).queryParam("pageSize", pageSize).queryParam("page", page).get(SEARCH_QUESTIONS_USING_GET_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable createQuestionUsingPostOnTheQuestionController(Object body){
		return Task.where(
		"{0} Create question.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_QUESTION_USING_POST_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable updateSectionUsingPatchOnTheSectionController(String key, Object body){
		return Task.where(
		"{0} Update section.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json-patch+json").pathParam("key", key).body(body).patch(UPDATE_SECTION_USING_PATCH_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable getSectionUsingGetOnTheSectionController(String key){
		return Task.where(
		"{0} Get section.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).get(GET_SECTION_USING_GET_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable deleteSectionUsingDeleteOnTheSectionController(String key){
		return Task.where(
		"{0} Delete section.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).delete(DELETE_SECTION_USING_DELETE_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable updateSectionUsingPutOnTheSectionController(String key, Object body){
		return Task.where(
		"{0} Replace section.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("key", key).body(body).put(UPDATE_SECTION_USING_PUT_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionBySectionKeyUsingGetOnTheQuestionController(String sectionKey, String searchTerm, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get questions by section key.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("sectionKey", sectionKey).queryParam("searchTerm", searchTerm).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_QUESTION_BY_SECTION_KEY_USING_GET_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionsByKeysUsingGetOnTheQuestionController(String keys, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get questions.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("keys", keys).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_QUESTIONS_BY_KEYS_USING_GET_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionListsUsingGetOnTheQuestionListController(String keys, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get question lists.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("keys", keys).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_QUESTION_LISTS_USING_GET_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionByQuestionListKeyUsingGetOnTheQuestionController(String questionListKey, String searchTerm, String pageSize, String page, String filter){
		return Task.where(
		"{0} Get questions by question list key.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("questionListKey", questionListKey).queryParam("searchTerm", searchTerm).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_QUESTION_BY_QUESTION_LIST_KEY_USING_GET_ON_THE_QUESTION_CONTROLLER);
			}
		);
	}

	public static Performable createSectionUsingPostOnTheSectionController(Object body){
		return Task.where(
		"{0} Create section.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_SECTION_USING_POST_ON_THE_SECTION_CONTROLLER);
			}
		);
	}

	public static Performable getQuestionListsOnTheQuestionListController(String searchTerm, String status, String pageSize, String page){
		return Task.where(
		"{0} Get question lists.", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("searchTerm", searchTerm).queryParam("status", status).queryParam("pageSize", pageSize).queryParam("page", page).get(GET_QUESTION_LISTS_ON_THE_QUESTION_LIST_CONTROLLER);
			}
		);
	}



}
