package apiPackage;

import modelPackage.AuthGroupMembershipV1;
import modelPackage.AuthGroupRoleAssignmentV1;
import modelPackage.AuthGroupV1;
import modelPackage.AuthUserRoleAssignmentV1;
import modelPackage.AuthUserV1;
import modelPackage.AuthenticationSettingsV1;
import modelPackage.ChangePasswordRequestV1;
import modelPackage.ERRORUNKNOWN;
import modelPackage.EmptyResponseV1;
import modelPackage.EntityConcordanceV1;
import modelPackage.EntityV1;
import modelPackage.IDPUserV1;
import modelPackage.JsonPatchPatchableAuthUserV1;
import modelPackage.PagedResponseV1AuthGroupMembershipV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1AuthGroupRoleAssignmentV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1AuthGroupV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1AuthUserRoleAssignmentV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1EntityConcordanceV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1ProductV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseV1UserAndAssignedRolesV1LimitOffsetPagingInfoV1;
import modelPackage.PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1;
import modelPackage.ProductV1;
import modelPackage.RealmV1;
import modelPackage.RoleV1;
import modelPackage.ServiceRoleV1;
import modelPackage.SingleResponseV1AssignedRoleCountV1;
import modelPackage.SingleResponseV1AuthGroupMembershipV1;
import modelPackage.SingleResponseV1AuthGroupRoleAssignmentV1;
import modelPackage.SingleResponseV1AuthGroupV1;
import modelPackage.SingleResponseV1AuthUserRoleAssignmentV1;
import modelPackage.SingleResponseV1AuthUserSecretV1;
import modelPackage.SingleResponseV1AuthUserV1;
import modelPackage.SingleResponseV1AuthenticationSettingsV1;
import modelPackage.SingleResponseV1ContextualAuthInfoV1;
import modelPackage.SingleResponseV1EntityBranchV1;
import modelPackage.SingleResponseV1EntityConcordanceV1;
import modelPackage.SingleResponseV1EntityV1;
import modelPackage.SingleResponseV1List;
import modelPackage.SingleResponseV1ListIDPUserMetaDataV1;
import modelPackage.SingleResponseV1ListServiceRoleV1;
import modelPackage.SingleResponseV1Mapstringboolean;
import modelPackage.SingleResponseV1Mapstringstring;
import modelPackage.SingleResponseV1OAuthTokenV1;
import modelPackage.SingleResponseV1ProductV1;
import modelPackage.SingleResponseV1RealmV1;
import modelPackage.SingleResponseV1ServiceRoleV1;
import modelPackage.SingleResponseWithRelatedObjectsV1RoleV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

@Controller
public class AuthApiController implements AuthApi {

