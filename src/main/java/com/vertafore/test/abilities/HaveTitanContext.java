// package com.vertafore.test.abilities;
//
// import net.serenitybdd.screenplay.Ability;
// import net.serenitybdd.screenplay.Actor;
//
// public class HaveTitanContext implements Ability {
//
//  private String product = null;
//  private String tenant = null;
//  private String entity = null;
//
//  private HaveTitanContext(String productId, String tenantId, String entityId) {
//    this.product = productId;
//    this.tenant = tenantId;
//    this.entity = entityId;
//  }
//
//  public static HaveTitanContext of(String product, String tenant, String entity) {
//    return new HaveTitanContext(product, tenant, entity);
//  }
//
//  public static HaveTitanContext as(Actor actor) {
//    if (actor.abilityTo(HaveTitanContext.class) == null) {
//      throw new IllegalArgumentException(
//          actor.getName().concat(" does not have the ability to have a Titan Context."));
//    } else {
//      return actor.abilityTo(HaveTitanContext.class);
//    }
//  }
//
//  public static String theProductIdOf(Actor actor) {
//    return HaveTitanContext.as(actor).product;
//  }
//
//  private void setProduct(String newProduct) {
//    this.product = newProduct;
//  }
//
//  public static void theNewProductOf(Actor actor, String newProduct) {
//    HaveTitanContext.as(actor).setProduct(newProduct);
//  }
//
//  public static String theTenantIdOf(Actor actor) {
//    return HaveTitanContext.as(actor).tenant;
//  }
//
//  private void setTenant(String newTenant) {
//    this.tenant = newTenant;
//  }
//
//  public static void theNewTenantOf(Actor actor, String newTenant) {
//    HaveTitanContext.as(actor).setTenant(newTenant);
//  }
//
//  public static String theEntityIdOf(Actor actor) {
//    return HaveTitanContext.as(actor).entity;
//  }
//
//  private void setEntity(String newEntity) {
//    this.entity = newEntity;
//  }
//
//  public static void theNewEntityOf(Actor actor, String newEntity) {
//    HaveTitanContext.as(actor).setEntity(newEntity);
//  }
// }
