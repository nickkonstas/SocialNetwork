package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.StatusUpdate;
import com.udemy.socialnetwork.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomePageController {

    @Autowired
    private StatusUpdateService statusUpdateService;

    @GetMapping("/")
    public ModelAndView homeView(ModelAndView modelAndView) {
        StatusUpdate statusUpdate = statusUpdateService.getLatest();
        modelAndView.getModel().put("statusUpdate", statusUpdate);
        modelAndView.setViewName("app.home");
        return modelAndView;
    }
}
