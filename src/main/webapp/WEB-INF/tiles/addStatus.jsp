<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 8/18/21
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<fmt:setTimeZone value="GMT+3"/>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="row">

    <div class="col-md-8 col-md-offset-2" >
        Request Status Update attribute: <%= request.getAttribute("statusUpdate")%>
        JSP object: <%=this%>
        JSP class: <%=this.getClass()%>

        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="panel-title">Add Status Update</div>
            </div>

            <div class="panel-body">
                <form:form modelAttribute="statusUpdate">
                    <div class="form-group">
                        <form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>
                    </div>
                    <input type="submit" name="submit" value="Add Status"/>
                </form:form>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="panel-title">Status Update added on: <fmt:formatDate pattern="EEEE d MMMM y 'at' H:mm:s"  value="${latestStatusUpdate.added}"/></div>
            </div>
            <div class="panel-body">
                <c:out value="${latestStatusUpdate.text}"/>
            </div>
        </div>




    </div>




</div>
</body>
</html>
