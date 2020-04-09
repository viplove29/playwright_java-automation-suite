package modelPackage;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import modelPackage.ErrorResponseV1;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SingleResponseV1Mapstringboolean
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class SingleResponseV1Mapstringboolean   {
  @JsonProperty("content")
  @Valid
  private java.util.Map<String, Boolean> content = null;

  @JsonProperty("error")
  private ErrorResponseV1 error = null;

  @JsonProperty("requestId")
  private String requestId = null;

  @JsonProperty("spanId")
  private String spanId = null;

  @JsonProperty("traceId")
  private String traceId = null;

  public SingleResponseV1Mapstringboolean content(java.util.Map<String, Boolean> content) {
    this.content = content;
    return this;
  }

  public SingleResponseV1Mapstringboolean putContentItem(String key, Boolean contentItem) {
    if (this.content == null) {
      this.content = new java.util.HashMap<String, Boolean>();
    }
    this.content.put(key, contentItem);
    return this;
  }

  /**
   * The response data element
   * @return content
  **/
  @ApiModelProperty(readOnly = true, value = "The response data element")


  public java.util.Map<String, Boolean> getContent() {
    return content;
  }

  public void setContent(java.util.Map<String, Boolean> content) {
    this.content = content;
  }

  public SingleResponseV1Mapstringboolean error(ErrorResponseV1 error) {
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

  public SingleResponseV1Mapstringboolean requestId(String requestId) {
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

  public SingleResponseV1Mapstringboolean spanId(String spanId) {
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

  public SingleResponseV1Mapstringboolean traceId(String traceId) {
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
    SingleResponseV1Mapstringboolean singleResponseV1Mapstringboolean = (SingleResponseV1Mapstringboolean) o;
    return Objects.equals(this.content, singleResponseV1Mapstringboolean.content) &&
        Objects.equals(this.error, singleResponseV1Mapstringboolean.error) &&
        Objects.equals(this.requestId, singleResponseV1Mapstringboolean.requestId) &&
        Objects.equals(this.spanId, singleResponseV1Mapstringboolean.spanId) &&
        Objects.equals(this.traceId, singleResponseV1Mapstringboolean.traceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, error, requestId, spanId, traceId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SingleResponseV1Mapstringboolean {\n");
    
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

