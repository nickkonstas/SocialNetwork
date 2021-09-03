
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="profilePhoto" value="/profilephoto" />
<c:url var="editProfileAbout" value="/edit-profile-about" />

<div class="row">

    <div class="col-md-10 col-md-offset-1">

        <div class="profile-about">

            <div class="profile-image">
                <img src="${profilePhoto}"/>
            </div>

            <div class="profile-text">
                <c:choose>
                    <c:when test="${profile.about == null}">
                        Click 'edit' and add some information about yourself
                    </c:when>

                    <c:otherwise>
                        ${profile.about}
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

        <div class="profile-about-edit">
            <a href="${editProfileAbout}">Edit Profile</a>
        </div>

        <p>&nbsp;</p>

        <c:url var="uploadPhotoLink" value="/upload-profile-photo"/>
        <form method="post" enctype="multipart/form-data" action="${uploadPhotoLink}">

            Select photo : <input type="file" accept="image/*" name="file"/>
            <input type="submit" value="upload"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </div>

</div>

