<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="profilePhoto" value="/profilephoto/${userId}"/>
<c:url var="editProfileAbout" value="/edit-profile-about"/>
<c:url var="saveInterest" value="/save-interest"/>
<c:url var="deleteInterest" value="/delete-interest"/>


<div class="row">

    <div class="col-md-10 col-md-offset-1">

        <div id="profile-photo-status"></div>

        <div id="interestDiv">
            <ul id="interestList">
                <c:choose>
                    <c:when test="${empty profile.interests}">
                        <li>Add your interests here(example: music)</li>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="interestName" items="${profile.interests}">
                           <li>${interestName}</li>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>

        <div class="profile-about">

            <div class="profile-image">
                <div>
                    <img id="profilePhotoImage" src="${profilePhoto}" width="100"/>
                </div>

                <div class="text-center">
                    <a href="#" id="uploadLink">Upload Photo</a>
                </div>
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

        <c:url var="uploadPhotoLink" value="/upload-profile-photo"/>
        <form method="post" enctype="multipart/form-data" action="${uploadPhotoLink}" id="photoUploadForm">

            <input type="file" id="photoFileInput" accept="image/*" name="file"/>
            <input type="submit" value="upload"/>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </div>
</div>

<script>

    function setUploadStatusText(text) {
        $("#profile-photo-status").text(text);

        window.setTimeout(function () {
            $("#profile-photo-status").text("");
        }, 2000);
    }

    function uploadSuccess(data) {
        $("#profilePhotoImage").attr("src", "${profilePhoto}");

        $("#photoFileInput").val("");

        setUploadStatusText(data.message);

    }

    function uploadPhoto(event) {
        $.ajax({
            url: $(this).attr("action"),
            type: 'POST',
            data: new FormData(this),
            processData: false,
            contentType: false,
            success: uploadSuccess,
            error: function () {
                setUploadStatusText("Server unreachable");
            }
        });

        event.preventDefault();
    }

    function saveInterest(text) {
        editInterest(text, "${saveInterest}");
    }

    function deleteInterest(text) {
        editInterest(text, "${deleteInterest}");
    }

    function editInterest(text, actionUrl) {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
            jqXHR.setRequestHeader(header, token);
        });

        $.ajax({
            'url': actionUrl,

            'data': {
                name: text
            },

            type: 'POST',
            success: function () {
                // alert("Ok");
            },

            error: function () {
                //alert("Error")
            }
        });

    }

    $(document).ready(function () {

        $("#interestList").tagit({

            afterTagRemoved: function (event, ui) {
                deleteInterest(ui.tagLabel);
            },

            afterTagAdded: function (event, ui) {
                if (ui.duringInitialization != true) {
                    saveInterest(ui.tagLabel);
                }
            },
            caseSensitive: false,
            allowSpaces: true,
            tagLimit: 10,

        });

        $("#uploadLink").click(function (event) {
            event.preventDefault();
            $("#photoFileInput").trigger('click');
        })

        $("#photoFileInput").change(function () {
            $("#photoUploadForm").submit();
        })

        $("#photoUploadForm").on("submit", uploadPhoto);
    });
</script>

