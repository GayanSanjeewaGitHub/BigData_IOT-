package com.sender.sender.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;
import org.springframework.stereotype.Service;

import com.sender.sender.component.ChatRoomService;

import java.util.List;

@Service
public class ReceiverService {

    private final JmsListenerEndpointRegistry registry;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ReceiverService(JmsListenerEndpointRegistry registry, ChatRoomService chatRoomService) {
        this.registry = registry;
        this.chatRoomService = chatRoomService;
    }

    
    // public void subscribeToRooms(String userId) {
    //     List<String> userRooms = chatRoomService.getUserRooms(userId);
    //     for (String room : userRooms) {
    //         String destination = room.replaceAll("\\s", ""); 
    //         if (destination != null){
    //             registry.getListenerContainer("chatRoom1").start();
    //             registry.registerListenerContainer(null, null);
    //         }
    //     }
    // }

    // // Method to dynamically remove JMS listeners for each room unsubscribed by the user
    // public void unsubscribeFromRooms(String userId) {
    //     List<String> userRooms = chatRoomService.getUserRooms(userId);
    //     for (String room : userRooms) {
    //         String destination = room.replaceAll("\\s", ""); // Remove spaces from room name
    //         if (destination != null){
    //             registry.getListenerContainer("chatRoom1").stop();
    //         }
    //     }
    // }

    @JmsListener(destination = "chatRoomcommon", containerFactory = "jmsListenerContainerFactory")
    public void receiveMsgFromCommonRoom(String message) {
        System.out.println("receive service from CommonRoom: " + message);
        // Here you can handle the received message from the CommonRoom
    }

    @JmsListener(destination = "chatRoom1", containerFactory = "jmsListenerContainerFactory")
    public void receiveMsgFromOtherRoom(String message) {
        System.out.println("receive service from OtherRoom: " + message);
        
    }

    
}
