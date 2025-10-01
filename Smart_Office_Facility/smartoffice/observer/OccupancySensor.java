package com.example.smartoffice.observer;

import com.example.smartoffice.model.Room;
import com.example.smartoffice.service.BookingService;

public class OccupancySensor implements RoomObserver {
    // This sensor simply reacts to occupancy changes and could notify external systems.
    // Also allows updating occupancy via BookingService commands (we attach an instance to the room).
    private final BookingService bookingService;

    public OccupancySensor(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Override
    public void onOccupancyChanged(Room room) {
        // We log or perform actions when occupancy changes - this example prints status
        if (room.isOccupied()) {
            System.out.println("OccupancySensor: Room " + room.getId() + " is now OCCUPIED (" + room.getCurrentOccupancy() + " people).");
        } else {
            System.out.println("OccupancySensor: Room " + room.getId() + " is now UNOCCUPIED.");
        }
    }
}
