package behavioral.strategy;

import java.util.Scanner;

public class RouteDemo {
    public static void run(Scanner sc) {
        String option = "";

        do {
            System.out.println("\n--- Route Finder Menu ---");
            System.out.println("1. Find a Route");
            System.out.println("2. Exit Route Finder");
            System.out.print("Enter choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Enter source: ");
                    String source = sc.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = sc.nextLine();

                    System.out.println("Choose route type:");
                    System.out.println("1. Shortest Route");
                    System.out.println("2. Fastest Route");
                    System.out.println("3. Scenic Route");
                    System.out.print("Enter choice: ");
                    String routeChoice = sc.nextLine();

                    RouteStrategy strategy;
                    switch (routeChoice) {
                        case "1" -> strategy = new ShortestRoute();
                        case "2" -> strategy = new FastestRoute();
                        case "3" -> strategy = new ScenicRoute();
                        default -> {
                            System.out.println("Invalid choice. Using Shortest Route by default.");
                            strategy = new ShortestRoute();
                        }
                    }
                    strategy.buildRoute(source, destination);
                }
                case "2" -> System.out.println("Exiting Route Finder...");
                default -> System.out.println("Invalid choice.");
            }
        } while (!option.equals("2"));
    }
}
