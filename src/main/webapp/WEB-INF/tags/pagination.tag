<%@ tag language="java" pageEncoding="utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<%-----------ATTRIBUTES--------------%>

<%--Spring Page object --%>
<%@attribute name="page" required="true" type="org.springframework.data.domain.Page" %>

<%--Base url of Page--%>
<%@attribute name="url" required="true" %>

<%-- Debug purposes --%>
Base url contains questionmark : ${fn:contains(url,'?')}

<%--Number of page numbers to display at once--%>
<%@attribute name="size" required="false" %>


<%-----------ATTRIBUTES-CALCULATION--------------%>

<c:set var="block" value="${empty param.b ? 0 : param.b}" />
<c:set var="size" value="${empty size ? 10 : size}"/>



<c:set var="startPage" value="${block * size + 1}"/>
<c:set var="endPage" value="${(block + 1) * size}"/>
<c:set var="endPage" value="${endPage > page.totalPages ? page.totalPages : endPage}"/>
<c:set var="paramListSeparator" value="${fn:contains(url,'?') ? '&' : '?'}" />

<%-- Debub Purposes
<p> Block value = ${block} </p>
<p>Size value = ${size}</p>
<p>Start Page = ${startPage}</p>
<p>End Page = ${endPage}</p>
<p>Total Pages = ${page.totalPages}</p>
--%>


<%-----------OUTPUT--------------%>

<c:if test="${page.totalPages != 1}">
    <div class="pagination">
        <c:if test="${block != 0}">
            <a href="${url}${paramListSeparator}b=${block - 1}&p=${ (block - 1) * size + 1}"> &lt;&lt; </a>
        </c:if>
        <c:forEach var="pageNumber" begin="${startPage}" end="${endPage}">

            <c:choose>

                <c:when test="${page.number != pageNumber - 1}">
                    <a href="${url}${paramListSeparator}p=${pageNumber} &b=${block}"> <c:out value="${pageNumber}"/> </a>
                </c:when>

                <c:otherwise>
                    <strong><c:out value="${pageNumber}"/> </strong>
                </c:otherwise>

            </c:choose>
            <c:if test="${pageNumber != endPage}">
                |
            </c:if>
        </c:forEach>

        <c:if test="${endPage != page.totalPages}">
            <a href="${url}${paramListSeparator}b=${block + 1}&p=${(block + 1) * size + 1}">&gt;&gt;</a>
        </c:if>
    </div>
</c:if>