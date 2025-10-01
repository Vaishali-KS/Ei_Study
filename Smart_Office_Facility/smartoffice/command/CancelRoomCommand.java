package com.example.smartoffice.command;

import com.example.smartoffice.service.BookingService;

public class CancelRoomCommand implements Command {
    private final BookingService bookingService;
    private final int roomId;

    public CancelRoomCommand(BookingService bookingService, int roomId) {
        this.bookingService = bookingService;
        this.roomId = roomId;
    }

    @Override
    public CommandResult execute() {
        try {
            bookingService.cancelBooking(roomId);
            return CommandResult.success("Booking for Room " + roomId + " cancelled successfully.");
        } catch (Exception ex) {
            return CommandResult.failure("Cancel failed: " + ex.getMessage());
        }
    }
}
