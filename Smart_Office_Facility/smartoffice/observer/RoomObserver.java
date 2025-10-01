package com.example.smartoffice.observer;

import com.example.smartoffice.model.Room;

public interface RoomObserver {
    void onOccupancyChanged(Room room);
}
