package com.example.smartoffice.service;

import com.example.smartoffice.config.OfficeConfig;
import com.example.smartoffice.exceptions.BookingConflictException;
import com.example.smartoffice.exceptions.RoomNotFoundException;
import com.example.smartoffice.model.Booking;
import com.example.smartoffice.model.Room;
import com.example.smartoffice.scheduler.AutoReleaseScheduler;

import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Very lightweight BookingService that attaches bookings to rooms (one active booking per room for simplicity).
 * It also schedules auto-release checks via AutoReleaseScheduler.
 */
public class BookingService {
    private static final Logger LOGGER = Logger.getLogger(BookingService.class.getName());
    private final OfficeConfig config;
    private final AutoReleaseScheduler scheduler;
    // simple statistics & storage
    private final Map<Integer, Integer> autoReleaseCounts = new ConcurrentHashMap<>();

    public BookingService(OfficeConfig config, AutoReleaseScheduler scheduler) {
        this.config = config;
        this.scheduler = scheduler;
    }

    public synchronized Booking bookRoom(int roomId, LocalTime start, int durationMin, String bookedBy) {
        Room room = config.getRoom(roomId).orElseThrow(() -> new RoomNotFoundException("Room " + roomId + " does not exist."));
        if (room.getCapacity() <= 0) {
            throw new IllegalArgumentException("Room " + roomId + " capacity is not set. Please set capacity before booking.");
        }

        Booking existing = room.getActiveBooking();
        LocalTime newStart = start;
        LocalTime newEnd = start.plusMinutes(durationMin);

        if (existing != null) {
            // check overlap
            LocalTime eStart = existing.getStartTime();
            LocalTime eEnd = existing.getEndTime();
            boolean overlap = newStart.isBefore(eEnd) && eStart.isBefore(newEnd);
            if (overlap) {
                throw new BookingConflictException("Room " + roomId + " is already booked during this time. Cannot book.");
            } else {
                // for simplicity support only one booking stored; real app would use a list
                // we allow replacing if no overlap (but keeping one active booking simplifies demo)
            }
        }

        Booking booking = new Booking(roomId, start, durationMin, bookedBy);
        room.setActiveBooking(booking);
        LOGGER.info("Room " + roomId + " booked by " + bookedBy + " from " + start + " for " + durationMin + " mins.");

        // schedule auto-release check (release if not occupied within configured seconds)
        scheduler.scheduleAutoRelease(roomId, booking, this);

        return booking;
    }

    public synchronized void cancelBooking(int roomId) {
        Room room = config.getRoom(roomId).orElseThrow(() -> new RoomNotFoundException("Room " + roomId + " does not exist."));
        if (room.getActiveBooking() == null) {
            throw new IllegalStateException("Room " + roomId + " is not booked. Cannot cancel booking.");
        }
        room.clearBooking();
        LOGGER.info("Booking for room " + roomId + " cancelled.");
    }

    public synchronized void updateOccupancy(int roomId, int occupants) {
        Room room = config.getRoom(roomId).orElseThrow(() -> new RoomNotFoundException("Room " + roomId + " does not exist."));
        if (occupants < 0) throw new IllegalArgumentException("Occupancy count must be non-negative.");
        if (occupants > room.getCapacity()) throw new IllegalArgumentException("Occupancy exceeds room capacity.");
        room.updateOccupancy(occupants);
        LOGGER.info("Room " + roomId + " occupancy updated to " + occupants);
    }

    public synchronized void autoReleaseIfUnoccupied(int roomId, Booking booking) {
        Room room = config.getRoom(roomId).orElse(null);
        if (room == null) return;
        Booking active = room.getActiveBooking();
        if (active == null) return;
        // ensure it's the same booking (compare start and creator)
        if (!booking.equals(active)) return;

        if (!room.isOccupied()) {
            // release booking
            room.clearBooking();
            int count = autoReleaseCounts.getOrDefault(roomId, 0) + 1;
            autoReleaseCounts.put(roomId, count);
            LOGGER.info("Booking for room " + roomId + " released due to no occupancy within configured time.");
            System.out.println("Room " + roomId + " booking released due to no occupancy within configured time.");
        } else {
            LOGGER.info("Room " + roomId + " occupied; not releasing booking.");
        }
    }

    public String getRoomStatus(Room room) {
        StringBuilder sb = new StringBuilder();
        sb.append("Room ").append(room.getId()).append(" - Capacity: ").append(room.getCapacity()).append("\n");
        sb.append("Occupancy: ").append(room.getCurrentOccupancy()).append(", Occupied: ").append(room.isOccupied()).append("\n");
        if (room.getActiveBooking() != null) {
            sb.append("Booking: by ").append(room.getActiveBooking().getBookedBy())
                    .append(", start ").append(room.getActiveBooking().getStartTime())
                    .append(", duration ").append(room.getActiveBooking().getDurationMinutes()).append(" mins\n");
        } else {
            sb.append("No active booking.\n");
        }
        return sb.toString();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("Auto-release counts per room:\n");
        for (var e : autoReleaseCounts.entrySet()) {
            sb.append("Room ").append(e.getKey()).append(" -> ").append(e.getValue()).append("\n");
        }
        return sb.toString();
    }
}
