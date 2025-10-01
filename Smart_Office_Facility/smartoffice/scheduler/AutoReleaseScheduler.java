package com.example.smartoffice.scheduler;

import com.example.smartoffice.model.Booking;
import com.example.smartoffice.service.BookingService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Schedules auto-release checks. delaySeconds is configurable for testing (e.g. 15s).
 */
public class AutoReleaseScheduler {
    private final ScheduledExecutorService scheduler;
    private final int releaseDelaySeconds;

    public AutoReleaseScheduler(int releaseDelaySeconds) {
        this.releaseDelaySeconds = releaseDelaySeconds;
        this.scheduler = Executors.newScheduledThreadPool(2);
    }

    public void scheduleAutoRelease(int roomId, Booking booking, BookingService bookingService) {
        Runnable task = () -> {
            try {
                bookingService.autoReleaseIfUnoccupied(roomId, booking);
            } catch (Exception e) {
                // log inside service
            }
        };
        scheduler.schedule(task, releaseDelaySeconds, TimeUnit.SECONDS);
    }

    public void shutdown() {
        scheduler.shutdownNow();
    }
}
