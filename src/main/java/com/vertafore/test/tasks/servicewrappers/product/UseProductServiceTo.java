package com.vertafore.test.tasks.servicewrappers.product;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import com.vertafore.test.abilities.CallTitanApi;

/**
* This class was automatically generated on 2020/03/25 13:40:49

*/
public class UseProductServiceTo {

	private static final String THIS_SERVICE = "product";
	private static final String GET_PRODUCTS_BY_PRODUCT_LDE_GROUP_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byProductLdeGroup";
	private static final String GET_PRODUCTS_FILTER_IS_LINE_OF_BUSINESS_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=isLineOfBusiness";
	private static final String GET_PRODUCTS_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products";
	private static final String GET_PRODUCTS_BY_NAME_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byName";
	private static final String GET_PRODUCTS_BY_PRODUCT_GROUP_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byProductGroup";
	private static final String GET_PRODUCTS_BY_KEYS_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byKeys";
	private static final String GET_PRODUCTS_BY_IDS_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byIds";
	private static final String CREATE_PRODUCT_USING_POST_ON_THE_PRODUCT_CONTROLLER = "products";
	private static final String RISKMATCH_UPDATE_PRODUCT_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products/riskmatch/{id}";
	private static final String GET_PRODUCT_BY_ID_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products/{id}";
	private static final String UPDATE_PRODUCT_USING_PUT_ON_THE_PRODUCT_CONTROLLER = "products/{id}";
	private static final String GET_PRODUCTS_BY_PRODUCT_SUPER_GROUP_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byProductSuperGroup";
	private static final String GET_PRODUCTS_BY_PARENT_KEY_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byParentKey";
	private static final String GET_PRODUCTS_BY_PARENT_ID_USING_GET_ON_THE_PRODUCT_CONTROLLER = "products?filter=byParentId";

	public static Performable getProductsByProductLdeGroupUsingGetOnTheProductController(String productLdeGroup, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Product by product LDE Group", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("productLdeGroup", productLdeGroup).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_PRODUCT_LDE_GROUP_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsFilterIsLineOfBusinessUsingGetOnTheProductController(String pageSize, String page, String filter){
		return Task.where(
		"{0} Get Products filtered by if the product is a line of business", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_FILTER_IS_LINE_OF_BUSINESS_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsUsingGetOnTheProductController(String pageSize, String page){
		return Task.where(
		"{0} Get all Products", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("pageSize", pageSize).queryParam("page", page).get(GET_PRODUCTS_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByNameUsingGetOnTheProductController(String name, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Product by name", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("name", name).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_NAME_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByProductGroupUsingGetOnTheProductController(String productGroup, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Product by product group", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("productGroup", productGroup).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_PRODUCT_GROUP_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByKeysUsingGetOnTheProductController(String keys, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Product by key", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("keys", keys).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_KEYS_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByIdsUsingGetOnTheProductController(String ids, String pageSize, String page, String filter){
		return Task.where(
		"{0} Retrieve lots of Product by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("ids", ids).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_IDS_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable createProductUsingPostOnTheProductController(Object body){
		return Task.where(
		"{0} Create Product", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").body(body).post(CREATE_PRODUCT_USING_POST_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable riskmatchUpdateProductUsingGetOnTheProductController(String id){
		return Task.where(
		"{0} RiskMatch Update Product", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(RISKMATCH_UPDATE_PRODUCT_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductByIdUsingGetOnTheProductController(String id){
		return Task.where(
		"{0} Get Product by id", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).get(GET_PRODUCT_BY_ID_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable updateProductUsingPutOnTheProductController(String id, Object body){
		return Task.where(
		"{0} Replace Product", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").pathParam("id", id).body(body).put(UPDATE_PRODUCT_USING_PUT_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByProductSuperGroupUsingGetOnTheProductController(String productSuperGroup, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Product by product supergroup", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("productSuperGroup", productSuperGroup).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_PRODUCT_SUPER_GROUP_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByParentKeyUsingGetOnTheProductController(String parentKey, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Product by parentKey", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("parentKey", parentKey).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_PARENT_KEY_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}

	public static Performable getProductsByParentIdUsingGetOnTheProductController(String parentId, String pageSize, String page, String filter){
		return Task.where(
		"{0} Find Product by parentId", 
			actor -> {
				CallTitanApi.asActorUsingService(actor, THIS_SERVICE).contentType("application/json").queryParam("parentId", parentId).queryParam("pageSize", pageSize).queryParam("page", page).queryParam("filter", filter).get(GET_PRODUCTS_BY_PARENT_ID_USING_GET_ON_THE_PRODUCT_CONTROLLER);
			}
		);
	}



}
