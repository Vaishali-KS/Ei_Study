package behavioral.observer;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ObserverDemo {
    public static void run(Scanner sc) {
        Stock stock = new Stock("Tesla", 800);
        Map<String, Investor> investors = new HashMap<>();
        String option = "";

        do {
            System.out.println("\n--- Stock Market Menu ---");
            System.out.println("1. Add Investor");
            System.out.println("2. Remove Investor");
            System.out.println("3. Update Stock Price");
            System.out.println("4. Exit Observer Demo");
            System.out.print("Enter choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Enter Investor name: ");
                    String name = sc.nextLine();
                    Investor inv = new Investor(name);
                    investors.put(name, inv);
                    stock.addObserver(inv);
                    System.out.println(name + " subscribed to Tesla updates.");
                }
                case "2" -> {
                    System.out.print("Enter Investor name to remove: ");
                    String name = sc.nextLine();
                    Investor inv = investors.remove(name);
                    if (inv != null) {
                        stock.removeObserver(inv);
                        System.out.println(name + " unsubscribed.");
                    } else {
                        System.out.println("No such investor.");
                    }
                }
                case "3" -> {
                    System.out.print("Enter new stock price: ");
                    try {
                        double price = Double.parseDouble(sc.nextLine());
                        stock.setPrice(price);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number.");
                    }
                }
                case "4" -> System.out.println("Exiting Observer Demo...");
                default -> System.out.println("Invalid choice.");
            }
        } while (!option.equals("4"));
    }
}
