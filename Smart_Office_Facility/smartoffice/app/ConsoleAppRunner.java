package com.example.smartoffice.app;

import com.example.smartoffice.command.*;
import com.example.smartoffice.config.OfficeConfig;
import com.example.smartoffice.model.Room;
import com.example.smartoffice.observer.ACController;
import com.example.smartoffice.observer.LightController;
import com.example.smartoffice.observer.OccupancySensor;
import com.example.smartoffice.scheduler.AutoReleaseScheduler;
import com.example.smartoffice.service.BookingService;

import java.time.LocalTime;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.concurrent.Executors;

public class ConsoleAppRunner {
    private static final Logger LOGGER = Logger.getLogger(ConsoleAppRunner.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Starting Smart Office Console App...");

        // Core singletons/services
        OfficeConfig config = OfficeConfig.getInstance();
        AutoReleaseScheduler scheduler = new AutoReleaseScheduler(300); // 15 seconds for testing; change to 300 for 5 minutes
        BookingService bookingService = new BookingService(config, scheduler);

        // Attach controllers (observers) when rooms are configured
        LightController lightController = new LightController();
        ACController acController = new ACController();

        CommandManager commandManager = new CommandManager();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Smart Office Console. Type 'help' for commands.");
        boolean running = true;

        while (running) {
            System.out.print("> ");
            String line = scanner.nextLine();
            if (line == null) break;
            String trimmed = line.trim();
            if (trimmed.isEmpty()) continue;
            String[] parts = trimmed.split("\\s+");
            String cmd = parts[0].toLowerCase();

            try {
                switch (cmd) {
                    case "help":
                        printHelp();
                        break;

                    case "config":
                        // config room count <n> OR config room maxcapacity <roomId> <capacity>
                        if (parts.length >= 4 && "room".equalsIgnoreCase(parts[1]) && "count".equalsIgnoreCase(parts[2])) {
                            int count = Integer.parseInt(parts[3]);
                            config.configureRooms(count);
                            // attach observers for each room
                            for (Room r : config.allRooms()) {
                                r.addObserver(lightController);
                                r.addObserver(acController);
                                r.addObserver(new OccupancySensor(bookingService)); // sensor will update occupancy via commands too
                            }
                            System.out.println("Office configured with " + count + " meeting rooms: " + config.allRooms().stream().map(Room::getId).toList());
                        } else if (parts.length >= 5 && "room".equalsIgnoreCase(parts[1]) && "maxcapacity".equalsIgnoreCase(parts[2])) {
                            int roomId = Integer.parseInt(parts[3]);
                            int cap = Integer.parseInt(parts[4]);
                            config.getRoom(roomId).ifPresentOrElse(
                                    room -> {
                                        room.setCapacity(cap);
                                        System.out.println("Room " + roomId + " maximum capacity set to " + cap + ".");
                                    },
                                    () -> System.out.println("Room " + roomId + " not found.")
                            );
                        } else {
                            System.out.println("Invalid config command. See help.");
                        }
                        break;

                    case "block":
                        // block room <roomId> <HH:mm> <durationMinutes> <bookedBy>
                        if (parts.length >= 6 && "room".equalsIgnoreCase(parts[1])) {
                            int roomId = Integer.parseInt(parts[2]);
                            LocalTime start = LocalTime.parse(parts[3]);
                            int duration = Integer.parseInt(parts[4]);
                            String bookedBy = String.join(" ", java.util.Arrays.copyOfRange(parts, 5, parts.length));
                            BookRoomCommand bookCmd = new BookRoomCommand(bookingService, roomId, start, duration, bookedBy);
                            CommandResult res = commandManager.execute(bookCmd);
                            System.out.println(res.getMessage());
                        } else {
                            System.out.println("Invalid block command. See help.");
                        }
                        break;

                    case "cancel":
                        // cancel room <roomId>
                        if (parts.length >= 3 && "room".equalsIgnoreCase(parts[1])) {
                            int roomId = Integer.parseInt(parts[2]);
                            CancelRoomCommand cancelCmd = new CancelRoomCommand(bookingService, roomId);
                            CommandResult cres = commandManager.execute(cancelCmd);
                            System.out.println(cres.getMessage());
                        } else {
                            System.out.println("Invalid cancel command. See help.");
                        }
                        break;

                    case "add":
                        // add occupant <roomId> <count>
                        if (parts.length >= 4 && "occupant".equalsIgnoreCase(parts[1])) {
                            int roomId = Integer.parseInt(parts[2]);
                            int count = Integer.parseInt(parts[3]);
                            AddOccupantCommand addOcc = new AddOccupantCommand(bookingService, roomId, count);
                            CommandResult ares = commandManager.execute(addOcc);
                            System.out.println(ares.getMessage());
                        } else {
                            System.out.println("Invalid add command. See help.");
                        }
                        break;

                    case "status":
                        // status room <roomId>
                        if (parts.length >= 3 && "room".equalsIgnoreCase(parts[1])) {
                            int roomId = Integer.parseInt(parts[2]);
                            config.getRoom(roomId).ifPresentOrElse(
                                    room -> System.out.println(bookingService.getRoomStatus(room)),
                                    () -> System.out.println("Room " + roomId + " not found.")
                            );
                        } else {
                            System.out.println("Invalid status command. See help.");
                        }
                        break;

                    case "stats":
                        System.out.println(bookingService.getStatistics());
                        break;

                    case "exit":
                        running = false;
                        break;

                    default:
                        System.out.println("Unknown command. Type 'help' for usage.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
                LOGGER.severe("Exception processing command: " + ex.toString());
            }
        }

        // shutdown scheduler
        scheduler.shutdown();
        System.out.println("Application exiting.");
        scanner.close();
    }

    private static void printHelp() {
        System.out.println("""
                Commands:
                config room count <n>
                config room maxcapacity <roomId> <capacity>
                block room <roomId> <HH:mm> <durationMinutes> <bookedBy>
                cancel room <roomId>
                add occupant <roomId> <count>
                status room <roomId>
                stats
                help
                exit
                """);
    }
}
