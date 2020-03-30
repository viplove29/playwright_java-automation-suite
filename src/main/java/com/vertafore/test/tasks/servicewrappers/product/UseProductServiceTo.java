package com.vertafore.test.tasks.servicewrappers.product;

import com.vertafore.test.abilities.CallTitanApi;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/** This class was automatically generated on 2020/03/30 11:16:59 */
public class UseProductServiceTo {

  private static final String THIS_SERVICE = "product";

  public static Performable getProductsUsingGetOnTheProductController(
      String pageSize, String page) {
    return Task.where(
        "{0} Get all Products",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .get("products");
        });
  }

  public static Performable getProductsByIdsUsingGetOnTheProductController(
      String ids, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Product by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("ids", ids)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byIds");
        });
  }

  public static Performable getProductsByParentKeyUsingGetOnTheProductController(
      String parentKey, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Find Product by parentKey",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("parentKey", parentKey)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byParentKey");
        });
  }

  public static Performable getProductsByProductLdeGroupUsingGetOnTheProductController(
      String productLdeGroup, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Find Product by product LDE Group",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("productLdeGroup", productLdeGroup)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byProductLdeGroup");
        });
  }

  public static Performable riskmatchUpdateProductUsingGetOnTheProductController(String id) {
    return Task.where(
        "{0} RiskMatch Update Product",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("products/riskmatch/{id}");
        });
  }

  public static Performable getProductsFilterIsLineOfBusinessUsingGetOnTheProductController(
      String pageSize, String page, String filter) {
    return Task.where(
        "{0} Get Products filtered by if the product is a line of business",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=isLineOfBusiness");
        });
  }

  public static Performable getProductsByProductSuperGroupUsingGetOnTheProductController(
      String productSuperGroup, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Find Product by product supergroup",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("productSuperGroup", productSuperGroup)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byProductSuperGroup");
        });
  }

  public static Performable getProductsByProductGroupUsingGetOnTheProductController(
      String productGroup, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Find Product by product group",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("productGroup", productGroup)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byProductGroup");
        });
  }

  public static Performable createProductUsingPostOnTheProductController(Object body) {
    return Task.where(
        "{0} Create Product",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .body(body)
              .post("products");
        });
  }

  public static Performable updateProductUsingPutOnTheProductController(String id, Object body) {
    return Task.where(
        "{0} Replace Product",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .body(body)
              .put("products/{id}");
        });
  }

  public static Performable getProductsByParentIdUsingGetOnTheProductController(
      String parentId, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Find Product by parentId",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("parentId", parentId)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byParentId");
        });
  }

  public static Performable getProductsByNameUsingGetOnTheProductController(
      String name, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Find Product by name",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("name", name)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byName");
        });
  }

  public static Performable getProductsByKeysUsingGetOnTheProductController(
      String keys, String pageSize, String page, String filter) {
    return Task.where(
        "{0} Retrieve lots of Product by key",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .queryParam("keys", keys)
              .queryParam("pageSize", pageSize)
              .queryParam("page", page)
              .queryParam("filter", filter)
              .get("products?filter=byKeys");
        });
  }

  public static Performable getProductByIdUsingGetOnTheProductController(String id) {
    return Task.where(
        "{0} Get Product by id",
        actor -> {
          CallTitanApi.asActorUsingService(actor, THIS_SERVICE)
              .contentType("application/json")
              .pathParam("id", id)
              .get("products/{id}");
        });
  }
}
