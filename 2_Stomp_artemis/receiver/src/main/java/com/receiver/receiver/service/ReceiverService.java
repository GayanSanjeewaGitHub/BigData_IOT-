package com.receiver.receiver.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
	@JmsListener( destination="learning")
	public void receiveMsg(String message) {
		System.out.println("receive service "+message);
	}
}
