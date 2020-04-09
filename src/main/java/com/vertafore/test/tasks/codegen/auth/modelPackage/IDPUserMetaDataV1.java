package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IDPUserMetaDataV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class IDPUserMetaDataV1   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("lastLoginTime")
  private OffsetDateTime lastLoginTime = null;

  public IDPUserMetaDataV1 id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Unique id of the AuthUser
   * @return id
  **/
  @ApiModelProperty(example = "b647a79e-b500-4640-a1bf-2aeb2efef379", readOnly = true, value = "Unique id of the AuthUser")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public IDPUserMetaDataV1 lastLoginTime(OffsetDateTime lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
    return this;
  }

  /**
   * Last time this user logged in
   * @return lastLoginTime
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "Last time this user logged in")

  @Valid

  public OffsetDateTime getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(OffsetDateTime lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IDPUserMetaDataV1 idPUserMetaDataV1 = (IDPUserMetaDataV1) o;
    return Objects.equals(this.id, idPUserMetaDataV1.id) &&
        Objects.equals(this.lastLoginTime, idPUserMetaDataV1.lastLoginTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastLoginTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IDPUserMetaDataV1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastLoginTime: ").append(toIndentedString(lastLoginTime)).append("\n");
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

