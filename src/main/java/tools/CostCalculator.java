package tools;

@FunctionalInterface
public interface CostCalculator<A, R, String> {
    String convertCurrency(A amountRub, R rate, String currency);
}
