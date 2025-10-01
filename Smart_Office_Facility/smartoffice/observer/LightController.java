package com.example.smartoffice.observer;

import com.example.smartoffice.model.Room;

import java.util.logging.Logger;

public class LightController implements RoomObserver {
    private static final Logger LOGGER = Logger.getLogger(LightController.class.getName());

    @Override
    public void onOccupancyChanged(Room room) {
        if (room.isOccupied()) {
            LOGGER.info("Lights turned ON for Room " + room.getId());
            System.out.println("Room " + room.getId() + " lights turned ON.");
        } else {
            LOGGER.info("Lights turned OFF for Room " + room.getId());
            System.out.println("Room " + room.getId() + " lights turned OFF.");
        }
    }
}
