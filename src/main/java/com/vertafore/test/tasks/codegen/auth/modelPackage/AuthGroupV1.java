package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthGroupV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthGroupV1   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("deletedOn")
  private OffsetDateTime deletedOn = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Gets or Sets groupCreationSource
   */
  public enum GroupCreationSourceEnum {
    FEDERATED("federated"),
    
    UNFEDERATED("unfederated");

    private String value;

    GroupCreationSourceEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static GroupCreationSourceEnum fromValue(String text) {
      for (GroupCreationSourceEnum b : GroupCreationSourceEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("groupCreationSource")
  private GroupCreationSourceEnum groupCreationSource = null;

  @JsonProperty("sourceAclId")
  private String sourceAclId = null;

  public AuthGroupV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique id of the AuthGroup
   * @return id
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", readOnly = true, value = "Unique id of the AuthGroup")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public AuthGroupV1 name(String name) {
    this.name = name;
    return this;
  }

  /**
   * A human-readable name for the AuthGroup.
   * @return name
  **/
  @ApiModelProperty(example = "Managers", required = true, value = "A human-readable name for the AuthGroup.")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AuthGroupV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the AuthGroup was created
   * @return createdOn
  **/
  @ApiModelProperty(readOnly = true, value = "When the AuthGroup was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public AuthGroupV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the AuthGroup was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(readOnly = true, value = "When the AuthGroup was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public AuthGroupV1 deletedOn(OffsetDateTime deletedOn) {
    this.deletedOn = deletedOn;
    return this;
  }

  /**
   * When the AuthGroup was deleted
   * @return deletedOn
  **/
  @ApiModelProperty(readOnly = true, value = "When the AuthGroup was deleted")

  @Valid

  public OffsetDateTime getDeletedOn() {
    return deletedOn;
  }

  public void setDeletedOn(OffsetDateTime deletedOn) {
    this.deletedOn = deletedOn;
  }

  public AuthGroupV1 description(String description) {
    this.description = description;
    return this;
  }

  /**
   * A description for the AuthGroup.
   * @return description
  **/
  @ApiModelProperty(example = "Admins for the system", value = "A description for the AuthGroup.")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public AuthGroupV1 groupCreationSource(GroupCreationSourceEnum groupCreationSource) {
    this.groupCreationSource = groupCreationSource;
    return this;
  }

  /**
   * Get groupCreationSource
   * @return groupCreationSource
  **/
  @ApiModelProperty(value = "")


  public GroupCreationSourceEnum getGroupCreationSource() {
    return groupCreationSource;
  }

  public void setGroupCreationSource(GroupCreationSourceEnum groupCreationSource) {
    this.groupCreationSource = groupCreationSource;
  }

  public AuthGroupV1 sourceAclId(String sourceAclId) {
    this.sourceAclId = sourceAclId;
    return this;
  }

  /**
   * An access control list for the AuthGroup.
   * @return sourceAclId
  **/
  @ApiModelProperty(example = "Source ACL ID", value = "An access control list for the AuthGroup.")


  public String getSourceAclId() {
    return sourceAclId;
  }

  public void setSourceAclId(String sourceAclId) {
    this.sourceAclId = sourceAclId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthGroupV1 authGroupV1 = (AuthGroupV1) o;
    return Objects.equals(this.id, authGroupV1.id) &&
        Objects.equals(this.name, authGroupV1.name) &&
        Objects.equals(this.createdOn, authGroupV1.createdOn) &&
        Objects.equals(this.updatedOn, authGroupV1.updatedOn) &&
        Objects.equals(this.deletedOn, authGroupV1.deletedOn) &&
        Objects.equals(this.description, authGroupV1.description) &&
        Objects.equals(this.groupCreationSource, authGroupV1.groupCreationSource) &&
        Objects.equals(this.sourceAclId, authGroupV1.sourceAclId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createdOn, updatedOn, deletedOn, description, groupCreationSource, sourceAclId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthGroupV1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    deletedOn: ").append(toIndentedString(deletedOn)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    groupCreationSource: ").append(toIndentedString(groupCreationSource)).append("\n");
    sb.append("    sourceAclId: ").append(toIndentedString(sourceAclId)).append("\n");
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

