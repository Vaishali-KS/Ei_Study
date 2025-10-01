package creational.factory;

public class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        return switch (type.toLowerCase()) {
            case "car" -> new Car();
            case "bike" -> new Bike();
            case "truck" -> new Truck();
            default -> null;
        };
    }
}
