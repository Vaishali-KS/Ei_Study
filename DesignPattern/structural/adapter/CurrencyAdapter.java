package structural.adapter;

public class CurrencyAdapter implements Currency {
    private USD usd;

    public CurrencyAdapter(USD usd) {
        this.usd = usd;
    }

    @Override
    public double getINR(double amount) {
        double usdAmount = usd.getUSD(amount);
        double inrAmount = usdAmount * 82.0; // Example conversion rate
        return inrAmount;
    }
}
