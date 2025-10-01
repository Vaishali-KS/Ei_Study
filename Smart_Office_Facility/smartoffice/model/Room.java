package com.example.smartoffice.model;

import com.example.smartoffice.observer.RoomObserver;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Room {
    private final int id;
    private int capacity = 0;
    private int currentOccupancy = 0;
    private boolean occupied = false;
    private Booking activeBooking;
    private final List<RoomObserver> observers = new CopyOnWriteArrayList<>();

    public Room(int id) { this.id = id; }

    public int getId() { return id; }

    public synchronized int getCapacity() { return capacity; }
    public synchronized void setCapacity(int capacity) { this.capacity = capacity; }

    public synchronized int getCurrentOccupancy() { return currentOccupancy; }

    public synchronized boolean isOccupied() { return occupied; }

    public synchronized void addObserver(RoomObserver obs) { observers.add(obs); }
    public synchronized void removeObserver(RoomObserver obs) { observers.remove(obs); }

    private void notifyObservers() {
        for (RoomObserver obs : observers) {
            try {
                obs.onOccupancyChanged(this);
            } catch (Exception e) {
                // swallow observer errors to prevent breaking core flow
            }
        }
    }

    public synchronized void updateOccupancy(int occupants) {
        if (occupants < 0) occupants = 0;
        this.currentOccupancy = occupants;
        boolean prev = this.occupied;
        this.occupied = occupants >= 2;
        if (prev != this.occupied) notifyObservers();
    }

    public synchronized Booking getActiveBooking() { return activeBooking; }
    public synchronized void setActiveBooking(Booking b) { this.activeBooking = b; }
    public synchronized void clearBooking() { this.activeBooking = null; }
}
