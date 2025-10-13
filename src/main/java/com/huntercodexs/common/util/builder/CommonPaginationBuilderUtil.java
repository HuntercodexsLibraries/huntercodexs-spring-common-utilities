package com.huntercodexs.common.util.builder;

import com.huntercodexs.common.util.model.CommonPaginationModel;
import com.huntercodexs.common.util.model.CommonPaginationLinkModel;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CommonPaginationBuilderUtil {
    private final Class<?> classObject;
    private final String path;

    public CommonPaginationBuilderUtil(Class<?> classObj, String path) {
        this.classObject = classObj;
        this.path = path;
    }

    public CommonPaginationModel buildPagination(Integer limit, Integer offset, long totalElements, int totalPages) {
        return new CommonPaginationModel()
                .limit(new BigDecimal(limit))
                .offset(new BigDecimal(offset))
                .totalCount(new BigDecimal(totalElements))
                .totalPages(new BigDecimal(totalPages))
                .links(getPaginationLinks(limit, offset, (int) totalElements));
    }

    private List<CommonPaginationLinkModel> getPaginationLinks(Integer limit, Integer offset, int totalCount) {

        int lastOffset = Math.max((totalCount - limit), 0);
        int previousOffset = offset - limit;
        int nextOffset = offset + limit;

        List<CommonPaginationLinkModel> links = new ArrayList<>();

        if (totalCount > 0) {
            links.add(new CommonPaginationLinkModel(getUriBuilder(limit, 0), "First"));
            links.add(new CommonPaginationLinkModel(getUriBuilder(limit, lastOffset), "Last"));
        }

        if (offset > 0)
            links.add(new CommonPaginationLinkModel(getUriBuilder(limit, previousOffset), "Previous"));

        if (nextOffset < lastOffset)
            links.add(new CommonPaginationLinkModel(getUriBuilder(limit, offset + limit), "Next"));

        return links;
    }

    private String getUriBuilder(Integer limit, Integer previousOffset) {

        var uriComponentsBuilder = MvcUriComponentsBuilder
                .fromController(classObject)
                .path(path)
                .queryParam("limit", limit)
                .queryParam("offset", previousOffset);

        return uriComponentsBuilder.toUriString();
    }
}