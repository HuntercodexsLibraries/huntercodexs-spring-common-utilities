package com.huntercodexs.common.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class CommonPaginationModel {

    @Nullable
    private BigDecimal offset;

    @Nullable
    private BigDecimal limit;

    @Nullable
    private BigDecimal totalCount;

    @Nullable
    private BigDecimal totalPages;

    @Setter
    @Valid
    private List<@Valid CommonPaginationLinkModel> links = new ArrayList<>();

    public CommonPaginationModel offset(@Nullable BigDecimal offset) {
        this.offset = offset;
        return this;
    }

    @Valid
    @JsonProperty("offset")
    public @Nullable BigDecimal getOffset() {
        return offset;
    }

    public void setOffset(@Nullable BigDecimal offset) {
        this.offset = offset;
    }

    public CommonPaginationModel limit(@Nullable BigDecimal limit) {
        this.limit = limit;
        return this;
    }

    @Valid
    @JsonProperty("limit")
    public @Nullable BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(@Nullable BigDecimal limit) {
        this.limit = limit;
    }

    public CommonPaginationModel totalCount(@Nullable BigDecimal totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    @Valid
    @JsonProperty("totalCount")
    public @Nullable BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(@Nullable BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public CommonPaginationModel totalPages(@Nullable BigDecimal totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    @Valid
    @JsonProperty("totalPages")
    public @Nullable BigDecimal getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(@Nullable BigDecimal totalPages) {
        this.totalPages = totalPages;
    }

    public CommonPaginationModel links(List<@Valid CommonPaginationLinkModel> links) {
        this.links = links;
        return this;
    }

    public CommonPaginationModel addLinksItem(CommonPaginationLinkModel linksItem) {
        if (this.links == null) {
            this.links = new ArrayList<>();
        }
        this.links.add(linksItem);
        return this;
    }

    @Valid
    @JsonProperty("links")
    public List<@Valid CommonPaginationLinkModel> getLinks() {
        return links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommonPaginationModel commonPaginationModel = (CommonPaginationModel) o;
        return Objects.equals(this.offset, commonPaginationModel.offset) &&
                Objects.equals(this.limit, commonPaginationModel.limit) &&
                Objects.equals(this.totalCount, commonPaginationModel.totalCount) &&
                Objects.equals(this.totalPages, commonPaginationModel.totalPages) &&
                Objects.equals(this.links, commonPaginationModel.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offset, limit, totalCount, totalPages, links);
    }

    @Override
    public String toString() {
        return "class Pagination {\n" +
                "    offset: " + toIndentedString(offset) + "\n" +
                "    limit: " + toIndentedString(limit) + "\n" +
                "    totalCount: " + toIndentedString(totalCount) + "\n" +
                "    totalPages: " + toIndentedString(totalPages) + "\n" +
                "    links: " + toIndentedString(links) + "\n" +
                "}";
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
