package structural.adapter;

import java.util.Scanner;

public class AdapterDemo {
    public static void run(Scanner sc) {
        String option = "";

        do {
            System.out.println("\n--- Currency Converter Menu ---");
            System.out.println("1. Convert USD to INR");
            System.out.println("2. Exit Converter");
            System.out.print("Enter choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Enter amount in USD: ");
                    try {
                        double usdAmount = Double.parseDouble(sc.nextLine());
                        USD usd = new USD();
                        Currency adapter = new CurrencyAdapter(usd);
                        double inrAmount = adapter.getINR(usdAmount);
                        System.out.println(usdAmount + " USD = " + inrAmount + " INR");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                    }
                }
                case "2" -> System.out.println("Exiting Converter...");
                default -> System.out.println("Invalid choice");
            }
        } while (!option.equals("2"));
    }
}
