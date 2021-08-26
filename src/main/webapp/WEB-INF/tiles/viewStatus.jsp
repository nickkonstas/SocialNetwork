<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 8/25/21
  Time: 7:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib tagdir="/WEB-INF/tags" prefix="nick" %>

<c:url var="url" value="/viewStatus"/>

<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="row">

    <div class="col-md-8 col-md-offset-2">
        
        <nick:pagination page="${page}" url="${url}" size="4"/>

        <c:forEach var="statusUpdate" items="${page.content}">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">Status Update added on: <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"
                                                                                     value="${statusUpdate.added}"/></div>
                </div>
                <div class="panel-body">
                    <c:out value="${statusUpdate.text}"/>
                </div>
            </div>

        </c:forEach>
    </div>
</div>

</body>
</html>
