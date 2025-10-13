package com.huntercodexs.common.util.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class CommonPaginationLinkModel {

    private String href;

    private String rel;

    public CommonPaginationLinkModel() {
        super();
    }

    public CommonPaginationLinkModel(String href, String rel) {
        this.href = href;
        this.rel = rel;
    }

    public CommonPaginationLinkModel href(String href) {
        this.href = href;
        return this;
    }

    @NotNull
    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    public CommonPaginationLinkModel rel(String rel) {
        this.rel = rel;
        return this;
    }

    @NotNull
    @JsonProperty("rel")
    public String getRel() {
        return rel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommonPaginationLinkModel commonPaginationLinkModel = (CommonPaginationLinkModel) o;
        return Objects.equals(this.href, commonPaginationLinkModel.href) &&
                Objects.equals(this.rel, commonPaginationLinkModel.rel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(href, rel);
    }

    @Override
    public String toString() {
        return "class PaginationLink {\n" +
                "    href: " + toIndentedString(href) + "\n" +
                "    rel: " + toIndentedString(rel) + "\n" +
                "}";
    }

    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

