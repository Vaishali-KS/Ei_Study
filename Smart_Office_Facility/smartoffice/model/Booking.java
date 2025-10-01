package com.example.smartoffice.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Booking {
    private final int roomId;
    private final LocalTime startTime;
    private final int durationMinutes;
    private final String bookedBy;
    private final LocalDateTime createdAt;

    public Booking(int roomId, LocalTime startTime, int durationMinutes, String bookedBy) {
        this.roomId = roomId;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.bookedBy = bookedBy;
        this.createdAt = LocalDateTime.now();
    }

    public int getRoomId() { return roomId; }
    public LocalTime getStartTime() { return startTime; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getBookedBy() { return bookedBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public LocalTime getEndTime() {
        return startTime.plusMinutes(durationMinutes);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return roomId == booking.roomId && durationMinutes == booking.durationMinutes && Objects.equals(startTime, booking.startTime) && Objects.equals(bookedBy, booking.bookedBy) && Objects.equals(createdAt, booking.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, startTime, durationMinutes, bookedBy, createdAt);
    }
}
