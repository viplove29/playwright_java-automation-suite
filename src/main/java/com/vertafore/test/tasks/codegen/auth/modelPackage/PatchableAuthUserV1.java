package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AddressV1;
import modelPackage.EmailAddressV1;
import modelPackage.NameV1;
import modelPackage.PhoneNumberV1;
import modelPackage.PhotoAddressV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PatchableAuthUserV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class PatchableAuthUserV1   {
  @JsonProperty("userType")
  private String userType = null;

  @JsonProperty("addresses")
  @Valid
  private java.util.List<AddressV1> addresses = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("emailAddresses")
  @Valid
  private java.util.List<EmailAddressV1> emailAddresses = null;

  @JsonProperty("localeLanguageTag")
  private String localeLanguageTag = null;

  @JsonProperty("name")
  private NameV1 name = null;

  @JsonProperty("nickName")
  private String nickName = null;

  @JsonProperty("phoneNumbers")
  @Valid
  private java.util.List<PhoneNumberV1> phoneNumbers = null;

  @JsonProperty("photos")
  @Valid
  private java.util.List<PhotoAddressV1> photos = null;

  @JsonProperty("preferredLanguage")
  private String preferredLanguage = null;

  @JsonProperty("profileUrl")
  private String profileUrl = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  public PatchableAuthUserV1 userType(String userType) {
    this.userType = userType;
    return this;
  }

  /**
   * Used to identify the relationship between the organization and the user. Typical values used might be 'Contractor', 'Employee', 'Intern', 'Temp', 'External', and  'Unknown', but any value may be used.
   * @return userType
  **/
  @ApiModelProperty(example = "Employee", value = "Used to identify the relationship between the organization and the user. Typical values used might be 'Contractor', 'Employee', 'Intern', 'Temp', 'External', and  'Unknown', but any value may be used.")


  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public PatchableAuthUserV1 addresses(java.util.List<AddressV1> addresses) {
    this.addresses = addresses;
    return this;
  }

  public PatchableAuthUserV1 addAddressesItem(AddressV1 addressesItem) {
    if (this.addresses == null) {
      this.addresses = new java.util.ArrayList<AddressV1>();
    }
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * A physical mailing address for this user.
   * @return addresses
  **/
  @ApiModelProperty(example = "\"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\"", value = "A physical mailing address for this user.")

  @Valid

  public java.util.List<AddressV1> getAddresses() {
    return addresses;
  }

  public void setAddresses(java.util.List<AddressV1> addresses) {
    this.addresses = addresses;
  }

  public PatchableAuthUserV1 displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * The name of the user, suitable for display to end-users. Each user returned MAY include a non-empty displayName value. The name SHOULD be the full name of the AuthUser being  described, if known (e.g., 'Babs Jensen' or 'Ms. Barbara J Jensen, III') but MAY be a  username or handle, if that is all that is available (e.g., 'bjensen').
   * @return displayName
  **/
  @ApiModelProperty(example = "Babs Jensen", value = "The name of the user, suitable for display to end-users. Each user returned MAY include a non-empty displayName value. The name SHOULD be the full name of the AuthUser being  described, if known (e.g., 'Babs Jensen' or 'Ms. Barbara J Jensen, III') but MAY be a  username or handle, if that is all that is available (e.g., 'bjensen').")


  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public PatchableAuthUserV1 emailAddresses(java.util.List<EmailAddressV1> emailAddresses) {
    this.emailAddresses = emailAddresses;
    return this;
  }

  public PatchableAuthUserV1 addEmailAddressesItem(EmailAddressV1 emailAddressesItem) {
    if (this.emailAddresses == null) {
      this.emailAddresses = new java.util.ArrayList<EmailAddressV1>();
    }
    this.emailAddresses.add(emailAddressesItem);
    return this;
  }

  /**
   * Email addresses for the AuthUser.
   * @return emailAddresses
  **/
  @ApiModelProperty(example = "\"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\"", value = "Email addresses for the AuthUser.")

  @Valid

  public java.util.List<EmailAddressV1> getEmailAddresses() {
    return emailAddresses;
  }

  public void setEmailAddresses(java.util.List<EmailAddressV1> emailAddresses) {
    this.emailAddresses = emailAddresses;
  }

  public PatchableAuthUserV1 localeLanguageTag(String localeLanguageTag) {
    this.localeLanguageTag = localeLanguageTag;
    return this;
  }

  /**
   * Used to indicate the AuthUser's default location for purposes of localizing such items as currency, date time format, or numerical representations.
   * @return localeLanguageTag
  **/
  @ApiModelProperty(example = "en-US", value = "Used to indicate the AuthUser's default location for purposes of localizing such items as currency, date time format, or numerical representations.")


  public String getLocaleLanguageTag() {
    return localeLanguageTag;
  }

  public void setLocaleLanguageTag(String localeLanguageTag) {
    this.localeLanguageTag = localeLanguageTag;
  }

  public PatchableAuthUserV1 name(NameV1 name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")

  @Valid

  public NameV1 getName() {
    return name;
  }

  public void setName(NameV1 name) {
    this.name = name;
  }

  public PatchableAuthUserV1 nickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  /**
   * The casual way to address the user in real life, e.g., 'Bob' or 'Bobby' instead of 'Robert'.
   * @return nickName
  **/
  @ApiModelProperty(example = "Babs", value = "The casual way to address the user in real life, e.g., 'Bob' or 'Bobby' instead of 'Robert'.")


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public PatchableAuthUserV1 phoneNumbers(java.util.List<PhoneNumberV1> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
    return this;
  }

  public PatchableAuthUserV1 addPhoneNumbersItem(PhoneNumberV1 phoneNumbersItem) {
    if (this.phoneNumbers == null) {
      this.phoneNumbers = new java.util.ArrayList<PhoneNumberV1>();
    }
    this.phoneNumbers.add(phoneNumbersItem);
    return this;
  }

  /**
   * Phone numbers for the user.
   * @return phoneNumbers
  **/
  @ApiModelProperty(example = "\"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\"", value = "Phone numbers for the user.")

  @Valid

  public java.util.List<PhoneNumberV1> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(java.util.List<PhoneNumberV1> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public PatchableAuthUserV1 photos(java.util.List<PhotoAddressV1> photos) {
    this.photos = photos;
    return this;
  }

  public PatchableAuthUserV1 addPhotosItem(PhotoAddressV1 photosItem) {
    if (this.photos == null) {
      this.photos = new java.util.ArrayList<PhotoAddressV1>();
    }
    this.photos.add(photosItem);
    return this;
  }

  /**
   * A URI that is a URL that points to a resource location representing the user's image. The resource MUST be a file (e.g., a GIF, JPEG, or PNG image file) rather than a web page containing an image.
   * @return photos
  **/
  @ApiModelProperty(example = "\"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\"", value = "A URI that is a URL that points to a resource location representing the user's image. The resource MUST be a file (e.g., a GIF, JPEG, or PNG image file) rather than a web page containing an image.")

  @Valid

  public java.util.List<PhotoAddressV1> getPhotos() {
    return photos;
  }

  public void setPhotos(java.util.List<PhotoAddressV1> photos) {
    this.photos = photos;
  }

  public PatchableAuthUserV1 preferredLanguage(String preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
    return this;
  }

  /**
   * Indicates the user's preferred written or spoken languages and is generally used for selecting a localized user interface. The value indicates the set of natural languages that are preferred.
   * @return preferredLanguage
  **/
  @ApiModelProperty(example = "en-US", value = "Indicates the user's preferred written or spoken languages and is generally used for selecting a localized user interface. The value indicates the set of natural languages that are preferred.")


  public String getPreferredLanguage() {
    return preferredLanguage;
  }

  public void setPreferredLanguage(String preferredLanguage) {
    this.preferredLanguage = preferredLanguage;
  }

  public PatchableAuthUserV1 profileUrl(String profileUrl) {
    this.profileUrl = profileUrl;
    return this;
  }

  /**
   * A URI that is a URL and that points to a location representing the user's online profile.
   * @return profileUrl
  **/
  @ApiModelProperty(example = "https://login.example.com/bjensen", value = "A URI that is a URL and that points to a location representing the user's online profile.")


  public String getProfileUrl() {
    return profileUrl;
  }

  public void setProfileUrl(String profileUrl) {
    this.profileUrl = profileUrl;
  }

  public PatchableAuthUserV1 title(String title) {
    this.title = title;
    return this;
  }

  /**
   * The user's title, such as 'Vice President'.
   * @return title
  **/
  @ApiModelProperty(example = "Tour Guide", value = "The user's title, such as 'Vice President'.")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PatchableAuthUserV1 username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Unique identifier for the user, typically used by the user to directly authenticate to the service provider. This identifier MUST be unique across the service provider's entire set of Users
   * @return username
  **/
  @ApiModelProperty(example = "bjjensen", value = "Unique identifier for the user, typically used by the user to directly authenticate to the service provider. This identifier MUST be unique across the service provider's entire set of Users")


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public PatchableAuthUserV1 zoneId(String zoneId) {
    this.zoneId = zoneId;
    return this;
  }

  /**
   * The AuthUser's time zone, in IANA Time Zone database format [RFC6557], also known as the 'Olson' time zone database format [Olson-TZ] (e.g., 'America/Los_Angeles').
   * @return zoneId
  **/
  @ApiModelProperty(example = "America/Los_Angeles", value = "The AuthUser's time zone, in IANA Time Zone database format [RFC6557], also known as the 'Olson' time zone database format [Olson-TZ] (e.g., 'America/Los_Angeles').")


  public String getZoneId() {
    return zoneId;
  }

  public void setZoneId(String zoneId) {
    this.zoneId = zoneId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PatchableAuthUserV1 patchableAuthUserV1 = (PatchableAuthUserV1) o;
    return Objects.equals(this.userType, patchableAuthUserV1.userType) &&
        Objects.equals(this.addresses, patchableAuthUserV1.addresses) &&
        Objects.equals(this.displayName, patchableAuthUserV1.displayName) &&
        Objects.equals(this.emailAddresses, patchableAuthUserV1.emailAddresses) &&
        Objects.equals(this.localeLanguageTag, patchableAuthUserV1.localeLanguageTag) &&
        Objects.equals(this.name, patchableAuthUserV1.name) &&
        Objects.equals(this.nickName, patchableAuthUserV1.nickName) &&
        Objects.equals(this.phoneNumbers, patchableAuthUserV1.phoneNumbers) &&
        Objects.equals(this.photos, patchableAuthUserV1.photos) &&
        Objects.equals(this.preferredLanguage, patchableAuthUserV1.preferredLanguage) &&
        Objects.equals(this.profileUrl, patchableAuthUserV1.profileUrl) &&
        Objects.equals(this.title, patchableAuthUserV1.title) &&
        Objects.equals(this.username, patchableAuthUserV1.username) &&
        Objects.equals(this.zoneId, patchableAuthUserV1.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userType, addresses, displayName, emailAddresses, localeLanguageTag, name, nickName, phoneNumbers, photos, preferredLanguage, profileUrl, title, username, zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PatchableAuthUserV1 {\n");
    
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    emailAddresses: ").append(toIndentedString(emailAddresses)).append("\n");
    sb.append("    localeLanguageTag: ").append(toIndentedString(localeLanguageTag)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nickName: ").append(toIndentedString(nickName)).append("\n");
    sb.append("    phoneNumbers: ").append(toIndentedString(phoneNumbers)).append("\n");
    sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
    sb.append("    preferredLanguage: ").append(toIndentedString(preferredLanguage)).append("\n");
    sb.append("    profileUrl: ").append(toIndentedString(profileUrl)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    zoneId: ").append(toIndentedString(zoneId)).append("\n");
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

