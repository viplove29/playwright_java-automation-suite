/*
 * Authentication Service API
 * Authentication and Authorization
 *
 * OpenAPI spec version: 1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package modelPackage;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import modelPackage.ErrorResponseV1;
import modelPackage.ServiceRoleV1;

/**
 * SingleResponseV1ServiceRoleV1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-04-02T00:57:29.075-06:00")
public class SingleResponseV1ServiceRoleV1 {
  @SerializedName("content")
  private ServiceRoleV1 content = null;

  @SerializedName("error")
  private ErrorResponseV1 error = null;

  @SerializedName("requestId")
  private String requestId = null;

  @SerializedName("spanId")
  private String spanId = null;

  @SerializedName("traceId")
  private String traceId = null;

   /**
   * The response data element
   * @return content
  **/
  @ApiModelProperty(value = "The response data element")
  public ServiceRoleV1 getContent() {
    return content;
  }

   /**
   * This field will contain information about the error if one occurred
   * @return error
  **/
  @ApiModelProperty(value = "This field will contain information about the error if one occurred")
  public ErrorResponseV1 getError() {
    return error;
  }

   /**
   * The unique ID of this request.
   * @return requestId
  **/
  @ApiModelProperty(value = "The unique ID of this request.")
  public String getRequestId() {
    return requestId;
  }

   /**
   * The unique ID of the span.
   * @return spanId
  **/
  @ApiModelProperty(value = "The unique ID of the span.")
  public String getSpanId() {
    return spanId;
  }

   /**
   * The unique ID of the trace.
   * @return traceId
  **/
  @ApiModelProperty(value = "The unique ID of the trace.")
  public String getTraceId() {
    return traceId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SingleResponseV1ServiceRoleV1 singleResponseV1ServiceRoleV1 = (SingleResponseV1ServiceRoleV1) o;
    return Objects.equals(this.content, singleResponseV1ServiceRoleV1.content) &&
        Objects.equals(this.error, singleResponseV1ServiceRoleV1.error) &&
        Objects.equals(this.requestId, singleResponseV1ServiceRoleV1.requestId) &&
        Objects.equals(this.spanId, singleResponseV1ServiceRoleV1.spanId) &&
        Objects.equals(this.traceId, singleResponseV1ServiceRoleV1.traceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, error, requestId, spanId, traceId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SingleResponseV1ServiceRoleV1 {\n");
    
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

