package org.xsoto.springcloud.msvc.hotel_reservations_service.reservations.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "reservation" )
//@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    private String id;
    private String roomId;
    private String checkIn;
    private String checkOut;
    private int guestCount;

    // Getters, Setters y Constructor
//    public Reservation() {}
//    public Reservation(String id, String roomId, String checkIn, String checkOut, int guestCount) {
//        this.id = id;
//        this.roomId = roomId;
//        this.checkIn = checkIn;
//        this.checkOut = checkOut;
//        this.guestCount = guestCount;
//    }
    // ... getters y setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reservation that = (Reservation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}