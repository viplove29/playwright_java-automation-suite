package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.AddressV1;
import modelPackage.AuthUserContextV1;
import modelPackage.EmailAddressV1;
import modelPackage.NameV1;
import modelPackage.PhoneNumberV1;
import modelPackage.PhotoAddressV1;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * AuthUserV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class AuthUserV1   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("password")
  private String password = null;

  /**
   * Used to identify the relationship between the organization and the user. Typical values used might be 'Contractor', 'Employee', 'Intern', 'Temp', 'External', and  'Unknown', but any value may be used.
   */
  public enum UserTypeEnum {
    EMPLOYEE("EMPLOYEE"),
    
    BOOTSTRAP("BOOTSTRAP"),
    
    SVI("SVI"),
    
    API_USER("API-USER"),
    
    NOW_SERVING("NOW-SERVING");

    private String value;

    UserTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UserTypeEnum fromValue(String text) {
      for (UserTypeEnum b : UserTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("userType")
  private UserTypeEnum userType = null;

  @JsonProperty("active")
  private Boolean active = null;

  @JsonProperty("createdOn")
  private OffsetDateTime createdOn = null;

  @JsonProperty("addresses")
  @Valid
  private java.util.List<AddressV1> addresses = null;

  @JsonProperty("contexts")
  @Valid
  private java.util.List<AuthUserContextV1> contexts = null;

  @JsonProperty("displayName")
  private String displayName = null;

  @JsonProperty("emailAddresses")
  @Valid
  private java.util.List<EmailAddressV1> emailAddresses = new java.util.ArrayList<EmailAddressV1>();

  @JsonProperty("lastLogin")
  private OffsetDateTime lastLogin = null;

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

  @JsonProperty("realmName")
  private String realmName = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("updatedOn")
  private OffsetDateTime updatedOn = null;

  @JsonProperty("zoneId")
  private String zoneId = null;

  public AuthUserV1 id(String id) {
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

  public AuthUserV1 username(String username) {
    this.username = username;
    return this;
  }

  /**
   * Unique identifier for the user, typically used by the user to directly authenticate to the service provider. This identifier MUST be unique across the service provider's entire set of Users
   * @return username
  **/
  @ApiModelProperty(example = "bjjensen", required = true, value = "Unique identifier for the user, typically used by the user to directly authenticate to the service provider. This identifier MUST be unique across the service provider's entire set of Users")
  @NotNull


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public AuthUserV1 password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Password for the auth user
   * @return password
  **/
  @ApiModelProperty(value = "Password for the auth user")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public AuthUserV1 userType(UserTypeEnum userType) {
    this.userType = userType;
    return this;
  }

  /**
   * Used to identify the relationship between the organization and the user. Typical values used might be 'Contractor', 'Employee', 'Intern', 'Temp', 'External', and  'Unknown', but any value may be used.
   * @return userType
  **/
  @ApiModelProperty(example = "Employee", value = "Used to identify the relationship between the organization and the user. Typical values used might be 'Contractor', 'Employee', 'Intern', 'Temp', 'External', and  'Unknown', but any value may be used.")


  public UserTypeEnum getUserType() {
    return userType;
  }

  public void setUserType(UserTypeEnum userType) {
    this.userType = userType;
  }

  public AuthUserV1 active(Boolean active) {
    this.active = active;
    return this;
  }

  /**
   * A boolean value indicating the user's administrative status. The definitive meaning of this  attribute is determined by the service provider.
   * @return active
  **/
  @ApiModelProperty(example = "false", value = "A boolean value indicating the user's administrative status. The definitive meaning of this  attribute is determined by the service provider.")


  public Boolean isActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public AuthUserV1 createdOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * When the AuthUser was created
   * @return createdOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the AuthUser was created")

  @Valid

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public AuthUserV1 addresses(java.util.List<AddressV1> addresses) {
    this.addresses = addresses;
    return this;
  }

  public AuthUserV1 addAddressesItem(AddressV1 addressesItem) {
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

  public AuthUserV1 contexts(java.util.List<AuthUserContextV1> contexts) {
    this.contexts = contexts;
    return this;
  }

  public AuthUserV1 addContextsItem(AuthUserContextV1 contextsItem) {
    if (this.contexts == null) {
      this.contexts = new java.util.ArrayList<AuthUserContextV1>();
    }
    this.contexts.add(contextsItem);
    return this;
  }

  /**
   * entities for the user and active status of entity
   * @return contexts
  **/
  @ApiModelProperty(readOnly = true, value = "entities for the user and active status of entity")

  @Valid

  public java.util.List<AuthUserContextV1> getContexts() {
    return contexts;
  }

  public void setContexts(java.util.List<AuthUserContextV1> contexts) {
    this.contexts = contexts;
  }

  public AuthUserV1 displayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  /**
   * The name of the user, suitable for display to end-users. Each user returned MAY include a non-empty displayName value. The name SHOULD be the full name of the AuthUser being described, if known (e.g., 'Babs Jensen' or 'Ms. Barbara J Jensen, III') but MAY be a username or handle, if that is all that is available (e.g., 'bjensen').
   * @return displayName
  **/
  @ApiModelProperty(example = "Babs Jensen", value = "The name of the user, suitable for display to end-users. Each user returned MAY include a non-empty displayName value. The name SHOULD be the full name of the AuthUser being described, if known (e.g., 'Babs Jensen' or 'Ms. Barbara J Jensen, III') but MAY be a username or handle, if that is all that is available (e.g., 'bjensen').")


  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public AuthUserV1 emailAddresses(java.util.List<EmailAddressV1> emailAddresses) {
    this.emailAddresses = emailAddresses;
    return this;
  }

  public AuthUserV1 addEmailAddressesItem(EmailAddressV1 emailAddressesItem) {
    this.emailAddresses.add(emailAddressesItem);
    return this;
  }

  /**
   * Email addresses for the AuthUser.
   * @return emailAddresses
  **/
  @ApiModelProperty(example = "\"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\"", required = true, value = "Email addresses for the AuthUser.")
  @NotNull

  @Valid

  public java.util.List<EmailAddressV1> getEmailAddresses() {
    return emailAddresses;
  }

  public void setEmailAddresses(java.util.List<EmailAddressV1> emailAddresses) {
    this.emailAddresses = emailAddresses;
  }

  public AuthUserV1 lastLogin(OffsetDateTime lastLogin) {
    this.lastLogin = lastLogin;
    return this;
  }

  /**
   * When the AuthUser last logged in
   * @return lastLogin
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the AuthUser last logged in")

  @Valid

  public OffsetDateTime getLastLogin() {
    return lastLogin;
  }

  public void setLastLogin(OffsetDateTime lastLogin) {
    this.lastLogin = lastLogin;
  }

  public AuthUserV1 localeLanguageTag(String localeLanguageTag) {
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

  public AuthUserV1 name(NameV1 name) {
    this.name = name;
    return this;
  }

  /**
   * The components of the user's name.
   * @return name
  **/
  @ApiModelProperty(value = "The components of the user's name.")

  @Valid

  public NameV1 getName() {
    return name;
  }

  public void setName(NameV1 name) {
    this.name = name;
  }

  public AuthUserV1 nickName(String nickName) {
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

  public AuthUserV1 phoneNumbers(java.util.List<PhoneNumberV1> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
    return this;
  }

  public AuthUserV1 addPhoneNumbersItem(PhoneNumberV1 phoneNumbersItem) {
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

  public AuthUserV1 photos(java.util.List<PhotoAddressV1> photos) {
    this.photos = photos;
    return this;
  }

  public AuthUserV1 addPhotosItem(PhotoAddressV1 photosItem) {
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

  public AuthUserV1 preferredLanguage(String preferredLanguage) {
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

  public AuthUserV1 profileUrl(String profileUrl) {
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

  public AuthUserV1 realmName(String realmName) {
    this.realmName = realmName;
    return this;
  }

  /**
   * An (optional, write once) identifier to indicate the namespace users from different sources.
   * @return realmName
  **/
  @ApiModelProperty(value = "An (optional, write once) identifier to indicate the namespace users from different sources.")


  public String getRealmName() {
    return realmName;
  }

  public void setRealmName(String realmName) {
    this.realmName = realmName;
  }

  public AuthUserV1 title(String title) {
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

  public AuthUserV1 updatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
    return this;
  }

  /**
   * When the AuthUser was last modified
   * @return updatedOn
  **/
  @ApiModelProperty(example = "1970-01-01T00:00:00.000Z", readOnly = true, value = "When the AuthUser was last modified")

  @Valid

  public OffsetDateTime getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(OffsetDateTime updatedOn) {
    this.updatedOn = updatedOn;
  }

  public AuthUserV1 zoneId(String zoneId) {
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
    AuthUserV1 authUserV1 = (AuthUserV1) o;
    return Objects.equals(this.id, authUserV1.id) &&
        Objects.equals(this.username, authUserV1.username) &&
        Objects.equals(this.password, authUserV1.password) &&
        Objects.equals(this.userType, authUserV1.userType) &&
        Objects.equals(this.active, authUserV1.active) &&
        Objects.equals(this.createdOn, authUserV1.createdOn) &&
        Objects.equals(this.addresses, authUserV1.addresses) &&
        Objects.equals(this.contexts, authUserV1.contexts) &&
        Objects.equals(this.displayName, authUserV1.displayName) &&
        Objects.equals(this.emailAddresses, authUserV1.emailAddresses) &&
        Objects.equals(this.lastLogin, authUserV1.lastLogin) &&
        Objects.equals(this.localeLanguageTag, authUserV1.localeLanguageTag) &&
        Objects.equals(this.name, authUserV1.name) &&
        Objects.equals(this.nickName, authUserV1.nickName) &&
        Objects.equals(this.phoneNumbers, authUserV1.phoneNumbers) &&
        Objects.equals(this.photos, authUserV1.photos) &&
        Objects.equals(this.preferredLanguage, authUserV1.preferredLanguage) &&
        Objects.equals(this.profileUrl, authUserV1.profileUrl) &&
        Objects.equals(this.realmName, authUserV1.realmName) &&
        Objects.equals(this.title, authUserV1.title) &&
        Objects.equals(this.updatedOn, authUserV1.updatedOn) &&
        Objects.equals(this.zoneId, authUserV1.zoneId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, userType, active, createdOn, addresses, contexts, displayName, emailAddresses, lastLogin, localeLanguageTag, name, nickName, phoneNumbers, photos, preferredLanguage, profileUrl, realmName, title, updatedOn, zoneId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthUserV1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    contexts: ").append(toIndentedString(contexts)).append("\n");
    sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
    sb.append("    emailAddresses: ").append(toIndentedString(emailAddresses)).append("\n");
    sb.append("    lastLogin: ").append(toIndentedString(lastLogin)).append("\n");
    sb.append("    localeLanguageTag: ").append(toIndentedString(localeLanguageTag)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    nickName: ").append(toIndentedString(nickName)).append("\n");
    sb.append("    phoneNumbers: ").append(toIndentedString(phoneNumbers)).append("\n");
    sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
    sb.append("    preferredLanguage: ").append(toIndentedString(preferredLanguage)).append("\n");
    sb.append("    profileUrl: ").append(toIndentedString(profileUrl)).append("\n");
    sb.append("    realmName: ").append(toIndentedString(realmName)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    updatedOn: ").append(toIndentedString(updatedOn)).append("\n");
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

