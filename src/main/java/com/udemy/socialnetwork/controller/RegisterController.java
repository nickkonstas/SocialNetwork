package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.AppUser;
import com.udemy.socialnetwork.model.VerificationToken;
import com.udemy.socialnetwork.service.AppUserService;
import com.udemy.socialnetwork.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.Date;

@Controller
public class RegisterController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppUserService appUserService;


    @Value("${message.registration.confirmed}")
    private String registrationConfirmedMessage;

    @Value("${message.invalid.user}")
    private String invalidUserMessage;

    @Value("${message.expired.token}")
    private String expiredTokenMessage;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    ModelAndView registerView(ModelAndView modelAndView) throws FileNotFoundException {


        AppUser user = new AppUser();
        modelAndView.getModel().put("user", user);
        modelAndView.setViewName("app.register");
        return modelAndView;
    }

    @RequestMapping(value = "/confirmregister")
    ModelAndView registrationConfirmed(ModelAndView modelAndView, @RequestParam("t") String tokenString) {
        VerificationToken token = appUserService.getVerificationToken(tokenString);

        if(token == null) {
            modelAndView.setViewName("redirect:/invaliduser");
            appUserService.deleteToken(token);
            return modelAndView;
        }

        Date expiryDate = token.getExpiry();
        if (expiryDate.before(new Date())) {
            modelAndView.setViewName("redirect:/expiredtoken");
            appUserService.deleteToken(token);
            return modelAndView;
        }


        AppUser user = token.getUser();
        if (user == null) {
            modelAndView.setViewName("redirect:/invaliduser");
            appUserService.deleteToken(token);
            return modelAndView;
        }
        appUserService.deleteToken(token);
        user.setEnabled(true);
        appUserService.save(user);

        modelAndView.getModel().put("message", registrationConfirmedMessage);
        modelAndView.setViewName("app.message");
        return modelAndView;
    }
    @RequestMapping(value = "/invaliduser")
    ModelAndView invalidUser(ModelAndView modelAndView) {
        modelAndView.getModel().put("message", invalidUserMessage);
        modelAndView.setViewName("app.message");
        return modelAndView;
    }
    @RequestMapping(value = "/expiredtoken")
    ModelAndView expiredToken(ModelAndView modelAndView) {
        modelAndView.getModel().put("message", expiredTokenMessage);
        modelAndView.setViewName("app.message");
        return modelAndView;
    }

    @RequestMapping(value = "/verifyemail")
        String verifyemailView() {
            return "app.verifyemail";
        }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ModelAndView registerPost(ModelAndView modelAndView, @ModelAttribute(value = "user") @Valid AppUser user, BindingResult result) {
        modelAndView.setViewName("app.register");

        if(!result.hasErrors()) {
            appUserService.register(user);

            String token = appUserService.createEmailVerificationToken(user);
            emailService.sendVerificationEmail(user.getEmail(), token);
            modelAndView.setViewName("redirect:/verifyemail");

        }
        return modelAndView;
    }
}
