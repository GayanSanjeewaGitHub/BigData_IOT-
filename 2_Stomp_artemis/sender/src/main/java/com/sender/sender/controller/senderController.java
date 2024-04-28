package com.sender.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sender.sender.service.SendMessageService;

@RestController
public class senderController {
	@Autowired
	SendMessageService sendMsgService;
	
	@GetMapping(value="/queue/sendMessage")
	public void sendMessage(@RequestParam("message") String message) {
		System.out.println("Message is controller "+message);
		sendMsgService.sendMsg(message);
	}
}
