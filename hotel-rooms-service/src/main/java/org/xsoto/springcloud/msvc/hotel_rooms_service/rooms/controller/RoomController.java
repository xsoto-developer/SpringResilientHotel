package org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.entity.Room;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.service.RoomService;

import java.util.List;

@RestController
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms/available")
    public List<Room> getAvailableRooms(
            @RequestParam int guestCount,
            @RequestParam double maxPrice) {
        return roomService.findAvailableRooms(guestCount, maxPrice);
    }
}