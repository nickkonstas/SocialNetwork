package com.udemy.socialnetwork.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPageController {
    @GetMapping("/about")
    public String aboutView() {
        return "app.about";
    }
}
