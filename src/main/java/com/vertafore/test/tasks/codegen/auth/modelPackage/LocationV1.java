package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AddressV1;
import modelPackage.PhoneNumberV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Location
 */
@ApiModel(description = "Location")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class LocationV1   {
  @JsonProperty("addresses")
  @Valid
  private java.util.List<AddressV1> addresses = null;

  @JsonProperty("phoneNumbers")
  @Valid
  private java.util.List<PhoneNumberV1> phoneNumbers = null;

  public LocationV1 addresses(java.util.List<AddressV1> addresses) {
    this.addresses = addresses;
    return this;
  }

  public LocationV1 addAddressesItem(AddressV1 addressesItem) {
    if (this.addresses == null) {
      this.addresses = new java.util.ArrayList<AddressV1>();
    }
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * List of Addresses for the Location
   * @return addresses
  **/
  @ApiModelProperty(value = "List of Addresses for the Location")

  @Valid

  public java.util.List<AddressV1> getAddresses() {
    return addresses;
  }

  public void setAddresses(java.util.List<AddressV1> addresses) {
    this.addresses = addresses;
  }

  public LocationV1 phoneNumbers(java.util.List<PhoneNumberV1> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
    return this;
  }

  public LocationV1 addPhoneNumbersItem(PhoneNumberV1 phoneNumbersItem) {
    if (this.phoneNumbers == null) {
      this.phoneNumbers = new java.util.ArrayList<PhoneNumberV1>();
    }
    this.phoneNumbers.add(phoneNumbersItem);
    return this;
  }

  /**
   * List of Phone numbers for the Location
   * @return phoneNumbers
  **/
  @ApiModelProperty(value = "List of Phone numbers for the Location")

  @Valid

  public java.util.List<PhoneNumberV1> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(java.util.List<PhoneNumberV1> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocationV1 locationV1 = (LocationV1) o;
    return Objects.equals(this.addresses, locationV1.addresses) &&
        Objects.equals(this.phoneNumbers, locationV1.phoneNumbers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addresses, phoneNumbers);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LocationV1 {\n");
    
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    phoneNumbers: ").append(toIndentedString(phoneNumbers)).append("\n");
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

