package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.entity.AppUser;
import com.udemy.socialnetwork.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Autowired
    private AppUserService userService;

    public AppUser getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

//        AppUser user = userService.get(email);
        return userService.get(email);
    }
}
