package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {}
