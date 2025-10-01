package creational.factory;

public class Truck implements Vehicle {
    @Override
    public void rent() {
        System.out.println("Truck rented. Cost: $80/day. Capacity: 5 tons");
    }
}
