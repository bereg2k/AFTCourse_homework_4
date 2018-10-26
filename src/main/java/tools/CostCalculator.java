package tools;

/**
 * Функциональный интерфейс для возможности осуществления конвертации валют.
 * На выходе выдаёт строчку типа "12.49 USD" (зависит от конкретной реализации абстрактвого метода convertCurrency).
 *
 * @param <A>      цена в рублях
 * @param <R>      коэффициент конвертации
 * @param <String> наименование валюты (EUR, USD...)
 */
@FunctionalInterface
public interface CostCalculator<A, R, String> {
    String convertCurrency(A amountRub, R rate, String currency);
}
