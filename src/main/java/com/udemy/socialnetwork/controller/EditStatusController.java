package com.udemy.socialnetwork.controller;

import com.udemy.socialnetwork.model.entity.StatusUpdate;
import com.udemy.socialnetwork.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EditStatusController {

    @Autowired
    StatusUpdateService statusUpdateService;

    @RequestMapping(value = "/editStatus", method = RequestMethod.GET)
    ModelAndView editStatusView(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {
        Optional<StatusUpdate> statusUpdate = statusUpdateService.get(id);

        modelAndView.getModel().put("statusUpdate", statusUpdate);
        modelAndView.setViewName("app.editStatus");
        return modelAndView;
    }

    @RequestMapping(value = "/editStatus", method = RequestMethod.POST)
    ModelAndView editStatusPost(ModelAndView modelAndView, @Valid @ModelAttribute("statusUpdate") StatusUpdate statusUpdate, BindingResult result) {

        modelAndView.setViewName("app.editStatus");
        System.out.println(statusUpdate.getAdded());

        if(!result.hasErrors()) {
            statusUpdateService.save(statusUpdate);
            modelAndView.setViewName("redirect:/viewStatus");
        }

        return modelAndView;
    }


}
