package com.sender.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sender.sender.component.ChatRoomService;
import com.sender.sender.service.SendMessageService;

@RestController
public class senderController {
	@Autowired
    SendMessageService sendMsgService;

	@Autowired
	ChatRoomService chatRoomService;

    @GetMapping(value="/queue/sendMessage2")
    public void sendMessage2(
            @RequestParam("userId") String userId,
            @RequestParam("userName") String userName,
            @RequestParam("chatRoom") String chatRoom,
            @RequestParam("message") String message) {

        System.out.println("Message in controller: " + message);
        // chatRoomService.addUserToRoom(userId, chatRoom); 
        // sendMsgService.sendMsg(userId ,userName,chatRoom, message);
    }

	
	@GetMapping(value="/queue/sendMessage")
	public void sendMessage( @RequestParam("userId") String userId,@RequestParam("userName") String userName,
	@RequestParam("chatRoom") String chatRoom,@RequestParam("message") String message) {
		 
		chatRoomService.addUserToRoom(userId, chatRoom); 
		sendMsgService.sendMsg(userId ,userName,chatRoom, message);
		 
	}
}
