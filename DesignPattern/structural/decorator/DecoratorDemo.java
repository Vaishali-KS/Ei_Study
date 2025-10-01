package structural.decorator;

import java.util.Scanner;

public class DecoratorDemo {
    public static void run(Scanner sc) {
        String option = "";
        do {
            System.out.println("\n--- Online Coffee Shop ---");
            System.out.println("1. Order Coffee");
            System.out.println("2. Exit Coffee Shop");
            System.out.print("Enter choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1" -> {
                    Coffee coffee = new SimpleCoffee();
                    System.out.println("Add ingredients:");
                    System.out.println("1. Milk");
                    System.out.println("2. Sugar");
                    System.out.println("3. Done");
                    String ingOption;
                    do {
                        System.out.print("Enter choice: ");
                        ingOption = sc.nextLine();
                        switch (ingOption) {
                            case "1" -> coffee = new MilkDecorator(coffee);
                            case "2" -> coffee = new SugarDecorator(coffee);
                            case "3" -> System.out.println("Finalizing order...");
                            default -> System.out.println("Invalid choice");
                        }
                    } while (!ingOption.equals("3"));

                    System.out.println("Your Order: " + coffee.getDescription());
                    System.out.println("Total Cost: $" + coffee.getCost());
                }
                case "2" -> System.out.println("Exiting Coffee Shop...");
                default -> System.out.println("Invalid choice");
            }
        } while (!option.equals("2"));
    }
}
