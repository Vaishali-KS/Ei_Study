package behavioral.strategy;

public class ScenicRoute implements RouteStrategy {
    @Override
    public void buildRoute(String source, String destination) {
        System.out.println("Calculating scenic route from " + source + " to " + destination + "...");
        System.out.println("Route: " + source + " -> Lake View -> Hilltop -> " + destination);
    }
}
