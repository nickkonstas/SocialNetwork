package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.StatusUpdate;
import com.udemy.socialnetwork.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class AddStatusPageController {

    @Autowired
    private StatusUpdateService statusUpdateService;

    @RequestMapping(value = "/addStatus", method=RequestMethod.GET)
    public ModelAndView addStatusView(ModelAndView modelAndView) {
        modelAndView.setViewName("app.addStatus");

        StatusUpdate statusUpdate = new StatusUpdate();

        StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();

        //ModelAndView is like a map (key-->value pair)
        modelAndView.getModel().put("statusUpdate", statusUpdate);
        modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);


        return modelAndView;
    }

    @RequestMapping(value = "/addStatus", method=RequestMethod.POST)
    public ModelAndView addStatusPost(ModelAndView modelAndView, StatusUpdate statusUpdate) {
        modelAndView.setViewName("app.addStatus");

        statusUpdateService.save(statusUpdate);
        StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
        modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);

        return modelAndView;
    }
}
