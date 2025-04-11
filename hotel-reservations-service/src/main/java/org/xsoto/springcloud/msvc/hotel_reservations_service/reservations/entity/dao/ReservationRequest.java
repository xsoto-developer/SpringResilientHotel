package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity.dao;

import lombok.Data;

@Data
public  class ReservationRequest {
//    static class ReservationRequest {
        private String roomId;
        private String checkIn;
        private String checkOut;
        private int guestCount;

        // Getters y Setters
        // ... getters y setters


}
