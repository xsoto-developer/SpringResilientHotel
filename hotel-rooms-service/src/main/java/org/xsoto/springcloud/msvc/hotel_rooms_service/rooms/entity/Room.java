package org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "room")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private String id;
    private String type; // Ejemplo: "simple", "double", "suite"
    private double price;
    private boolean available;
//
//    // Getters, Setters y Constructor
//    public Room() {}
//    public Room(String id, String type, double price, boolean available) {
//        this.id = id;
//        this.type = type;
//        this.price = price;
//        this.available = available;
//    }
//    // ... getters y setters
}
