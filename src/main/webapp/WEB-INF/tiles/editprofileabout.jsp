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


<div class="row">

    <div class="col-md-8 col-md-offset-2">

        <div class="errors">
            <form:errors path="profile.*"/>
        </div>

        <div class="panel panel-default">

            <div class="panel-heading">
                <div class="panel-title">Edit Your 'About' text</div>
            </div>
            <form:form modelAttribute="profile">


                <div class="form-group">
                    <form:textarea path="about" name="about" rows="10" cols="50"></form:textarea>

                </div>
                <input type="submit" name="submit" value="Save"/>
            </form:form>
        </div>

    </div>
</div>

<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
<script>
    tinymce.init({
        selector: "textarea",
        //plugins: "link"
    })
</script>
