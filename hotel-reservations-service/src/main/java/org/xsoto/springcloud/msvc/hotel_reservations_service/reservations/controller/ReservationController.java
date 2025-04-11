package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.Reservation;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.dao.ReservationRequest;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.service.ReservationService;

@RestController
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservations")
    public Reservation createReservation(@RequestBody ReservationRequest request) {
        return reservationService.createReservation(
                request.getRoomId(), request.getCheckIn(), request.getCheckOut(), request.getGuestCount());
    }


}