package org.xsoto.springcloud.msvc.hotel_rooms_service.service;

//package org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.entity.Room;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.repository.RoomRepository;
import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.service.RoomService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAvailableRooms_WithGuestCountAndMaxPrice() {
        // Arrange
        Room room1 = new Room("1", "Single", 100.0, true);
        Room room2 = new Room("2", "Double", 150.0, true);
        Room room3 = new Room("3", "Suite", 300.0, true);

        List<Room> allRooms = Arrays.asList(room1, room2, room3);
        when(roomRepository.findAll((Sort) null)).thenReturn(allRooms);

        // Act
        List<Room> availableRooms = roomService.findAvailableRooms(2, 200.0);

        // Assert
        assertEquals(1, availableRooms.size());
        assertEquals("2", availableRooms.get(0).getId());
        assertEquals("Double", availableRooms.get(0).getType());
    }

    @Test
    void testFindAvailableRooms_NoMatchingRooms() {
        // Arrange
        Room room1 = new Room("1", "Single", 100.0, true);
        when(roomRepository.findAll((Sort) null)).thenReturn(Arrays.asList(room1));

        // Act
        List<Room> availableRooms = roomService.findAvailableRooms(2, 50.0);

        // Assert
        assertEquals(0, availableRooms.size());
    }
}
