<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 9/18/21
  Time: 4:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="outboundDestination" value="/app/message/send/${chatWithUserID}" />

<script>


   // connectionManager.addSubscription();

    function sendMessage() {
        var text = document.getElementById("chat-message-text").value;

        var message = {
            'text' : text
        };

        //alert(JSON.stringify(message));

        StompClient.send("${outboundDestination}",headers, JSON.stringify(message));

        $("#chat-message-text").val("");
        $("#chat-message-text").focus();
    }

    $(document).ready(function() {

        alert("${outboundDestination}")
        $(document).keyup(function(e) {
            if(e.which == 13) {
                // Enter key pressed
                sendMessage();

                return false;
            }
        });

        $('#chat-send-button').click(function(){
            sendMessage();
        });
    });
</script>
