<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 8/18/21
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <c:set var="contextRoot" value="${pageContext.request.contextPath}"/>
    <title><tiles:insertAttribute name="title"/>
    </title>

    <!-- Bootstrap -->
    <link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextRoot}/css/main.css" rel="stylesheet">

    <!-- This is only for javascript tagging in the profile -->
    <link href="${contextRoot}/css/jquery.tagit.css" rel="stylesheet">
    <script src="${contextRoot}/js/jquery-ui.min.js"></script>
    <script src="${contextRoot}/js/tag-it.min.js"></script>

    <script src="/webjars/sockjs-client/sockjs.min.js"></script>
    <script src="/webjars/stomp-websocket/stomp.min.js"></script>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <tiles:insertAttribute name="chatnotifications"></tiles:insertAttribute>
    <tiles:insertAttribute name="chatviewscript" ignore="true"></tiles:insertAttribute>
</head>
<body>

<!-- Static navbar -->
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                    data-toggle="collapse" data-target="#navbar" aria-expanded="false"
                    aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span
                    class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextRoot}/">SocialNetwork</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="${contextRoot}/">Home</a></li>
                <li><a href="${contextRoot}/about">About</a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">

                <sec:authorize access="!isAuthenticated()">
                    <li><a href="${contextRoot}/login">Log In</a></li>
                    <li><a href="${contextRoot}/register">Register</a></li>

                </sec:authorize>

                <sec:authorize access="isAuthenticated()">

                    <li><a href="${contextRoot}/profile">Profile</a></li>
                    <li><a href="javascript:$('#logoutForm').submit();">Log Out</a></li>

                </sec:authorize>

                <sec:authorize access="hasRole('ADMIN')">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">Status <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="${contextRoot}/addStatus">Add Status</a></li>
                            <li><a href="${contextRoot}/viewStatus">View Status Updates</a></li>
                        </ul>
                    </li>
                </sec:authorize>

            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<c:url var="logoutLink" value="/logout"/>
<form id="logoutForm" method="post" action="${logoutLink}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


<div class="container">
    <tiles:insertAttribute name="content"/>
</div>



<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${contextRoot}/js/bootstrap.min.js"></script>


</body>
</html>

