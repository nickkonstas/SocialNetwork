package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.AppUser;
import com.udemy.socialnetwork.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private AppUserService appUserService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    ModelAndView registerView(ModelAndView modelAndView) {

        AppUser user = new AppUser();
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("app.register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ModelAndView registerPost(ModelAndView modelAndView, @ModelAttribute(value = "user") @Valid AppUser user, BindingResult result) {
        modelAndView.setViewName("app.register");

        if(!result.hasErrors()) {
            appUserService.register(user);
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }
}
