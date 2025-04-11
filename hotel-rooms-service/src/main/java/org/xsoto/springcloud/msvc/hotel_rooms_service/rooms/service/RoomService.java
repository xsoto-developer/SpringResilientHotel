package org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.service;


import org.springframework.stereotype.Service;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.entity.Room;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.repository.RoomRepository;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.specification.AvailableRoomSpecification;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAvailableRooms(int guestCount, double maxPrice) {
        AvailableRoomSpecification spec = new AvailableRoomSpecification(guestCount, maxPrice);
        return roomRepository.findAll().stream()
                .filter(spec::isSatisfiedBy)
                .toList();
    }
}
