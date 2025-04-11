package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.factory;

import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.Reservation;

import java.util.UUID;

public class ReservationFactory {
    public static Reservation createReservation(String roomId, String checkIn, String checkOut, int guestCount) {
        return new Reservation(UUID.randomUUID().toString(), roomId, checkIn, checkOut, guestCount);
    }
}