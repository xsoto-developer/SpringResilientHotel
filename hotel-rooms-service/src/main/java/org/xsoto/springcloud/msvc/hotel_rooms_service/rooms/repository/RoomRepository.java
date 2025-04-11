package org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.repository;


//import com.hotel.reservations.rooms.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.entity.Room;

public interface RoomRepository extends JpaRepository<Room, String> {}