package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * EntityConcordanceV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class EntityConcordanceV1   {
  @JsonProperty("entityId")
  private String entityId = null;

  @JsonProperty("realmName")
  private String realmName = null;

  @JsonProperty("sourceEntityId")
  private String sourceEntityId = null;

  /**
   * Type of Source
   */
  public enum SourceTypeEnum {
    SAGITTA("SAGITTA"),
    
    AMS360("AMS360"),
    
    QQ("QQ");

    private String value;

    SourceTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SourceTypeEnum fromValue(String text) {
      for (SourceTypeEnum b : SourceTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("sourceType")
  private SourceTypeEnum sourceType = null;

  @JsonProperty("tenantId")
  private String tenantId = null;

  public EntityConcordanceV1 entityId(String entityId) {
    this.entityId = entityId;
    return this;
  }

  /**
   * Id of the Entity
   * @return entityId
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", required = true, value = "Id of the Entity")
  @NotNull


  public String getEntityId() {
    return entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public EntityConcordanceV1 realmName(String realmName) {
    this.realmName = realmName;
    return this;
  }

  /**
   * Displayable name of the Realm
   * @return realmName
  **/
  @ApiModelProperty(required = true, readOnly = true, value = "Displayable name of the Realm")
  @NotNull


  public String getRealmName() {
    return realmName;
  }

  public void setRealmName(String realmName) {
    this.realmName = realmName;
  }

  public EntityConcordanceV1 sourceEntityId(String sourceEntityId) {
    this.sourceEntityId = sourceEntityId;
    return this;
  }

  /**
   * Entity id of the source
   * @return sourceEntityId
  **/
  @ApiModelProperty(required = true, value = "Entity id of the source")
  @NotNull


  public String getSourceEntityId() {
    return sourceEntityId;
  }

  public void setSourceEntityId(String sourceEntityId) {
    this.sourceEntityId = sourceEntityId;
  }

  public EntityConcordanceV1 sourceType(SourceTypeEnum sourceType) {
    this.sourceType = sourceType;
    return this;
  }

  /**
   * Type of Source
   * @return sourceType
  **/
  @ApiModelProperty(example = "AMS360", required = true, value = "Type of Source")
  @NotNull


  public SourceTypeEnum getSourceType() {
    return sourceType;
  }

  public void setSourceType(SourceTypeEnum sourceType) {
    this.sourceType = sourceType;
  }

  public EntityConcordanceV1 tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  /**
   * Id of the Tenant
   * @return tenantId
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", required = true, value = "Id of the Tenant")
  @NotNull


  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EntityConcordanceV1 entityConcordanceV1 = (EntityConcordanceV1) o;
    return Objects.equals(this.entityId, entityConcordanceV1.entityId) &&
        Objects.equals(this.realmName, entityConcordanceV1.realmName) &&
        Objects.equals(this.sourceEntityId, entityConcordanceV1.sourceEntityId) &&
        Objects.equals(this.sourceType, entityConcordanceV1.sourceType) &&
        Objects.equals(this.tenantId, entityConcordanceV1.tenantId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entityId, realmName, sourceEntityId, sourceType, tenantId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EntityConcordanceV1 {\n");
    
    sb.append("    entityId: ").append(toIndentedString(entityId)).append("\n");
    sb.append("    realmName: ").append(toIndentedString(realmName)).append("\n");
    sb.append("    sourceEntityId: ").append(toIndentedString(sourceEntityId)).append("\n");
    sb.append("    sourceType: ").append(toIndentedString(sourceType)).append("\n");
    sb.append("    tenantId: ").append(toIndentedString(tenantId)).append("\n");
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

