package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.EntityMorselV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthorizationInfoV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthorizationInfoV1   {
  @JsonProperty("entities")
  @Valid
  private java.util.Map<String, EntityMorselV1> entities = null;

  @JsonProperty("entitlementAssignments")
  @Valid
  private java.util.Map<String, String> entitlementAssignments = null;

  @JsonProperty("groupAssignments")
  @Valid
  private java.util.List<String> groupAssignments = null;

  public AuthorizationInfoV1 entities(java.util.Map<String, EntityMorselV1> entities) {
    this.entities = entities;
    return this;
  }

  public AuthorizationInfoV1 putEntitiesItem(String key, EntityMorselV1 entitiesItem) {
    if (this.entities == null) {
      this.entities = new java.util.HashMap<String, EntityMorselV1>();
    }
    this.entities.put(key, entitiesItem);
    return this;
  }

  /**
   * Get entities
   * @return entities
  **/
  @ApiModelProperty(value = "")

  @Valid

  public java.util.Map<String, EntityMorselV1> getEntities() {
    return entities;
  }

  public void setEntities(java.util.Map<String, EntityMorselV1> entities) {
    this.entities = entities;
  }

  public AuthorizationInfoV1 entitlementAssignments(java.util.Map<String, String> entitlementAssignments) {
    this.entitlementAssignments = entitlementAssignments;
    return this;
  }

  public AuthorizationInfoV1 putEntitlementAssignmentsItem(String key, String entitlementAssignmentsItem) {
    if (this.entitlementAssignments == null) {
      this.entitlementAssignments = new java.util.HashMap<String, String>();
    }
    this.entitlementAssignments.put(key, entitlementAssignmentsItem);
    return this;
  }

  /**
   * Get entitlementAssignments
   * @return entitlementAssignments
  **/
  @ApiModelProperty(value = "")


  public java.util.Map<String, String> getEntitlementAssignments() {
    return entitlementAssignments;
  }

  public void setEntitlementAssignments(java.util.Map<String, String> entitlementAssignments) {
    this.entitlementAssignments = entitlementAssignments;
  }

  public AuthorizationInfoV1 groupAssignments(java.util.List<String> groupAssignments) {
    this.groupAssignments = groupAssignments;
    return this;
  }

  public AuthorizationInfoV1 addGroupAssignmentsItem(String groupAssignmentsItem) {
    if (this.groupAssignments == null) {
      this.groupAssignments = new java.util.ArrayList<String>();
    }
    this.groupAssignments.add(groupAssignmentsItem);
    return this;
  }

  /**
   * Get groupAssignments
   * @return groupAssignments
  **/
  @ApiModelProperty(value = "")


  public java.util.List<String> getGroupAssignments() {
    return groupAssignments;
  }

  public void setGroupAssignments(java.util.List<String> groupAssignments) {
    this.groupAssignments = groupAssignments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthorizationInfoV1 authorizationInfoV1 = (AuthorizationInfoV1) o;
    return Objects.equals(this.entities, authorizationInfoV1.entities) &&
        Objects.equals(this.entitlementAssignments, authorizationInfoV1.entitlementAssignments) &&
        Objects.equals(this.groupAssignments, authorizationInfoV1.groupAssignments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entities, entitlementAssignments, groupAssignments);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthorizationInfoV1 {\n");
    
    sb.append("    entities: ").append(toIndentedString(entities)).append("\n");
    sb.append("    entitlementAssignments: ").append(toIndentedString(entitlementAssignments)).append("\n");
    sb.append("    groupAssignments: ").append(toIndentedString(groupAssignments)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

