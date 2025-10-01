package creational.factory;

public class Car implements Vehicle {
    @Override
    public void rent() {
        System.out.println("Car rented. Cost: $50/day. Seats: 4");
    }
}
