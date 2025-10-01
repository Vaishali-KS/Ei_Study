package behavioral.observer;

public class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double newPrice) {
        System.out.println(name + " notified: " + stockName + " is now " + newPrice);
    }
}
