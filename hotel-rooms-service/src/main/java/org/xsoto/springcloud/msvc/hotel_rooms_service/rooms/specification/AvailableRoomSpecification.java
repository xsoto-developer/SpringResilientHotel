package org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.specification;

//import com.hotel.reservations.rooms.entity.Room;

import org.xsoto.springcloud.msvc.hotel_rooms_service.rooms.entity.Room;

public class AvailableRoomSpecification {
    private final int guestCount;
    private final double maxPrice;

    public AvailableRoomSpecification(int guestCount, double maxPrice) {
        this.guestCount = guestCount;
        this.maxPrice = maxPrice;
    }

    public boolean isSatisfiedBy(Room room) {
        return room.isAvailable() && room.getPrice() <= maxPrice && canFitGuests(room.getType());
    }

    private boolean canFitGuests(String roomType) {
        return switch (roomType) {
            case "simple" -> guestCount <= 2;
            case "double" -> guestCount <= 4;
            case "suite" -> guestCount <= 6;
            default -> false;
        };
    }
}