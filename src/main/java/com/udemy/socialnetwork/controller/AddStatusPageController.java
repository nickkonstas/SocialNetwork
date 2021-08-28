package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.StatusUpdate;
import com.udemy.socialnetwork.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;

@Controller
public class AddStatusPageController {

    @Autowired
    private StatusUpdateService statusUpdateService;

    @RequestMapping(value = "/addStatus", method=RequestMethod.GET)
    public ModelAndView addStatusView(ModelAndView modelAndView, @ModelAttribute("statusUpdate")StatusUpdate statusUpdate) {
        modelAndView.setViewName("app.addStatus");

        StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();

        modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);


        return modelAndView;
    }

    @RequestMapping(value = "/addStatus", method=RequestMethod.POST)
    public ModelAndView addStatusPost(ModelAndView modelAndView, @Valid @ModelAttribute("statusUpdate") StatusUpdate statusUpdate, BindingResult bindingResult) {
        modelAndView.setViewName("app.addStatus");
        System.out.println(statusUpdate.getAdded());


        if(!bindingResult.hasErrors()) {
            statusUpdateService.save(statusUpdate);
            //Clear the submit form in addStatus.jsp if there aren't any errors in validation
            modelAndView.getModel().put("statusUpdate", new StatusUpdate());

            //Redirect to viewStatus page if everything went ok
            modelAndView.setViewName("redirect:/viewStatus");
        }
        StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
        modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);



        return modelAndView;
    }
}
