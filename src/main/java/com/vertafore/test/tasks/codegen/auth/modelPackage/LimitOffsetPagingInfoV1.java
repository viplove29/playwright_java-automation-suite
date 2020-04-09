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
 * LimitOffsetPagingInfoV1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-04-09T11:16:26.670-06:00")

public class LimitOffsetPagingInfoV1   {
  @JsonProperty("method")
  private String method = null;

  @JsonProperty("numberOfElements")
  private Integer numberOfElements = null;

  @JsonProperty("page")
  private Integer page = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("totalElements")
  private Integer totalElements = null;

  @JsonProperty("totalPages")
  private Integer totalPages = null;

  public LimitOffsetPagingInfoV1 method(String method) {
    this.method = method;
    return this;
  }

  /**
   * The method of paging that this pagingInfo contains
   * @return method
  **/
  @ApiModelProperty(readOnly = true, value = "The method of paging that this pagingInfo contains")


  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public LimitOffsetPagingInfoV1 numberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
    return this;
  }

  /**
   * The number of data elements in this response.
   * @return numberOfElements
  **/
  @ApiModelProperty(readOnly = true, value = "The number of data elements in this response.")


  public Integer getNumberOfElements() {
    return numberOfElements;
  }

  public void setNumberOfElements(Integer numberOfElements) {
    this.numberOfElements = numberOfElements;
  }

  public LimitOffsetPagingInfoV1 page(Integer page) {
    this.page = page;
    return this;
  }

  /**
   * The page number of the response.
   * @return page
  **/
  @ApiModelProperty(readOnly = true, value = "The page number of the response.")


  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public LimitOffsetPagingInfoV1 pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * The page size of this response.
   * @return pageSize
  **/
  @ApiModelProperty(readOnly = true, value = "The page size of this response.")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public LimitOffsetPagingInfoV1 totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * The total number of elements
   * @return totalElements
  **/
  @ApiModelProperty(readOnly = true, value = "The total number of elements")


  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public LimitOffsetPagingInfoV1 totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * The total number of pages, based on the pageSize and totalElements
   * @return totalPages
  **/
  @ApiModelProperty(readOnly = true, value = "The total number of pages, based on the pageSize and totalElements")


  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LimitOffsetPagingInfoV1 limitOffsetPagingInfoV1 = (LimitOffsetPagingInfoV1) o;
    return Objects.equals(this.method, limitOffsetPagingInfoV1.method) &&
        Objects.equals(this.numberOfElements, limitOffsetPagingInfoV1.numberOfElements) &&
        Objects.equals(this.page, limitOffsetPagingInfoV1.page) &&
        Objects.equals(this.pageSize, limitOffsetPagingInfoV1.pageSize) &&
        Objects.equals(this.totalElements, limitOffsetPagingInfoV1.totalElements) &&
        Objects.equals(this.totalPages, limitOffsetPagingInfoV1.totalPages);
  }

  @Override
  public int hashCode() {
    return Objects.hash(method, numberOfElements, page, pageSize, totalElements, totalPages);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LimitOffsetPagingInfoV1 {\n");
    
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    numberOfElements: ").append(toIndentedString(numberOfElements)).append("\n");
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
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

