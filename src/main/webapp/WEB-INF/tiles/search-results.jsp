<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 9/10/21
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="nick" %>


<div class="row">
    <div class="col-md-12 results-noresult">
        <c:if test="${empty page.content}">
            <strong>No results</strong>
        </c:if>
    </div>
</div>

<c:url var="searchUrl" value="/search?s=${s}"/>

<div class="row">
    <div class="col-md-12">
        <nick:pagination page="${page}" url="${searchUrl}" size="4"/>
    </div>
</div>

<c:forEach var="result" items="${page.content}">
    <c:url var="profilePhoto" value="/profilephoto/${result.userId}"/>
    <c:url var="profileLink" value="/profile/${result.userId}"/>

    <div class="row person-row">
        <div class="col-md-12">

            <div class="results-photo">
                <a href="${profileLink}"> <img id="profilePhotoImage" src="${profilePhoto}" width="100"/> </a>
            </div>

            <div class="results-details">

                <div class="results-name">
                    <a href="${profileLink}">
                        <c:out value="${result.firstName}"/>
                        <c:out value="${result.lastName}"/>
                    </a>
                </div>

                <div class="results-interests">
                    <c:forEach var="interest" items="${result.interests}" varStatus="status">
                        <c:url var="interestLink" value="/search?s=${interest.name}"/>
                        <a href="${interestLink}"> <c:out value="${interest.name}"/> </a>
                        <c:if test="${!status.last}"> | </c:if>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</c:forEach>