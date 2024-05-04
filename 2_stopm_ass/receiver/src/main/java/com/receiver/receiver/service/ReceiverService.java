package com.receiver.receiver.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
	@JmsListener(destination="CommonRoom")
    public void receiveMsg(String message) {
        System.out.println("receive service from CommonRoom: " + message);
        // Here you can handle the received message from the CommonRoom
    }

    @JmsListener(destination="OtherRoom")
    public void receiveMsgFromOtherRoom(String message) {
        System.out.println("receive service from OtherRoom: " + message);
        // Here you can handle the received message from the OtherRoom
    }
}
