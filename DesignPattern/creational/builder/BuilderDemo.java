package creational.builder;

import java.util.Scanner;

public class BuilderDemo {
    public static void run(Scanner sc) {
        String option = "";
        do {
            System.out.println("\n--- Computer Assembly ---");
            System.out.println("1. Build Gaming Computer");
            System.out.println("2. Build Office Computer");
            System.out.println("3. Exit Computer Assembly");
            System.out.print("Enter choice: ");
            option = sc.nextLine();

            switch (option) {
                case "1" -> {
                    Director director = new Director(new GamingComputerBuilder());
                    director.constructComputer();
                    Computer gamingComputer = director.getComputer();
                    System.out.println("Your Gaming Computer: " + gamingComputer);
                }
                case "2" -> {
                    Director director = new Director(new OfficeComputerBuilder());
                    director.constructComputer();
                    Computer officeComputer = director.getComputer();
                    System.out.println("Your Office Computer: " + officeComputer);
                }
                case "3" -> System.out.println("Exiting Computer Assembly...");
                default -> System.out.println("Invalid choice");
            }
        } while (!option.equals("3"));
    }
}
