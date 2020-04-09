package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.ComputedAuthenticationSettingsV1;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthenticationSettingsV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthenticationSettingsV1   {
  @JsonProperty("mfaEnabled")
  private Boolean mfaEnabled = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("computed")
  private ComputedAuthenticationSettingsV1 computed = null;

  public AuthenticationSettingsV1 mfaEnabled(Boolean mfaEnabled) {
    this.mfaEnabled = mfaEnabled;
    return this;
  }

  /**
   * Boolean value indicating if MFA is required for users accessing this product/tenant/entity
   * @return mfaEnabled
  **/
  @ApiModelProperty(example = "false", value = "Boolean value indicating if MFA is required for users accessing this product/tenant/entity")


  public Boolean isMfaEnabled() {
    return mfaEnabled;
  }

  public void setMfaEnabled(Boolean mfaEnabled) {
    this.mfaEnabled = mfaEnabled;
  }

  public AuthenticationSettingsV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * instant these settings were created
   * @return createdOn
  **/
  @ApiModelProperty(readOnly = true, value = "instant these settings were created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public AuthenticationSettingsV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * instant these settings were last modified
   * @return updatedOn
  **/
  @ApiModelProperty(readOnly = true, value = "instant these settings were last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public AuthenticationSettingsV1 computed(ComputedAuthenticationSettingsV1 computed) {
    this.computed = computed;
    return this;
  }

  /**
   * Computed settings for the requested context
   * @return computed
  **/
  @ApiModelProperty(readOnly = true, value = "Computed settings for the requested context")

  @Valid

  public ComputedAuthenticationSettingsV1 getComputed() {
    return computed;
  }

  public void setComputed(ComputedAuthenticationSettingsV1 computed) {
    this.computed = computed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthenticationSettingsV1 authenticationSettingsV1 = (AuthenticationSettingsV1) o;
    return Objects.equals(this.mfaEnabled, authenticationSettingsV1.mfaEnabled) &&
        Objects.equals(this.createdOn, authenticationSettingsV1.createdOn) &&
        Objects.equals(this.updatedOn, authenticationSettingsV1.updatedOn) &&
        Objects.equals(this.computed, authenticationSettingsV1.computed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mfaEnabled, createdOn, updatedOn, computed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticationSettingsV1 {\n");
    
    sb.append("    mfaEnabled: ").append(toIndentedString(mfaEnabled)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
    sb.append("    computed: ").append(toIndentedString(computed)).append("\n");
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