    private static final Logger log = LoggerFactory.getLogger(AuthApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public AuthApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> activateUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the User",required=true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthGroupMembershipV1> addAuthUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the AuthUser",required=true) @PathVariable("userId") String userId,@ApiParam(value = "Id of the AuthGroup",required=true) @PathVariable("groupId") String groupId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthGroupMembershipV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"productId\" : \"productId\",    \"authGroupContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"tenantId\" : \"tenantId\",    \"entityId\" : \"entityId\",    \"userId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"authGroupMembershipSource\" : \"UNFEDERATED\"  }}", SingleResponseV1AuthGroupMembershipV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthGroupMembershipV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthGroupMembershipV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1Mapstringstring> bulkActivateUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Ids of the Users") @Valid @RequestParam(value = "userIds", required = false) java.util.List<String> userIds) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1Mapstringstring>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"key\" : \"content\"  }}", SingleResponseV1Mapstringstring.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1Mapstringstring>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1Mapstringstring>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1Mapstringstring> bulkCreateUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "List of new AuthUserV1s to be created"  )  @Valid @RequestBody java.util.List<AuthUserV1> toBeCreated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1Mapstringstring>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"key\" : \"content\"  }}", SingleResponseV1Mapstringstring.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1Mapstringstring>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1Mapstringstring>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1Mapstringstring> bulkDeactivateUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Ids of the Users") @Valid @RequestParam(value = "userIds", required = false) java.util.List<String> userIds) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1Mapstringstring>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"key\" : \"content\"  }}", SingleResponseV1Mapstringstring.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1Mapstringstring>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1Mapstringstring>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1List> bulkDeleteUserUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "userIds", required = true) @Valid @RequestParam(value = "userIds", required = true) java.util.List<String> userIds) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1List>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ \"{}\", \"{}\" ]}", SingleResponseV1List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1List>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1List>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1List> bulkGrantRoleUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new AuthUserRoleAssignmentV1 to be created"  )  @Valid @RequestBody AuthUserRoleAssignmentV1 authUserRoleAssignmentV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1List>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ \"{}\", \"{}\" ]}", SingleResponseV1List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1List>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1List>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> changePasswordUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Payload that includes username, old password, and new password"  )  @Valid @RequestBody ChangePasswordRequestV1 changePassword) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type ", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthGroupV1> createAuthGroupUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new AuthGroup to be created"  )  @Valid @RequestBody AuthGroupV1 toBeCreated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthGroupV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"Managers\",    \"description\" : \"Admins for the system\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"sourceAclId\" : \"Source ACL ID\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"groupCreationSource\" : \"federated\"  }}", SingleResponseV1AuthGroupV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthGroupV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthGroupV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1EntityConcordanceV1> createEntityConcordanceUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the Entity",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new EntityConcordance to be created"  )  @Valid @RequestBody EntityConcordanceV1 toBeCreated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1EntityConcordanceV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"sourceType\" : \"AMS360\",    \"realmName\" : \"realmName\",    \"sourceEntityId\" : \"sourceEntityId\",    \"tenantId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"entityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\"  }}", SingleResponseV1EntityConcordanceV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1EntityConcordanceV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1EntityConcordanceV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1EntityV1> createEntityUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new Entity to be created"  )  @Valid @RequestBody EntityV1 toBeCreated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1EntityV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"emailAddress\" : \"example@gmail.com\",    \"metadata\" : \"{'ams-web-ui': { 'office-type': 'shared' }}\",    \"entityType\" : \"AGENCY\",    \"webAddress\" : \"https://insurancewebsite.com/\",    \"name\" : \"Insurance West Division - Chicago\",    \"zoneId\" : \"America/Los_Angeles\",    \"attributes\" : [ {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    }, {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    } ],    \"locations\" : [ {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    }, {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    } ],    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"parentEntityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1EntityV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthGroupV1> createOrUpdateAuthGroupUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the AuthGroup",required=true) @PathVariable("groupId") String groupId,@ApiParam(value = "An AuthGroup to be updated or created"  )  @Valid @RequestBody AuthGroupV1 toBeUpdated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthGroupV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"Managers\",    \"description\" : \"Admins for the system\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"sourceAclId\" : \"Source ACL ID\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"groupCreationSource\" : \"federated\"  }}", SingleResponseV1AuthGroupV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthGroupV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthGroupV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1EntityV1> createOrUpdateEntityUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of Entity to update/create",required=true) @PathVariable("entityIdToWrite") String entityIdToWrite,@ApiParam(value = "An Entity to be updated/created"  )  @Valid @RequestBody EntityV1 toBeUpdated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1EntityV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"emailAddress\" : \"example@gmail.com\",    \"metadata\" : \"{'ams-web-ui': { 'office-type': 'shared' }}\",    \"entityType\" : \"AGENCY\",    \"webAddress\" : \"https://insurancewebsite.com/\",    \"name\" : \"Insurance West Division - Chicago\",    \"zoneId\" : \"America/Los_Angeles\",    \"attributes\" : [ {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    }, {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    } ],    \"locations\" : [ {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    }, {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    } ],    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"parentEntityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1EntityV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1EntityV1> createOrUpdateTenantUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the tenant",required=true) @PathVariable("tenantIdToModify") String tenantIdToModify,@ApiParam(value = "A new Tenant to be updated"  )  @Valid @RequestBody EntityV1 tenantToUpdate) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1EntityV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"emailAddress\" : \"example@gmail.com\",    \"metadata\" : \"{'ams-web-ui': { 'office-type': 'shared' }}\",    \"entityType\" : \"AGENCY\",    \"webAddress\" : \"https://insurancewebsite.com/\",    \"name\" : \"Insurance West Division - Chicago\",    \"zoneId\" : \"America/Los_Angeles\",    \"attributes\" : [ {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    }, {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    } ],    \"locations\" : [ {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    }, {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    } ],    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"parentEntityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1EntityV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthenticationSettingsV1> createOrUpdateUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "AuthUser to be Updated"  )  @Valid @RequestBody AuthenticationSettingsV1 toBeUpdated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthenticationSettingsV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"computed\" : {      \"mfaEnabled\" : false    },    \"mfaEnabled\" : false,    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }}", SingleResponseV1AuthenticationSettingsV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthenticationSettingsV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthenticationSettingsV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ProductV1> createProductUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Product",required=true) @PathVariable("productIdToCreate") String productIdToCreate,@ApiParam(value = "A new Product to be created"  )  @Valid @RequestBody ProductV1 toBeCreated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ProductV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"internal\" : false,    \"name\" : \"AMS360\",    \"id\" : \"VERTAFORE\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }}", SingleResponseV1ProductV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ProductV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ProductV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1RealmV1> createRealmUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new Realm to be created"  )  @Valid @RequestBody RealmV1 newRealmV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1RealmV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"name\" : \"name\",    \"description\" : \"description\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1RealmV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1RealmV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1RealmV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1> createRoleUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new Role to be created"  )  @Valid @RequestBody RoleV1 newRoleV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  },  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", SingleResponseWithRelatedObjectsV1RoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> createUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new AuthUserV1 to be created"  )  @Valid @RequestBody AuthUserV1 toBeCreated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> deactivateUserUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the User",required=true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteAndMigrateEntityUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of Entity to delete",required=true) @PathVariable("entityIdToDelete") String entityIdToDelete,@NotNull @ApiParam(value = "ID of Entity to migrate child data to", required = true) @Valid @RequestParam(value = "migrateTo", required = true) String migrateTo) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteAuthGroupUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of AuthGroup to delete",required=true) @PathVariable("groupId") String groupId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteEntityConcordanceUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the Entity",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The EntityConcordance to be deleted"  )  @Valid @RequestBody EntityConcordanceV1 toBeDeleted) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteEntityUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of Entity to delete",required=true) @PathVariable("entityIdToDelete") String entityIdToDelete,@NotNull @ApiParam(value = "requestParams", required = true) @Valid @RequestParam(value = "requestParams", required = true) ERRORUNKNOWN requestParams) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteRealmUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Name of the Realm to delete",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteRoleUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Role to delete",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteTenantUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Tenant to delete",required=true) @PathVariable("tenantIdToDelete") String tenantIdToDelete) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> deleteUserUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the User",required=true) @PathVariable("userId") String userId,@ApiParam(value = "Optional flag to delete user in Descendant contexts", defaultValue = "false") @Valid @RequestParam(value = "cascade", required = false, defaultValue="false") Boolean cascade) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ListServiceRoleV1> findAllServiceRolesForServiceUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Name of the service the ServiceRole belongs to.",required=true) @PathVariable("serviceName") String serviceName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ListServiceRoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"entitlements\" : \"['example-service.user.delete', 'example-service.user.read']\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"name\" : \"admin\",    \"description\" : \"Represents Admin Functionality\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"serviceName\" : \"example-service\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }, {    \"entitlements\" : \"['example-service.user.delete', 'example-service.user.read']\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"name\" : \"admin\",    \"description\" : \"Represents Admin Functionality\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"serviceName\" : \"example-service\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  } ]}", SingleResponseV1ListServiceRoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ListServiceRoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ListServiceRoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1AuthGroupMembershipV1LimitOffsetPagingInfoV1> findAuthUserMembershipsInAuthGroupUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the AuthGroup",required=true) @PathVariable("groupId") String groupId,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1AuthGroupMembershipV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"productId\" : \"productId\",    \"authGroupContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"tenantId\" : \"tenantId\",    \"entityId\" : \"entityId\",    \"userId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"authGroupMembershipSource\" : \"UNFEDERATED\"  }, {    \"productId\" : \"productId\",    \"authGroupContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"tenantId\" : \"tenantId\",    \"entityId\" : \"entityId\",    \"userId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"authGroupMembershipSource\" : \"UNFEDERATED\"  } ]}", PagedResponseV1AuthGroupMembershipV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1AuthGroupMembershipV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1AuthGroupMembershipV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1> findRoleByIdsUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byIds", defaultValue = "byIds") @Valid @RequestParam(value = "filter", required = true, defaultValue="byIds") String filter,@ApiParam(value = "The list of ids to search by") @Valid @RequestParam(value = "ids", required = false) java.util.List<String> ids,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }, {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  } ],  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1> findRoleByNameUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "The Name to search by", required = true) @Valid @RequestParam(value = "name", required = true) String name,@NotNull @ApiParam(value = "", required = true, allowableValues = "byName", defaultValue = "byName") @Valid @RequestParam(value = "filter", required = true, defaultValue="byName") String filter) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  },  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", SingleResponseWithRelatedObjectsV1RoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1AuthGroupRoleAssignmentV1LimitOffsetPagingInfoV1> findRolesForGroupUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "groupId",required=true) @PathVariable("groupId") String groupId,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1AuthGroupRoleAssignmentV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"roleContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"productId\" : \"productId\",    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"authGroupContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"tenantId\" : \"tenantId\",    \"roleAssignmentSource\" : \"FEDERATED\",    \"entityId\" : \"entityId\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"roleContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"productId\" : \"productId\",    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"authGroupContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"tenantId\" : \"tenantId\",    \"roleAssignmentSource\" : \"FEDERATED\",    \"entityId\" : \"entityId\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  } ]}", PagedResponseV1AuthGroupRoleAssignmentV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1AuthGroupRoleAssignmentV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1AuthGroupRoleAssignmentV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1AuthUserRoleAssignmentV1LimitOffsetPagingInfoV1> findRolesForUserUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byUserId", defaultValue = "byUserId") @Valid @RequestParam(value = "filter", required = true, defaultValue="byUserId") String filter,@ApiParam(value = "Id of the User") @Valid @RequestParam(value = "userId", required = false) String userId,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1AuthUserRoleAssignmentV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"productId\" : \"productId\",    \"roleContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"groupId\" : \"groupId\",    \"userIds\" : [ \"userIds\", \"userIds\" ],    \"tenantId\" : \"tenantId\",    \"roleAssignmentSource\" : \"federated\",    \"entityId\" : \"entityId\",    \"userId\" : \"userId\"  }, {    \"productId\" : \"productId\",    \"roleContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"groupId\" : \"groupId\",    \"userIds\" : [ \"userIds\", \"userIds\" ],    \"tenantId\" : \"tenantId\",    \"roleAssignmentSource\" : \"federated\",    \"entityId\" : \"entityId\",    \"userId\" : \"userId\"  } ]}", PagedResponseV1AuthUserRoleAssignmentV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1AuthUserRoleAssignmentV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1AuthUserRoleAssignmentV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1OAuthTokenV1> getAccessTokenUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The user we are getting the token for"  )  @Valid @RequestBody IDPUserV1 idpUser) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1OAuthTokenV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"expiresIn\" : 0,    \"accessToken\" : \"accessToken\",    \"tokenType\" : \"tokenType\",    \"refreshToken\" : \"refreshToken\"  }}", SingleResponseV1OAuthTokenV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1OAuthTokenV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1OAuthTokenV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AssignedRoleCountV1> getAssignedRoleCountUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byRoleIds", defaultValue = "byRoleIds") @Valid @RequestParam(value = "filter", required = true, defaultValue="byRoleIds") String filter,@ApiParam(value = "RoleIds that will be used to search for user role assignments") @Valid @RequestParam(value = "roleIds", required = false) java.util.List<String> roleIds,@ApiParam(value = "Filter on status", allowableValues = "true, false, all", defaultValue = "all") @Valid @RequestParam(value = "activeFilter", required = false, defaultValue="all") String activeFilter) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AssignedRoleCountV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"roleIds\" : \"['UUID1', 'UUID2']\",    \"userStatus\" : \"true\",    \"tenantId\" : \"exampleTenant\",    \"entityId\" : \"exampleEntityId\",    \"assignedRoleCount\" : 0  }}", SingleResponseV1AssignedRoleCountV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AssignedRoleCountV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AssignedRoleCountV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthGroupV1> getAuthGroupUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the AuthGroup",required=true) @PathVariable("groupId") String groupId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthGroupV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"Managers\",    \"description\" : \"Admins for the system\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"sourceAclId\" : \"Source ACL ID\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"groupCreationSource\" : \"federated\"  }}", SingleResponseV1AuthGroupV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthGroupV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthGroupV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1AuthGroupV1LimitOffsetPagingInfoV1> getAuthGroupUsingGET1(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1AuthGroupV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"Managers\",    \"description\" : \"Admins for the system\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"sourceAclId\" : \"Source ACL ID\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"groupCreationSource\" : \"federated\"  }, {    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"name\" : \"Managers\",    \"description\" : \"Admins for the system\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"sourceAclId\" : \"Source ACL ID\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"groupCreationSource\" : \"federated\"  } ]}", PagedResponseV1AuthGroupV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1AuthGroupV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1AuthGroupV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ContextualAuthInfoV1> getContextualAuthPreviewUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The service to retrieve the entitlements for",required=true) @PathVariable("serviceName") String serviceName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ContextualAuthInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"user\" : {      \"displayName\" : \"displayName\",      \"familyName\" : \"familyName\",      \"givenName\" : \"givenName\",      \"id\" : \"id\"    },    \"authz\" : {      \"entitlementAssignments\" : {        \"key\" : \"entitlementAssignments\"      },      \"entities\" : {        \"key\" : {          \"name\" : \"name\",          \"zoneId\" : \"zoneId\",          \"id\" : \"id\",          \"parentEntityId\" : \"parentEntityId\",          \"type\" : \"type\"        }      },      \"groupAssignments\" : [ \"groupAssignments\", \"groupAssignments\" ]    }  }}", SingleResponseV1ContextualAuthInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ContextualAuthInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ContextualAuthInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ContextualAuthInfoV1> getContextualAuthUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The service to retrieve the entitlements for",required=true) @PathVariable("serviceName") String serviceName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ContextualAuthInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"user\" : {      \"displayName\" : \"displayName\",      \"familyName\" : \"familyName\",      \"givenName\" : \"givenName\",      \"id\" : \"id\"    },    \"authz\" : {      \"entitlementAssignments\" : {        \"key\" : \"entitlementAssignments\"      },      \"entities\" : {        \"key\" : {          \"name\" : \"name\",          \"zoneId\" : \"zoneId\",          \"id\" : \"id\",          \"parentEntityId\" : \"parentEntityId\",          \"type\" : \"type\"        }      },      \"groupAssignments\" : [ \"groupAssignments\", \"groupAssignments\" ]    }  }}", SingleResponseV1ContextualAuthInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ContextualAuthInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ContextualAuthInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1EntityConcordanceV1LimitOffsetPagingInfoV1> getEntityConcordanceByEntityIdsUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "The Entity Id to search by",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1EntityConcordanceV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"sourceType\" : \"AMS360\",    \"realmName\" : \"realmName\",    \"sourceEntityId\" : \"sourceEntityId\",    \"tenantId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"entityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\"  }, {    \"sourceType\" : \"AMS360\",    \"realmName\" : \"realmName\",    \"sourceEntityId\" : \"sourceEntityId\",    \"tenantId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"entityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\"  } ]}", PagedResponseV1EntityConcordanceV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1EntityConcordanceV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1EntityConcordanceV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1EntityBranchV1> getEntityHierarchyUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of Entity to retrieve",required=true) @PathVariable("entityIdToRetrieve") String entityIdToRetrieve,@ApiParam(value = "Number of ancestors to include,  all includes all ancestors", defaultValue = "0") @Valid @RequestParam(value = "ancestor_levels", required = false, defaultValue="0") String ancestorLevels,@ApiParam(value = "Ancestor properties to include", allowableValues = "all, summary", defaultValue = "summary") @Valid @RequestParam(value = "ancestor_properties", required = false, defaultValue="summary") String ancestorProperties,@ApiParam(value = "Number of descendants to include,  all includes all descendants", defaultValue = "0") @Valid @RequestParam(value = "descendant_levels", required = false, defaultValue="0") String descendantLevels,@ApiParam(value = "Descendants properties to include", allowableValues = "all, summary", defaultValue = "summary") @Valid @RequestParam(value = "descendant_properties", required = false, defaultValue="summary") String descendantProperties,@ApiParam(value = "Entity type to filter on") @Valid @RequestParam(value = "entity_type", required = false) java.util.List<String> entityType) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1EntityBranchV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"metadata\" : \"{'ams-web-ui': { 'office-type': 'shared' }}\",    \"webAddress\" : \"https://insurancewebsite.com/\",    \"entityType\" : \"AGENCY\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"parentEntityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"descendants\" : \"{ '1234-1070-5707-e7h2-1dkl' : {'name' : 'Insurance Division Parent','parent_entity_id': '1234-1070-5707-e7h2-1dkl' }\",    \"emailAddress\" : \"example@gmail.com\",    \"name\" : \"Insurance West Division - Chicago\",    \"zoneId\" : \"America/Los_Angeles\",    \"attributes\" : [ {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    }, {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    } ],    \"locations\" : [ {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    }, {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    } ],    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"ancestors\" : \"{ '1234-1070-5707-e7h2-1dkl' : {'name' : 'Insurance Division Parent','parent_entity_id': '1234-1070-5707-e7h2-1dkl' }\"  }}", SingleResponseV1EntityBranchV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1EntityBranchV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1EntityBranchV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1Mapstringboolean> getEntityIdsForEntitlementUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "Entitlement to check", required = true) @Valid @RequestParam(value = "entitlement", required = true) String entitlement,@NotNull @ApiParam(value = "User Id", required = true) @Valid @RequestParam(value = "userId", required = true) String userId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byEntitlement", defaultValue = "byEntitlement") @Valid @RequestParam(value = "filter", required = true, defaultValue="byEntitlement") String filter,@ApiParam(value = "List of Entities") @Valid @RequestParam(value = "entityIds", required = false) java.util.List<String> entityIds) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1Mapstringboolean>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"key\" : true  }}", SingleResponseV1Mapstringboolean.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1Mapstringboolean>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1Mapstringboolean>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ProductV1> getProductByNameUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byName", defaultValue = "byName") @Valid @RequestParam(value = "filter", required = true, defaultValue="byName") String filter,@ApiParam(value = "Name of the Product") @Valid @RequestParam(value = "name", required = false) String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ProductV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"internal\" : false,    \"name\" : \"AMS360\",    \"id\" : \"VERTAFORE\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }}", SingleResponseV1ProductV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ProductV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ProductV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ProductV1> getProductUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Product",required=true) @PathVariable("productIdToRetrieve") String productIdToRetrieve) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ProductV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"internal\" : false,    \"name\" : \"AMS360\",    \"id\" : \"VERTAFORE\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }}", SingleResponseV1ProductV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ProductV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ProductV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1ProductV1LimitOffsetPagingInfoV1> getProductsByIdsUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byIds", defaultValue = "byIds") @Valid @RequestParam(value = "filter", required = true, defaultValue="byIds") String filter,@NotNull @ApiParam(value = "internal", required = true) @Valid @RequestParam(value = "internal", required = true) String internal,@ApiParam(value = "The ids to search by") @Valid @RequestParam(value = "ids", required = false) java.util.List<String> ids,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1ProductV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"internal\" : false,    \"name\" : \"AMS360\",    \"id\" : \"VERTAFORE\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }, {    \"internal\" : false,    \"name\" : \"AMS360\",    \"id\" : \"VERTAFORE\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  } ]}", PagedResponseV1ProductV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1ProductV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1ProductV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1RealmV1> getRealmUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Realm to get",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1RealmV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"name\" : \"name\",    \"description\" : \"description\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1RealmV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1RealmV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1RealmV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1OAuthTokenV1> getRefreshTokenUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Refresh token"  )  @Valid @RequestBody String refreshToken) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1OAuthTokenV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"expiresIn\" : 0,    \"accessToken\" : \"accessToken\",    \"tokenType\" : \"tokenType\",    \"refreshToken\" : \"refreshToken\"  }}", SingleResponseV1OAuthTokenV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1OAuthTokenV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1OAuthTokenV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1> getRoleByNameUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "The Name to search by",required=true) @PathVariable("name") String name,@NotNull @ApiParam(value = "", required = true, allowableValues = "name", defaultValue = "name") @Valid @RequestParam(value = "lookupBy", required = true, defaultValue="name") String lookupBy) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  },  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", SingleResponseWithRelatedObjectsV1RoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1> getRoleLookupByIdUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Role to be retrieved",required=true) @PathVariable("id") String id,@NotNull @ApiParam(value = "", required = true, allowableValues = "id", defaultValue = "id") @Valid @RequestParam(value = "lookupBy", required = true, defaultValue="id") String lookupBy) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  },  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", SingleResponseWithRelatedObjectsV1RoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1> getRoleUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Role to be retrieved",required=true) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  },  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", SingleResponseWithRelatedObjectsV1RoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ServiceRoleV1> getRoleUsingGET1(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Name of the service the ServiceRole belongs to.",required=true) @PathVariable("serviceName") String serviceName,@ApiParam(value = "Name of the ServiceRole to get",required=true) @PathVariable("name") String name) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ServiceRoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"entitlements\" : \"['example-service.user.delete', 'example-service.user.read']\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"name\" : \"admin\",    \"description\" : \"Represents Admin Functionality\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"serviceName\" : \"example-service\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1ServiceRoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ServiceRoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ServiceRoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1EntityV1> getTenantUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Parent Tenant (VERTAFORE), providing Context for this Tenant",required=true) @PathVariable("tenantIdToRetrieve") String tenantIdToRetrieve) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1EntityV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"emailAddress\" : \"example@gmail.com\",    \"metadata\" : \"{'ams-web-ui': { 'office-type': 'shared' }}\",    \"entityType\" : \"AGENCY\",    \"webAddress\" : \"https://insurancewebsite.com/\",    \"name\" : \"Insurance West Division - Chicago\",    \"zoneId\" : \"America/Los_Angeles\",    \"attributes\" : [ {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    }, {      \"value\" : \"Hispanic\",      \"key\" : \"Ethnicity\"    } ],    \"locations\" : [ {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    }, {      \"addresses\" : [ {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      }, {        \"country\" : \"US\",        \"streetAddress\" : \"100 Universal City Plaza\",        \"formatted\" : \"100 Universal City Plaza\nHollywood, CA 91608 USA\",        \"postalCode\" : \"91608\",        \"locality\" : \"Hollywood\",        \"streetAddress2\" : \"#201\",        \"mailing\" : true,        \"region\" : \"CA\",        \"type\" : \"Business Address\",        \"preferred\" : true      } ],      \"phoneNumbers\" : [ {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      }, {        \"extension\" : \"4567\",        \"phoneNumber\" : \"5555555555\",        \"countryCode\" : \"1\",        \"type\" : \"Cell Phone\",        \"preferred\" : true      } ]    } ],    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"parentEntityId\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1EntityV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1EntityV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> getUserByUsernameUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Username of the user") @Valid @RequestParam(value = "withUsername", required = false) String withUsername,@ApiParam(value = "Optional Realm to find users of other realms") @Valid @RequestParam(value = "inRealm", required = false) String inRealm) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserSecretV1> getUserSecretUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "User Id of the Service User",required=true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserSecretV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"secret\" : \"secret\"  }}", SingleResponseV1AuthUserSecretV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserSecretV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserSecretV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> getUserUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the User",required=true) @PathVariable("userId") String userId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1UserAndAssignedRolesV1LimitOffsetPagingInfoV1> getUsersByAssignedRolesUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "ByAssignedRoles", defaultValue = "ByAssignedRoles") @Valid @RequestParam(value = "filter", required = true, defaultValue="ByAssignedRoles") String filter,@ApiParam(value = "RoleIds that will be used to search for user role assignments") @Valid @RequestParam(value = "roleIds", required = false) java.util.List<String> roleIds,@ApiParam(value = "Filter on status", allowableValues = "true, false, all", defaultValue = "all") @Valid @RequestParam(value = "activeFilter", required = false, defaultValue="all") String activeFilter,@ApiParam(value = "entityIds") @Valid @RequestParam(value = "entityIds", required = false) java.util.List<String> entityIds,@ApiParam(value = "userIds") @Valid @RequestParam(value = "userIds", required = false) java.util.List<String> userIds,@ApiParam(value = "displayName") @Valid @RequestParam(value = "displayName", required = false) String displayName,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1UserAndAssignedRolesV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"roles\" : {      \"key\" : [ \"roles\", \"roles\" ]    },    \"user\" : {      \"profileUrl\" : \"https://login.example.com/bjensen\",      \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",      \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",      \"preferredLanguage\" : \"en-US\",      \"displayName\" : \"Babs Jensen\",      \"nickName\" : \"Babs\",      \"realmName\" : \"realmName\",      \"active\" : false,      \"contexts\" : [ {        \"tenantId\" : \"tenantId\",        \"active\" : false,        \"entityId\" : \"entityId\"      }, {        \"tenantId\" : \"tenantId\",        \"active\" : false,        \"entityId\" : \"entityId\"      } ],      \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",      \"title\" : \"Tour Guide\",      \"createdOn\" : \"1970-01-01T00:00:00.000Z\",      \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",      \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",      \"password\" : \"password\",      \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",      \"localeLanguageTag\" : \"en-US\",      \"name\" : {        \"honorificSuffix\" : \"III\",        \"formatted\" : \"Ms. Barbara Jane Jensen, III\",        \"familyName\" : \"Jensen\",        \"givenName\" : \"Barbara\",        \"honorificPrefix\" : \"Ms.\",        \"middleName\" : \"Jane\"      },      \"zoneId\" : \"America/Los_Angeles\",      \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",      \"userType\" : \"Employee\",      \"username\" : \"bjjensen\"    }  }, {    \"roles\" : {      \"key\" : [ \"roles\", \"roles\" ]    },    \"user\" : {      \"profileUrl\" : \"https://login.example.com/bjensen\",      \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",      \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",      \"preferredLanguage\" : \"en-US\",      \"displayName\" : \"Babs Jensen\",      \"nickName\" : \"Babs\",      \"realmName\" : \"realmName\",      \"active\" : false,      \"contexts\" : [ {        \"tenantId\" : \"tenantId\",        \"active\" : false,        \"entityId\" : \"entityId\"      }, {        \"tenantId\" : \"tenantId\",        \"active\" : false,        \"entityId\" : \"entityId\"      } ],      \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",      \"title\" : \"Tour Guide\",      \"createdOn\" : \"1970-01-01T00:00:00.000Z\",      \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",      \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",      \"password\" : \"password\",      \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",      \"localeLanguageTag\" : \"en-US\",      \"name\" : {        \"honorificSuffix\" : \"III\",        \"formatted\" : \"Ms. Barbara Jane Jensen, III\",        \"familyName\" : \"Jensen\",        \"givenName\" : \"Barbara\",        \"honorificPrefix\" : \"Ms.\",        \"middleName\" : \"Jane\"      },      \"zoneId\" : \"America/Los_Angeles\",      \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",      \"userType\" : \"Employee\",      \"username\" : \"bjjensen\"    }  } ]}", PagedResponseV1UserAndAssignedRolesV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1UserAndAssignedRolesV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1UserAndAssignedRolesV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1> getUsersByContextUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byScope", defaultValue = "byScope") @Valid @RequestParam(value = "filter", required = true, defaultValue="byScope") String filter,@ApiParam(value = "Context scope", allowableValues = "request, descendants, ancestors", defaultValue = "request") @Valid @RequestParam(value = "scope", required = false, defaultValue="request") String scope,@ApiParam(value = "Allows to search users by username or displayName(full or partial match)") @Valid @RequestParam(value = "userOrDisplayNameIncludes", required = false) String userOrDisplayNameIncludes,@ApiParam(value = "Sort by", allowableValues = "displayName, username, active", defaultValue = "displayName") @Valid @RequestParam(value = "sortBy", required = false, defaultValue="displayName") String sortBy,@ApiParam(value = "Sort by direction", allowableValues = "asc, desc", defaultValue = "asc") @Valid @RequestParam(value = "sortDirection", required = false, defaultValue="asc") String sortDirection,@ApiParam(value = "Filter on status", allowableValues = "true, false, all", defaultValue = "all") @Valid @RequestParam(value = "activeFilter", required = false, defaultValue="all") String activeFilter,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }, {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  } ]}", PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1> getUsersByIdsUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byIds", defaultValue = "byIds") @Valid @RequestParam(value = "filter", required = true, defaultValue="byIds") String filter,@ApiParam(value = "The ids to search by") @Valid @RequestParam(value = "ids", required = false) java.util.List<String> ids,@ApiParam(value = "The maximum number of items to include in the response") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize,@ApiParam(value = "The page of items to retrieve") @Valid @RequestParam(value = "page", required = false) Integer page) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"paging\" : {    \"method\" : \"method\",    \"numberOfElements\" : 0,    \"totalPages\" : 5,    \"pageSize\" : 1,    \"page\" : 6,    \"totalElements\" : 5  },  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }, {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  } ]}", PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<PagedResponseV1AuthUserV1LimitOffsetPagingInfoV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ListIDPUserMetaDataV1> getUsersLastLoginTimesByIdsUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@NotNull @ApiParam(value = "", required = true, allowableValues = "byIds", defaultValue = "byIds") @Valid @RequestParam(value = "filter", required = true, defaultValue="byIds") String filter,@ApiParam(value = "The ids to search by") @Valid @RequestParam(value = "ids", required = false) java.util.List<String> ids) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ListIDPUserMetaDataV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"lastLoginTime\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\"  }, {    \"lastLoginTime\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\"  } ]}", SingleResponseV1ListIDPUserMetaDataV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ListIDPUserMetaDataV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ListIDPUserMetaDataV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthenticationSettingsV1> getUsingGET(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthenticationSettingsV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"computed\" : {      \"mfaEnabled\" : false    },    \"mfaEnabled\" : false,    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }}", SingleResponseV1AuthenticationSettingsV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthenticationSettingsV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthenticationSettingsV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthGroupRoleAssignmentV1> grantRoleUsingPOST(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "groupId",required=true) @PathVariable("groupId") String groupId,@ApiParam(value = "roleId",required=true) @PathVariable("roleId") String roleId,@ApiParam(value = "A new Auth Group Role Assignment to be created"  )  @Valid @RequestBody AuthGroupRoleAssignmentV1 authGroupRoleAssignmentV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthGroupRoleAssignmentV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"roleContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"productId\" : \"productId\",    \"deletedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"authGroupContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"tenantId\" : \"tenantId\",    \"roleAssignmentSource\" : \"FEDERATED\",    \"entityId\" : \"entityId\",    \"updatedOn\" : \"2000-01-23T04:56:07.000+00:00\",    \"createdOn\" : \"2000-01-23T04:56:07.000+00:00\"  }}", SingleResponseV1AuthGroupRoleAssignmentV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthGroupRoleAssignmentV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthGroupRoleAssignmentV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserRoleAssignmentV1> grantRoleUsingPOST1(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "A new AuthUserRoleAssignmentV1 to be created"  )  @Valid @RequestBody AuthUserRoleAssignmentV1 authUserRoleAssignmentV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserRoleAssignmentV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"productId\" : \"productId\",    \"roleContextualId\" : {      \"productId\" : \"productId\",      \"tenantId\" : \"tenantId\",      \"entityId\" : \"entityId\",      \"id\" : \"id\"    },    \"groupId\" : \"groupId\",    \"userIds\" : [ \"userIds\", \"userIds\" ],    \"tenantId\" : \"tenantId\",    \"roleAssignmentSource\" : \"federated\",    \"entityId\" : \"entityId\",    \"userId\" : \"userId\"  }}", SingleResponseV1AuthUserRoleAssignmentV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserRoleAssignmentV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserRoleAssignmentV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> patchUpdateUserUsingPATCH(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the User to update",required=true) @PathVariable("userId") String userId,@ApiParam(value = "A JSON Patch to update an existing User"  )  @Valid @RequestBody JsonPatchPatchableAuthUserV1 jsonPatch) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> revokeAuthUserFromUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Id of the AuthGroup",required=true) @PathVariable("groupId") String groupId,@ApiParam(value = "Id of the AuthUser",required=true) @PathVariable("userId") String userId,@ApiParam(value = "authGroupMembershipV1" ,required=true )  @Valid @RequestBody AuthGroupMembershipV1 authGroupMembershipV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> revokeRoleFromUsingDELETE(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "groupId",required=true) @PathVariable("groupId") String groupId,@ApiParam(value = "roleId",required=true) @PathVariable("roleId") String roleId,@ApiParam(value = "An Auth Group Role Assignment to be deleted"  )  @Valid @RequestBody AuthGroupRoleAssignmentV1 authGroupRoleAssignmentV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<EmptyResponseV1> revokeRoleFromUsingDELETE1(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "An AuthUserRoleAssignmentV1 to be deleted"  )  @Valid @RequestBody AuthUserRoleAssignmentV1 authUserRoleAssignmentV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<EmptyResponseV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  }}", EmptyResponseV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<EmptyResponseV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<EmptyResponseV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1ListServiceRoleV1> saveAllUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "serviceName",required=true) @PathVariable("serviceName") String serviceName,@ApiParam(value = "A list of Roles"  )  @Valid @RequestBody java.util.List<ServiceRoleV1> serviceRoleV1s) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1ListServiceRoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : [ {    \"entitlements\" : \"['example-service.user.delete', 'example-service.user.read']\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"name\" : \"admin\",    \"description\" : \"Represents Admin Functionality\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"serviceName\" : \"example-service\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }, {    \"entitlements\" : \"['example-service.user.delete', 'example-service.user.read']\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"name\" : \"admin\",    \"description\" : \"Represents Admin Functionality\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"serviceName\" : \"example-service\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  } ]}", SingleResponseV1ListServiceRoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1ListServiceRoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1ListServiceRoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1RealmV1> updateRealmUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "Name of the Realm to replace",required=true) @PathVariable("name") String name,@ApiParam(value = "A Realm to replace the existing Realm"  )  @Valid @RequestBody RealmV1 newRealmV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1RealmV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"name\" : \"name\",    \"description\" : \"description\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  }}", SingleResponseV1RealmV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1RealmV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1RealmV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1> updateRoleUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "ID of the Role to replace",required=true) @PathVariable("id") String id,@ApiParam(value = "A Role to replace the existing Role"  )  @Valid @RequestBody RoleV1 newRoleV1) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"subRoleContextualIds\" : \"[{\\"id\\":\\"some-subRole-id\\",\\"productId\\":\\"core\\",\\"tenantId\\":\\"some-tenant-id\\",\\"entityId\\":\\"some-entity-id\\"}]\",    \"metadata\" : \"{'structure': { 'categories': [ { 'label': 'Customers', 'serviceRoles': [ 'Customers-View', 'Customers-Manage' ] 'subCategories': [] }] }}\",    \"productId\" : \"exampleProduct\",    \"subServiceRoles\" : \"{'example-service': ['admin'], 'other-example-service': ['viewThing', 'updateThing']}\",    \"tenantId\" : \"exampleTenant\",    \"name\" : \"Administration\",    \"description\" : \"Represents Admin Functionality\",    \"entityId\" : \"exampleEntity\",    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"id\" : \"20b9b890-4f2f-4dea-8dad-48d34ee22dbe\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\"  },  \"relatedObjects\" : {    \"key\" : {      \"key\" : \"{}\"    }  }}", SingleResponseWithRelatedObjectsV1RoleV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseWithRelatedObjectsV1RoleV1>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<SingleResponseV1AuthUserV1> updateUserUsingPUT(@ApiParam(value = "ID of the product for which the request is being made",required=true) @PathVariable("productId") String productId,@ApiParam(value = "ID of the tenant for which the request is being made",required=true) @PathVariable("tenantId") String tenantId,@ApiParam(value = "ID of the entity for which the request is being made",required=true) @PathVariable("entityId") String entityId,@ApiParam(value = "userId",required=true) @PathVariable("userId") String userId,@ApiParam(value = "AuthUser to be Updated"  )  @Valid @RequestBody AuthUserV1 toBeUpdated) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<SingleResponseV1AuthUserV1>(objectMapper.readValue("{  \"spanId\" : \"spanId\",  \"traceId\" : \"traceId\",  \"requestId\" : \"requestId\",  \"error\" : {    \"message\" : \"message\",    \"status\" : 6  },  \"content\" : {    \"profileUrl\" : \"https://login.example.com/bjensen\",    \"lastLogin\" : \"1970-01-01T00:00:00.000Z\",    \"addresses\" : \"[{ 'formatted': '555 Fake St., Apartment 1, Denver, CO 00000 USA', 'streetAddress': '555 Fake St., Apartment 1', 'locality': 'Denver', 'region': 'CO', 'postalCode': '00000', 'country': 'USA', 'type': 'home', 'preferred': true }]\",    \"preferredLanguage\" : \"en-US\",    \"displayName\" : \"Babs Jensen\",    \"nickName\" : \"Babs\",    \"realmName\" : \"realmName\",    \"active\" : false,    \"contexts\" : [ {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    }, {      \"tenantId\" : \"tenantId\",      \"active\" : false,      \"entityId\" : \"entityId\"    } ],    \"updatedOn\" : \"1970-01-01T00:00:00.000Z\",    \"title\" : \"Tour Guide\",    \"createdOn\" : \"1970-01-01T00:00:00.000Z\",    \"photos\" : \"[{ 'value': 'https://photos.example.com/profilephoto/72930000000Ccne/F', 'type': 'thumbnail', 'display': 'babsProfilePic', 'primary': 'true' }]\",    \"phoneNumbers\" : \"[{ 'countryCode': '1', 'phoneNumber': '555-555-5555', 'extension': '#222', 'type': 'work', 'preferred': true }]\",    \"password\" : \"password\",    \"emailAddresses\" : \"[{ 'value': 'test@example.example', 'type': 'email', 'primary': 'true' }]\",    \"localeLanguageTag\" : \"en-US\",    \"name\" : {      \"honorificSuffix\" : \"III\",      \"formatted\" : \"Ms. Barbara Jane Jensen, III\",      \"familyName\" : \"Jensen\",      \"givenName\" : \"Barbara\",      \"honorificPrefix\" : \"Ms.\",      \"middleName\" : \"Jane\"    },    \"zoneId\" : \"America/Los_Angeles\",    \"id\" : \"b647a79e-b500-4640-a1bf-2aeb2efef379\",    \"userType\" : \"Employee\",    \"username\" : \"bjjensen\"  }}", SingleResponseV1AuthUserV1.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<SingleResponseV1AuthUserV1>(HttpStatus.NOT_IMPLEMENTED);
    }

}
