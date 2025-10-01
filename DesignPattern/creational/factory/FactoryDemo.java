package creational.factory;

import java.util.Scanner;

public class FactoryDemo {
    public static void run(Scanner sc) {
        String option = "";

        do {
            System.out.println("\n--- Vehicle Rental Menu ---");
            System.out.println("1. Rent a Vehicle");
            System.out.println("2. Exit Vehicle Rental");
            System.out.print("Enter choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Enter vehicle type (Car/Bike/Truck): ");
                    String type = sc.nextLine();
                    Vehicle vehicle = VehicleFactory.getVehicle(type);
                    if (vehicle != null) {
                        vehicle.rent();
                    } else {
                        System.out.println("Invalid vehicle type.");
                    }
                }
                case "2" -> System.out.println("Exiting Vehicle Rental...");
                default -> System.out.println("Invalid choice");
            }
        } while (!option.equals("2"));
    }
}
