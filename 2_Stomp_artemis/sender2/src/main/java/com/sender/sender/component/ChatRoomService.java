package com.sender.sender.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgroups.annotations.Component;

@org.springframework.stereotype.Component
public class ChatRoomService {

   private Map<String, List<String>> userRoomsMap = new HashMap<>();

    public void addUserToRoom(String userId, String room) {
        List<String> rooms = userRoomsMap.computeIfAbsent(userId, k -> new ArrayList<>());
        if (!rooms.contains(room)) {
            rooms.add(room);
        }
    }

    public void removeUserFromRoom(String userId, String room) {
        List<String> rooms = userRoomsMap.get(userId);
        if (rooms != null) {
            rooms.remove(room);
            if (rooms.isEmpty()) {
                userRoomsMap.remove(userId);
            }
        }
    }

    // Method to get rooms of a user
    public List<String> getUserRooms(String userId) {
        return userRoomsMap.getOrDefault(userId, Collections.emptyList());
    }
}