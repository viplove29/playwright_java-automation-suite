package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.ErrorResponseV1;
import modelPackage.LimitOffsetPagingInfoV1;
import modelPackage.RoleV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1   {
  @JsonProperty("content")
  @Valid
  private java.util.List<RoleV1> content = null;

  @JsonProperty("error")
  private ErrorResponseV1 error = null;

  @JsonProperty("paging")
  private LimitOffsetPagingInfoV1 paging = null;

  @JsonProperty("relatedObjects")
  @Valid
  private java.util.Map<String, java.util.Map<String, Object>> relatedObjects = null;

  @JsonProperty("requestId")
  private String requestId = null;

  @JsonProperty("spanId")
  private String spanId = null;

  @JsonProperty("traceId")
  private String traceId = null;

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 content(java.util.List<RoleV1> content) {
    this.content = content;
    return this;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 addContentItem(RoleV1 contentItem) {
    if (this.content == null) {
      this.content = new java.util.ArrayList<RoleV1>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * The data elements requested
   * @return content
  **/
  @ApiModelProperty(readOnly = true, value = "The data elements requested")

  @Valid

  public java.util.List<RoleV1> getContent() {
    return content;
  }

  public void setContent(java.util.List<RoleV1> content) {
    this.content = content;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 error(ErrorResponseV1 error) {
    this.error = error;
    return this;
  }

  /**
   * This field will contain information about the error if one occurred
   * @return error
  **/
  @ApiModelProperty(readOnly = true, value = "This field will contain information about the error if one occurred")

  @Valid

  public ErrorResponseV1 getError() {
    return error;
  }

  public void setError(ErrorResponseV1 error) {
    this.error = error;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 paging(LimitOffsetPagingInfoV1 paging) {
    this.paging = paging;
    return this;
  }

  /**
   * The paging method used
   * @return paging
  **/
  @ApiModelProperty(readOnly = true, value = "The paging method used")

  @Valid

  public LimitOffsetPagingInfoV1 getPaging() {
    return paging;
  }

  public void setPaging(LimitOffsetPagingInfoV1 paging) {
    this.paging = paging;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 relatedObjects(java.util.Map<String, java.util.Map<String, Object>> relatedObjects) {
    this.relatedObjects = relatedObjects;
    return this;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 putRelatedObjectsItem(String key, java.util.Map<String, Object> relatedObjectsItem) {
    if (this.relatedObjects == null) {
      this.relatedObjects = new java.util.HashMap<String, java.util.Map<String, Object>>();
    }
    this.relatedObjects.put(key, relatedObjectsItem);
    return this;
  }

  /**
   * A map of different kinds of objects related to the response. Each key in the map is a type of object that is referenced from the content of the response, e.g. a User or an Entity. Each value of the map is itself a map, but of the objects' IDs to the objects themselves so as to allow looking each object up via the ID that was provided in the content.
   * @return relatedObjects
  **/
  @ApiModelProperty(readOnly = true, value = "A map of different kinds of objects related to the response. Each key in the map is a type of object that is referenced from the content of the response, e.g. a User or an Entity. Each value of the map is itself a map, but of the objects' IDs to the objects themselves so as to allow looking each object up via the ID that was provided in the content.")

  @Valid

  public java.util.Map<String, java.util.Map<String, Object>> getRelatedObjects() {
    return relatedObjects;
  }

  public void setRelatedObjects(java.util.Map<String, java.util.Map<String, Object>> relatedObjects) {
    this.relatedObjects = relatedObjects;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  /**
   * The unique ID of this request.
   * @return requestId
  **/
  @ApiModelProperty(readOnly = true, value = "The unique ID of this request.")


  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 spanId(String spanId) {
    this.spanId = spanId;
    return this;
  }

  /**
   * The unique ID of the span.
   * @return spanId
  **/
  @ApiModelProperty(readOnly = true, value = "The unique ID of the span.")


  public String getSpanId() {
    return spanId;
  }

  public void setSpanId(String spanId) {
    this.spanId = spanId;
  }

  public PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 traceId(String traceId) {
    this.traceId = traceId;
    return this;
  }

  /**
   * The unique ID of the trace.
   * @return traceId
  **/
  @ApiModelProperty(readOnly = true, value = "The unique ID of the trace.")


  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 = (PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1) o;
    return Objects.equals(this.content, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.content) &&
        Objects.equals(this.error, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.error) &&
        Objects.equals(this.paging, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.paging) &&
        Objects.equals(this.relatedObjects, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.relatedObjects) &&
        Objects.equals(this.requestId, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.requestId) &&
        Objects.equals(this.spanId, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.spanId) &&
        Objects.equals(this.traceId, pagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1.traceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, error, paging, relatedObjects, requestId, spanId, traceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagedResponseWithRelatedObjectsV1RoleV1LimitOffsetPagingInfoV1 {\n");
    
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
    sb.append("    paging: ").append(toIndentedString(paging)).append("\n");
    sb.append("    relatedObjects: ").append(toIndentedString(relatedObjects)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    spanId: ").append(toIndentedString(spanId)).append("\n");
    sb.append("    traceId: ").append(toIndentedString(traceId)).append("\n");
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

