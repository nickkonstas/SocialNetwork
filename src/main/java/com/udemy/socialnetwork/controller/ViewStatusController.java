package com.udemy.socialnetwork.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewStatusController {
    @GetMapping(value = "/viewStatus")
    ModelAndView viewStatusView(ModelAndView modelAndView) {
        modelAndView.setViewName("app.viewStatus");
        return modelAndView;
    }
}
