package creational.factory;

public class Bike implements Vehicle {
    @Override
    public void rent() {
        System.out.println("Bike rented. Cost: $20/day. Seats: 2");
    }
}
