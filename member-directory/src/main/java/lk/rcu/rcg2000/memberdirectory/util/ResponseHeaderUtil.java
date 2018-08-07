package lk.rcu.rcg2000.memberdirectory.util;

import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseHeaderUtil {

    public static void addPagingHeadersToResponse(final HttpServletRequest request, final HttpServletResponse response,
            final Page page, final UriComponentsBuilder uriComponentsBuilder) {
        addMetadataHeaders(response, page);
        addLinkHeader(request, response, page, uriComponentsBuilder);
    }

    private static void addMetadataHeaders(final HttpServletResponse response, final Page page) {
        response.addHeader("Paging-First", String.valueOf(page.isFirst()));
        response.addHeader("Paging-Last", String.valueOf(page.isLast()));
        response.addHeader("Paging-Total-Pages", String.valueOf(page.getTotalPages()));
        response.addHeader("Paging-Total-Elements", String.valueOf(page.getTotalElements()));
        response.addHeader("Paging-Number-Of-Elements", String.valueOf(page.getNumberOfElements()));
        response.addHeader("Paging-Sort", String.valueOf(page.getSort()));
        response.addHeader("Paging-Size", String.valueOf(page.getSize()));
    }

    private static void addLinkHeader(final HttpServletRequest request, final HttpServletResponse response,
            final Page page, final UriComponentsBuilder uriBuilder) {
        uriBuilder.path(request.getRequestURI());
        final StringBuilder linkHeader = new StringBuilder();
        final String sortParam = request.getParameter("sort");
        final int maxPageNo = getMaxPageNo(page);

        if (hasNextPage(page.getNumber(), maxPageNo)) {
            final String uriForNextPage = constructNextPageUri(uriBuilder, page.getNumber(), page.getSize(), sortParam);
            linkHeader.append(createLinkHeader(uriForNextPage, "next"));
        }
        if (hasPreviousPage(page.getNumber())) {
            final String uriForPrevPage = constructPrevPageUri(uriBuilder, page.getNumber(), page.getSize(), sortParam);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForPrevPage, "prev"));
        }
        if (hasFirstPage(page.getNumber())) {
            final String uriForFirstPage = constructFirstPageUri(uriBuilder, page.getSize(), sortParam);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForFirstPage, "first"));
        }
        if (hasLastPage(page.getNumber(), maxPageNo)) {
            final String uriForLastPage = constructLastPageUri(uriBuilder, maxPageNo, page.getSize(), sortParam);
            appendCommaIfNecessary(linkHeader);
            linkHeader.append(createLinkHeader(uriForLastPage, "last"));
        }
        response.addHeader("Link", linkHeader.toString());
    }

    private static int getMaxPageNo(final Page page) {
        if (page.getSize() == 0) {
            return page.getTotalPages();
        } else {
            return page.getTotalPages() - 1;
        }
    }

    private static boolean hasNextPage(final int page, final int totalPages) {
        return page < totalPages;
    }

    private static boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    private static boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    private static boolean hasLastPage(final int page, final int totalPages) {
        return totalPages > 0 && hasNextPage(page, totalPages);
    }

    private static String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size,
            final String sort) {
        return uriBuilder.replaceQueryParam("page", page + 1)
                .replaceQueryParam("size", size)
                .replaceQueryParam("sort", sort)
                .build().encode().toUriString();
    }

    private static String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size,
            final String sort) {
        return uriBuilder.replaceQueryParam("page", page - 1)
                .replaceQueryParam("size", size)
                .replaceQueryParam("sort", sort)
                .build().encode().toUriString();
    }

    private static String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size,
            final String sort) {
        return uriBuilder.replaceQueryParam("page", 0)
                .replaceQueryParam("size", size)
                .replaceQueryParam("sort", sort)
                .build().encode().toUriString();
    }

    private static String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages,
            final int size, final String sort) {
        return uriBuilder.replaceQueryParam("page", totalPages)
                .replaceQueryParam("size", size)
                .replaceQueryParam("sort", sort)
                .build().encode().toUriString();
    }

    private static void appendCommaIfNecessary(final StringBuilder linkHeader) {
        if (linkHeader.length() > 0) {
            linkHeader.append(", ");
        }
    }

    private static String createLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }
}
