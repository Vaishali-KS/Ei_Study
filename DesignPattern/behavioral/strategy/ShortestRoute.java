package behavioral.strategy;

public class ShortestRoute implements RouteStrategy {
    @Override
    public void buildRoute(String source, String destination) {
        System.out.println("Calculating shortest route from " + source + " to " + destination + "...");
        System.out.println("Route: " + source + " -> Point A -> Point B -> " + destination);
    }
}
