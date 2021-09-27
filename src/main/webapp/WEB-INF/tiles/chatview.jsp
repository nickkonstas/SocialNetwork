<%--
  Created by IntelliJ IDEA.
  User: nikos
  Date: 9/18/21
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-md-12">

        chatting with: ${chatWithUserName}
        <br>
        with id : ${chatWithUserID}
        <div class="panel panel-default">

            <div class="panel panel-heading">
                <div class="panel-title">Chatting with something</div>
            </div>

            <div class="panel-body">

                <div id="chat-message-view">

                    <div id="chat-message-previous">
                        <a href="#">View older messages</a>
                    </div>


                    <div id="chat-message-record"></div>


                    <div id="chat-message-send" class="input-group input-group-lg">

                        <textarea class="form-control" id="chat-message-text" placeholder="Enter message"></textarea>

                        <span class="input-group-btn">
							<button id="chat-send-button" class="btn btn-primary" type="button">Send</button>

						</span>
                    </div>
                </div>

            </div>


        </div>

    </div>

</div>
