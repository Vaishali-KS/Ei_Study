import java.util.Scanner;
import behavioral.observer.ObserverDemo;
import behavioral.strategy.RouteDemo;
import creational.factory.FactoryDemo;
import creational.builder.BuilderDemo;  // Newly added
import structural.adapter.AdapterDemo;
import structural.decorator.DecoratorDemo;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "";

        do {
            System.out.println("\n=== Design Patterns Menu ===");
            System.out.println("1. Observer Pattern – Stock Market");
            System.out.println("2. Strategy Pattern – Route Finder");
            System.out.println("3. Factory Pattern – Vehicle Rental");      // Creational
            System.out.println("4. Builder Pattern – Computer Assembly");  // Creational
            System.out.println("5. Adapter Pattern – Currency Converter");
            System.out.println("6. Decorator Pattern – Coffee Shop");
            System.out.println("Type 'exit' to quit.");
            System.out.print("Enter choice: ");
            choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> ObserverDemo.run(sc);
                case "2" -> RouteDemo.run(sc);
                case "3" -> FactoryDemo.run(sc);
                case "4" -> BuilderDemo.run(sc);
                case "5" -> AdapterDemo.run(sc);
                case "6" -> DecoratorDemo.run(sc);
                case "exit" -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice");
            }
        } while (!choice.equalsIgnoreCase("exit"));

        sc.close();
    }
}
