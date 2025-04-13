package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.service;

//package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.Reservation;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.repository.ReservationRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation_Success() {
        // Arrange
        String roomId = "room1";
//        LocalDate checkIn = LocalDate.parse("2025-04-15");
//        LocalDate checkOut = LocalDate.parse("2025-04-20");
        String checkIn = "2025-04-15";
        String checkOut = "2025-04-20";
        int guestCount = 2;

        Reservation savedReservation = new Reservation("res1", roomId, checkIn, checkOut, guestCount);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        // Act
        Reservation result = reservationService.createReservation(roomId, checkIn, checkOut, guestCount);

        // Assert
        assertEquals("res1", result.getId());
        assertEquals(roomId, result.getRoomId());
        assertEquals(checkIn, result.getCheckIn());
        assertEquals(checkOut, result.getCheckOut());
        assertEquals(guestCount, result.getGuestCount());
    }
}
