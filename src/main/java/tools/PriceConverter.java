package tools;

public class PriceConverter implements CostCalculator {
    @Override
    public String convertCurrency(Object amountRub, Object rate, Object currency) {
        String s = String.format("%.2f", (double) amountRub * (double) rate);
        return s + " " + currency;
    }
}
