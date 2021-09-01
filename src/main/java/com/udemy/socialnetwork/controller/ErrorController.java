package com.udemy.socialnetwork.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @Value("${message.error.forbidden}")
    private String accessDenied;

    @RequestMapping("/403")
    ModelAndView accessDenied(ModelAndView modelAndView) {
        modelAndView.getModel().put("message", accessDenied);
        modelAndView.setViewName("app.message");
        return modelAndView;
    }
}
