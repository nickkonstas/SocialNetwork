<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 9/18/21
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


<sec:authorize access="isAuthenticated()">

  <c:url var="webSocketEndpoint" value="/chat" scope="request" />
  <c:url var="inboundDestination" value="/user/queue/${thisUserID}" />

  <script>

    //var connectionManager = new ConnectionManager();

    var csrfTokenName = $("meta[name='_csrf_header']").attr("content");
    var csrfTokenValue = $("meta[name='_csrf']").attr("content");

    console.log("CSRF name", csrfTokenName);
    console.log("CSRF value", csrfTokenValue);

    var headers = [];
    headers[csrfTokenName] = csrfTokenValue;

    var wsocket = new SockJS("${webSocketEndpoint}");
    var StompClient = Stomp.over(wsocket);

    StompClient.connect({headers}, function() {
      console.log("Connection established");
      });

      <%--client.subscribe("${inboundDestination}", function(messageJson) {--%>
      <%--  var message = JSON.parse(messageJson.body);--%>

      <%--  alert(message.text);--%>
      <%--});--%>


  </script>

</sec:authorize>