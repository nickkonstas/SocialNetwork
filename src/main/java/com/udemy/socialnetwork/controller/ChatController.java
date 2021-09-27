package com.udemy.socialnetwork.controller;

import com.udemy.socialnetwork.model.dto.SimpleMessage;
import com.udemy.socialnetwork.model.entity.AppUser;
import com.udemy.socialnetwork.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@CrossOrigin(origins = "http://localhost:8081")

public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpleMessagingTemplate;

    @Autowired
    private Util util;

    @Autowired
    private AppUserService userService;

    @RequestMapping("/chatview/{chatWithUserID}")
    ModelAndView chatView(ModelAndView modelAndView, @PathVariable Long chatWithUserID) {

        AppUser thisUser = util.getUser();
        String chatWithUserName = userService.getUserName(chatWithUserID);

        modelAndView.getModel().put("thisUserID", thisUser.getId());
        modelAndView.getModel().put("chatWithUserID", chatWithUserID);
        modelAndView.getModel().put("chatWithUserName", chatWithUserName);

        modelAndView.setViewName("chat.chatview");
        return modelAndView;
    }


//    @MessageMapping("/message/send/{toUserID}")
//    //@SendTo("/user/topic/public")
//    public void send(@org.jetbrains.annotations.NotNull Principal principal, Message<SimpleMessage> message, @DestinationVariable Long toUserID) {
//
//        SimpleMessage m = message.getPayload();
//        System.out.println(m);
//        System.out.println("reached");
//
//        String fromUsername = principal.getName();
//        AppUser fromUser = userService.get(fromUsername);
//        Long fromUserId = fromUser.getId();
//
//        String returnReceiptQueue = String.format("/topic/public/%d", fromUserId);
//        //simpleMessagingTemplate.convertAndSendToUser(fromUsername, returnReceiptQueue, message);
//        simpleMessagingTemplate.convertAndSendToUser(fromUsername, "/topic/public", message);
//
//    }

    @MessageMapping("/message/send/{toUserID}")
    public void send(SimpleMessage message, @DestinationVariable Long toUserID) {
        System.out.println(message);
    }
}
