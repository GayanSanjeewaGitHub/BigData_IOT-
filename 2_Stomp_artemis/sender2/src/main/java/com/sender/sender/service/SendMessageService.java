package com.sender.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sender.sender.component.ChatRoomService;

import lombok.Synchronized;

@Service
public class SendMessageService {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private ReceiverService receiverService;

	@Autowired
	private ChatRoomService chatRoomService;

	public void sendMsg(String id, String name, String chatRoom, String message) {

		System.out.println("Message sent for chatRoom: " + chatRoom + " details: " + id + ":" + name + ":" + message);
		jmsTemplate.convertAndSend(chatRoom, id + ":" + name + ":" + message + ":" + chatRoom);
		// receiverService.subscribeToRooms(id);

	}
}
