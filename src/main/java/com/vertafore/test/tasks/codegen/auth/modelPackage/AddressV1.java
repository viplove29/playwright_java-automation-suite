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
 * Standard Address
 */
@ApiModel(description = "Standard Address")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AddressV1   {
  @JsonProperty("country")
  private String country = null;

  @JsonProperty("formatted")
  private String formatted = null;

  @JsonProperty("locality")
  private String locality = null;

  @JsonProperty("mailing")
  private Boolean mailing = null;

  @JsonProperty("postalCode")
  private String postalCode = null;

  @JsonProperty("preferred")
  private Boolean preferred = null;

  @JsonProperty("region")
  private String region = null;

  @JsonProperty("streetAddress")
  private String streetAddress = null;

  @JsonProperty("streetAddress2")
  private String streetAddress2 = null;

  @JsonProperty("type")
  private String type = null;

  public AddressV1 country(String country) {
    this.country = country;
    return this;
  }

  /**
   * The country name component. When specified, the value MUST be in ISO 3166-1 'alpha-2' code format [ISO3166]
   * @return country
  **/
  @ApiModelProperty(example = "US", value = "The country name component. When specified, the value MUST be in ISO 3166-1 'alpha-2' code format [ISO3166]")


  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public AddressV1 formatted(String formatted) {
    this.formatted = formatted;
    return this;
  }

  /**
   * The full mailing address, formatted for display or use with a mailing label. This attribute MAY contain newlines.
   * @return formatted
  **/
  @ApiModelProperty(example = "100 Universal City Plaza Hollywood, CA 91608 USA", value = "The full mailing address, formatted for display or use with a mailing label. This attribute MAY contain newlines.")


  public String getFormatted() {
    return formatted;
  }

  public void setFormatted(String formatted) {
    this.formatted = formatted;
  }

  public AddressV1 locality(String locality) {
    this.locality = locality;
    return this;
  }

  /**
   * The city or locality component.
   * @return locality
  **/
  @ApiModelProperty(example = "Hollywood", value = "The city or locality component.")


  public String getLocality() {
    return locality;
  }

  public void setLocality(String locality) {
    this.locality = locality;
  }

  public AddressV1 mailing(Boolean mailing) {
    this.mailing = mailing;
    return this;
  }

  /**
   * Indicates a mailing Address
   * @return mailing
  **/
  @ApiModelProperty(example = "true", value = "Indicates a mailing Address")


  public Boolean isMailing() {
    return mailing;
  }

  public void setMailing(Boolean mailing) {
    this.mailing = mailing;
  }

  public AddressV1 postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  /**
   * The zip code or postal code component.
   * @return postalCode
  **/
  @ApiModelProperty(example = "91608", value = "The zip code or postal code component.")


  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public AddressV1 preferred(Boolean preferred) {
    this.preferred = preferred;
    return this;
  }

  /**
   * Indicates a preferred Address
   * @return preferred
  **/
  @ApiModelProperty(example = "true", value = "Indicates a preferred Address")


  public Boolean isPreferred() {
    return preferred;
  }

  public void setPreferred(Boolean preferred) {
    this.preferred = preferred;
  }

  public AddressV1 region(String region) {
    this.region = region;
    return this;
  }

  /**
   * The state, province or region component. Administrative division, within a country; a state or province.
   * @return region
  **/
  @ApiModelProperty(example = "CA", value = "The state, province or region component. Administrative division, within a country; a state or province.")


  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public AddressV1 streetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
    return this;
  }

  /**
   * The main street address component, which may include house number, street name, P.O. box.
   * @return streetAddress
  **/
  @ApiModelProperty(example = "100 Universal City Plaza", value = "The main street address component, which may include house number, street name, P.O. box.")


  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public AddressV1 streetAddress2(String streetAddress2) {
    this.streetAddress2 = streetAddress2;
    return this;
  }

  /**
   * The second line of a full address. The unit, apartment, building, etc. any secondary address component.
   * @return streetAddress2
  **/
  @ApiModelProperty(example = "#201", value = "The second line of a full address. The unit, apartment, building, etc. any secondary address component.")


  public String getStreetAddress2() {
    return streetAddress2;
  }

  public void setStreetAddress2(String streetAddress2) {
    this.streetAddress2 = streetAddress2;
  }

  public AddressV1 type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of Address
   * @return type
  **/
  @ApiModelProperty(example = "Business Address", value = "Type of Address")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddressV1 addressV1 = (AddressV1) o;
    return Objects.equals(this.country, addressV1.country) &&
        Objects.equals(this.formatted, addressV1.formatted) &&
        Objects.equals(this.locality, addressV1.locality) &&
        Objects.equals(this.mailing, addressV1.mailing) &&
        Objects.equals(this.postalCode, addressV1.postalCode) &&
        Objects.equals(this.preferred, addressV1.preferred) &&
        Objects.equals(this.region, addressV1.region) &&
        Objects.equals(this.streetAddress, addressV1.streetAddress) &&
        Objects.equals(this.streetAddress2, addressV1.streetAddress2) &&
        Objects.equals(this.type, addressV1.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, formatted, locality, mailing, postalCode, preferred, region, streetAddress, streetAddress2, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddressV1 {\n");
    
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    formatted: ").append(toIndentedString(formatted)).append("\n");
    sb.append("    locality: ").append(toIndentedString(locality)).append("\n");
    sb.append("    mailing: ").append(toIndentedString(mailing)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(postalCode)).append("\n");
    sb.append("    preferred: ").append(toIndentedString(preferred)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    streetAddress: ").append(toIndentedString(streetAddress)).append("\n");
    sb.append("    streetAddress2: ").append(toIndentedString(streetAddress2)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

