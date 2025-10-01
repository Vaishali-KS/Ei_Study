package com.example.smartoffice.command;

import com.example.smartoffice.model.Booking;
import com.example.smartoffice.service.BookingService;

import java.time.LocalTime;

public class BookRoomCommand implements Command {
    private final BookingService bookingService;
    private final int roomId;
    private final LocalTime start;
    private final int duration;
    private final String bookedBy;

    public BookRoomCommand(BookingService bookingService, int roomId, LocalTime start, int duration, String bookedBy) {
        this.bookingService = bookingService;
        this.roomId = roomId;
        this.start = start;
        this.duration = duration;
        this.bookedBy = bookedBy;
    }

    @Override
    public CommandResult execute() {
        try {
            Booking booking = bookingService.bookRoom(roomId, start, duration, bookedBy);
            return CommandResult.success("Room " + roomId + " booked from " + start + " for " + duration + " minutes by " + bookedBy + ".");
        } catch (Exception ex) {
            return CommandResult.failure("Booking failed: " + ex.getMessage());
        }
    }
}
