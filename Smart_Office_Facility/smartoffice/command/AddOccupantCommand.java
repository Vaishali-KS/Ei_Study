package com.example.smartoffice.command;

import com.example.smartoffice.service.BookingService;

public class AddOccupantCommand implements Command {
    private final BookingService bookingService;
    private final int roomId;
    private final int count;

    public AddOccupantCommand(BookingService bookingService, int roomId, int count) {
        this.bookingService = bookingService;
        this.roomId = roomId;
        this.count = count;
    }

    @Override
    public CommandResult execute() {
        try {
            bookingService.updateOccupancy(roomId, count);
            return CommandResult.success("Occupancy updated for Room " + roomId + " to " + count + " occupants.");
        } catch (Exception ex) {
            return CommandResult.failure("Update occupancy failed: " + ex.getMessage());
        }
    }
}
