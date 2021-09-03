package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.exception.ImageTooSmallException;
import com.udemy.socialnetwork.exception.InvalidImageFileException;
import com.udemy.socialnetwork.model.AppUser;
import com.udemy.socialnetwork.model.FileInfo;
import com.udemy.socialnetwork.model.Profile;
import com.udemy.socialnetwork.service.AppUserService;
import com.udemy.socialnetwork.service.FileService;
import com.udemy.socialnetwork.service.ProfileService;
import org.owasp.html.PolicyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class ProfileController {

    @Autowired
    private PolicyFactory htmlPolicy;

    @Autowired
    private AppUserService userService;

    @Autowired
    private ProfileService profileService;


    @Value("${photo.upload.ok}")
    private String photoStatusOK;

    @Value("${photo.upload.invalid}")
    private String photoStatusInvalid;

    @Value("${ photo.upload.ioexception}")
    private String photoStatusIOException;

    @Value("${photo.upload.toosmall}")
    private String photoStatusTooSmall;

    @Value("${photo.upload.directory}")
    private String photoUploadDirectory;

    @Value("${profile.image.width}")
    private int profileImgWidth;

    @Value("${profile.image.height}")
    private int profileImgHeight;

    @Autowired
    private FileService fileService;

    private AppUser getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return userService.get(email);
    }

    @RequestMapping(value = "/profile")
    public ModelAndView profileView(ModelAndView modelAndView) {

        AppUser user = getUser();
        Profile profile = profileService.getUserProfile(user);

        if(profile == null) {
            profile = new Profile();
            profile.setUser(user);
            profileService.save(profile);
        }

        //For security reasons, not to display sensitive information about the profile of a user in the jsp page
        Profile webProfile = new Profile();
        webProfile.safeCopyFrom(profile);

        modelAndView.getModel().put("profile", webProfile);
        modelAndView.setViewName("app.profile");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-profile-about", method = RequestMethod.GET)
    public ModelAndView editProfileView(ModelAndView modelAndView) {
        AppUser user = getUser();
        Profile profile = profileService.getUserProfile(user);

        Profile webProfile = new Profile();
        webProfile.safeCopyFrom(profile);

        modelAndView.getModel().put("profile", webProfile);
        modelAndView.setViewName("app.editProfileAbout");
        return modelAndView;
    }

    @RequestMapping(value = "/edit-profile-about", method = RequestMethod.POST)
    public ModelAndView editProfilePost(ModelAndView modelAndView, @Valid Profile webProfile, BindingResult result) {
        modelAndView.setViewName("app.editProfileAbout");

        AppUser user = getUser();
        Profile profile = profileService.getUserProfile(user);
        profile.safeMergeFrom(webProfile, htmlPolicy);

        if (!result.hasErrors()) {
            profileService.save(profile);
            modelAndView.setViewName("redirect:/profile");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/upload-profile-photo" , method = RequestMethod.POST)
    public ModelAndView handlePhotoUploads(ModelAndView modelAndView, @RequestParam("file") MultipartFile file)  {

        modelAndView.setViewName("redirect:/profile");

        AppUser user = getUser();
        Profile profile = profileService.getUserProfile(user);

        Path oldPhotoPath = profile.getPhotoPath(photoUploadDirectory);

        try {
            FileInfo photoInfo = fileService.saveImageFile(file, photoUploadDirectory, "photos", "profile" + user.getId(), profileImgWidth, profileImgHeight); //maybe there are security flaws in user.getId() but secures name uniqueness of the filename
            profile.setPhotoDetails(photoInfo);
            profileService.save(profile);

            if(oldPhotoPath != null) {
                Files.delete(oldPhotoPath);
            }

        } catch (InvalidImageFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ImageTooSmallException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/profilephoto", method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<InputStreamResource> servePhoto() throws IOException {
        AppUser user = getUser();
        Profile profile = profileService.getUserProfile(user);
        Path photoPath = Paths.get(photoUploadDirectory,"default", "avatar.png");

        if(profile != null && profile.getPhotoPath(photoUploadDirectory) != null) {
            photoPath = profile.getPhotoPath(photoUploadDirectory);
        }

        return ResponseEntity
                .ok()
                .contentLength(Files.size(photoPath))
                .contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
                .body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
    }
}
