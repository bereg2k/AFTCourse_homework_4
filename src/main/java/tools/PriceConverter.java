package tools;

/**
 * Класс, реализующий интерфейс CostCalculator.
 * Переопределяет метод конвертации convertCurrency.
 */
public class PriceConverter implements CostCalculator {
    /**
     * Реализация метода конвертации валют.
     *
     * @param amountRub стоимость в рублях
     * @param rate      коэффициент конвертации
     * @param currency  наименование валюты
     * @return на выходе получается строчка типа "12.58 EUR"
     */
    @Override
    public String convertCurrency(Object amountRub, Object rate, Object currency) {
        String s = String.format("%.2f", (double) amountRub * (double) rate);
        return s + " " + currency;
    }
}
