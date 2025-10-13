package com.huntercodexs.common.util.builder;

import com.huntercodexs.common.util.model.CommonPaginationLinkModel;
import com.huntercodexs.common.util.model.CommonPaginationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommonPaginationBuilderUtilTests {

    private CommonPaginationBuilderUtil paginationBuilder;

    // Dummy class just for MvcUriComponentsBuilder to generate URLs based on it
    @RestController
    static class DummyController {}

    @BeforeEach
    void setupRequestContext() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("localhost");
        request.setServerPort(8080);
        request.setContextPath("/api");
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        paginationBuilder = new CommonPaginationBuilderUtil(DummyController.class, "/items");
    }

    @Test
    void shouldBuildPaginationWithCorrectValues() {
        int limit = 10;
        int offset = 0;
        long totalElements = 35;
        int totalPages = 4;

        CommonPaginationModel pagination = paginationBuilder.buildPagination(limit, offset, totalElements, totalPages);

        assertNotNull(pagination);
        assertEquals(BigDecimal.valueOf(limit), pagination.getLimit());
        assertEquals(BigDecimal.valueOf(offset), pagination.getOffset());
        assertEquals(BigDecimal.valueOf(totalElements), pagination.getTotalCount());
        assertEquals(BigDecimal.valueOf(totalPages), pagination.getTotalPages());
    }

    @Test
    void shouldGenerateFirstAndLastLinksWhenTotalCountIsPositive() {
        int limit = 5;
        int offset = 0;
        long totalElements = 20;

        CommonPaginationModel pagination = paginationBuilder.buildPagination(limit, offset, totalElements, 4);

        List<CommonPaginationLinkModel> links = pagination.getLinks();
        assertEquals(3, links.size());

        CommonPaginationLinkModel firstLink = links.get(0);
        CommonPaginationLinkModel lastLink = links.get(1);

        assertEquals("First", firstLink.getRel());
        assertTrue(firstLink.getHref().contains("limit=5"));
        assertTrue(firstLink.getHref().contains("offset=0"));

        assertEquals("Last", lastLink.getRel());
        assertTrue(lastLink.getHref().contains("offset=15")); // 20 - 5 = 15
    }

    @Test
    void shouldGeneratePreviousLinkWhenOffsetIsGreaterThanZero() {
        int limit = 10;
        int offset = 20;
        long totalElements = 100;

        CommonPaginationModel pagination = paginationBuilder.buildPagination(limit, offset, totalElements, 10);
        List<CommonPaginationLinkModel> links = pagination.getLinks();

        // First, Last, Previous, Next
        assertEquals(4, links.size());

        CommonPaginationLinkModel previousLink = links.stream()
                .filter(link -> "Previous".equals(link.getRel()))
                .findFirst()
                .orElseThrow();

        assertTrue(previousLink.getHref().contains("offset=10")); // 20 - 10
    }

    @Test
    void shouldGenerateNextLinkWhenNextOffsetIsLessThanLastOffset() {
        int limit = 10;
        int offset = 20;
        long totalElements = 50; // lastOffset = 40

        CommonPaginationModel pagination = paginationBuilder.buildPagination(limit, offset, totalElements, 5);
        List<CommonPaginationLinkModel> links = pagination.getLinks();

        CommonPaginationLinkModel nextLink = links.stream()
                .filter(link -> "Next".equals(link.getRel()))
                .findFirst()
                .orElseThrow();

        assertTrue(nextLink.getHref().contains("offset=30"));
    }

    @Test
    void shouldNotGenerateLinksWhenTotalCountIsZero() {
        int limit = 10;
        int offset = 0;
        long totalElements = 0;

        CommonPaginationModel pagination = paginationBuilder.buildPagination(limit, offset, totalElements, 0);
        List<CommonPaginationLinkModel> links = pagination.getLinks();

        assertTrue(links.isEmpty());
    }

    @Test
    void shouldHandleOffsetGreaterThanTotalElementsGracefully() {
        int limit = 10;
        int offset = 100; // maior que totalElements
        long totalElements = 30; // lastOffset = 20

        CommonPaginationModel pagination = paginationBuilder.buildPagination(limit, offset, totalElements, 3);
        List<CommonPaginationLinkModel> links = pagination.getLinks();

        // Should have at least (first and last)
        assertTrue(links.stream().anyMatch(link -> "First".equals(link.getRel())));
        assertTrue(links.stream().anyMatch(link -> "Last".equals(link.getRel())));
    }
}
