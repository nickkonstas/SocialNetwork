package com.udemy.socialnetwork.controller;

import com.udemy.socialnetwork.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteStatusController {

    @Autowired
    private StatusUpdateService statusUpdateService;

    @RequestMapping(value = "/deleteStatus", method = RequestMethod.GET)
    ModelAndView deleteStatusView (ModelAndView modelAndView, @RequestParam(name = "id") Long id) {

        statusUpdateService.delete(id);

        modelAndView.setViewName("redirect:/viewStatus");
        return modelAndView;
    }
}
