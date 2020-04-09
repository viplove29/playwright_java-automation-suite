package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ComputedAuthenticationSettingsV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class ComputedAuthenticationSettingsV1   {
  @JsonProperty("mfaEnabled")
  private Boolean mfaEnabled = null;

  public ComputedAuthenticationSettingsV1 mfaEnabled(Boolean mfaEnabled) {
    this.mfaEnabled = mfaEnabled;
    return this;
  }

  /**
   * Computed Boolean value indicating if MFA is required for users accessing this product/tenant/entity
   * @return mfaEnabled
  **/
  @ApiModelProperty(example = "false", readOnly = true, value = "Computed Boolean value indicating if MFA is required for users accessing this product/tenant/entity")


  public Boolean isMfaEnabled() {
    return mfaEnabled;
  }

  public void setMfaEnabled(Boolean mfaEnabled) {
    this.mfaEnabled = mfaEnabled;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComputedAuthenticationSettingsV1 computedAuthenticationSettingsV1 = (ComputedAuthenticationSettingsV1) o;
    return Objects.equals(this.mfaEnabled, computedAuthenticationSettingsV1.mfaEnabled);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mfaEnabled);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComputedAuthenticationSettingsV1 {\n");
    
    sb.append("    mfaEnabled: ").append(toIndentedString(mfaEnabled)).append("\n");
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

