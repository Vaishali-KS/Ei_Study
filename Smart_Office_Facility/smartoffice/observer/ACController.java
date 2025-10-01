package com.example.smartoffice.observer;

import com.example.smartoffice.model.Room;

import java.util.logging.Logger;

public class ACController implements RoomObserver {
    private static final Logger LOGGER = Logger.getLogger(ACController.class.getName());

    @Override
    public void onOccupancyChanged(Room room) {
        if (room.isOccupied()) {
            LOGGER.info("AC turned ON for Room " + room.getId());
            System.out.println("Room " + room.getId() + " AC turned ON.");
        } else {
            LOGGER.info("AC turned OFF for Room " + room.getId());
            System.out.println("Room " + room.getId() + " AC turned OFF.");
        }
    }
}
