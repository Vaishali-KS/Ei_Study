package com.example.smartoffice.config;

import com.example.smartoffice.model.Room;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class OfficeConfig {
    private static final Logger LOGGER = Logger.getLogger(OfficeConfig.class.getName());
    private static OfficeConfig INSTANCE;
    private final Map<Integer, Room> rooms = new ConcurrentHashMap<>();

    private OfficeConfig() {}

    public static synchronized OfficeConfig getInstance() {
        if (INSTANCE == null) INSTANCE = new OfficeConfig();
        return INSTANCE;
    }

    public synchronized void configureRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.put(i, new Room(i));
        }
        LOGGER.info("Configured " + count + " rooms.");
    }

    public Optional<Room> getRoom(int id) {
        return Optional.ofNullable(rooms.get(id));
    }

    public Collection<Room> allRooms() {
        return Collections.unmodifiableCollection(rooms.values());
    }
    // default: 180 seconds (3 minutes)
    private int autoReleaseDelay = 180;

    public int getAutoReleaseDelay() {
        return autoReleaseDelay;
    }

    public void setAutoReleaseDelay(int autoReleaseDelay) {
        this.autoReleaseDelay = autoReleaseDelay;
    }

}
