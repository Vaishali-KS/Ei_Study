package behavioral.strategy;

public class FastestRoute implements RouteStrategy {
    @Override
    public void buildRoute(String source, String destination) {
        System.out.println("Calculating fastest route from " + source + " to " + destination + "...");
        System.out.println("Route: " + source + " -> Highway -> " + destination);
    }
}
