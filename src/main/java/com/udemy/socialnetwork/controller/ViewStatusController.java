package com.udemy.socialnetwork.controller;


import com.udemy.socialnetwork.model.entity.StatusUpdate;
import com.udemy.socialnetwork.service.StatusUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewStatusController {
    @Autowired
    private StatusUpdateService statusUpdateService;

    @RequestMapping(value = "/viewStatus", method = RequestMethod.GET)
    ModelAndView viewStatusView(ModelAndView modelAndView, @RequestParam(name = "p", defaultValue = "1") int pageNumber) {
        Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);
        modelAndView.getModel().put("page", page);

        modelAndView.setViewName("app.viewStatus");
        return modelAndView;
    }
}
