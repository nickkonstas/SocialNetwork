package com.udemy.socialnetwork.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddStatusPageController {
    @GetMapping("/addStatus")
    public String addStatusView() {
        return "app.addStatus";
    }
}
