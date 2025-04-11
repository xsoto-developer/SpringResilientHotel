package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.service;

import org.springframework.stereotype.Service;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.Reservation;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.factory.ReservationFactory;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(String roomId, String checkIn, String checkOut, int guestCount) {
        Reservation reservation = ReservationFactory.createReservation(roomId, checkIn, checkOut, guestCount);
        return reservationRepository.save(reservation);
    }
}