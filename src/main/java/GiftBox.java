import sweets.Sweets;
import tools.PriceConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для описания свойств и методов подарочной коробки.
 * Коробка нужна для упаковки в неё сладостей из класса sweets.Sweets.
 */
class GiftBox {
    private List<Sweets> sweetsInTheBox = new ArrayList<>(); //массив сладостей
    private double boxWeight; //общий вес коробки
    private double boxPrice; //общая стоимость коробки

    private Predicate<Sweets> boxPolicy = sweets -> true; //policy для фильтрации добавления сладостей в коробку

    private boolean isPriceConverted = false; // флаг для включения конвертации валют
    private Double rate; //коэффициент конвертации
    private String currency; //строковое значение валюты для конвертации
    private PriceConverter rubToCurrency = new PriceConverter(); //объект класса, реализующий интерфейс конвертации валют

    enum Currency { //enum-значения для валют
        EUR, USD, GBP, RUB
    }

    GiftBox() {
    }

    List<Sweets> getSweetsInTheBox() {
        return sweetsInTheBox;
    }

    /**
     * Метод запуска работы политики ограничения на добавление сладостей в коробку.
     * Метод получает переменную, по которой понимает как надо фильтровать новые сладости при добавлении.
     * Например, turnOnBoxPolicy("ONLY CHOCOLATE") позволит добавлять в коробку только шоколад.
     * А turnOnBoxPolicy("LESS THAN 100 RUB") не даст добавить в коробку ни одной сладости дороже 100 рублей.
     *
     * @param policyChoice строковая переменная для определения нужного вида фильтрации.
     */
    void turnOnBoxPolicy(String policyChoice) {
        switch (policyChoice) {
            case "ONLY CHOCOLATE":
                boxPolicy = sweets -> sweets.getClass().toString().contains("Chocolate");
                break;
            case "LESS THAN 100 RUB":
                boxPolicy = sweets -> sweets.getPrice() < 100;
                break;
            default:
                boxPolicy = sweets -> true;
                break;
        }

    }

    /**
     * Метод отключения работы политики фильтрации для коробки.
     */
    void turnOffBoxPolicy() {
        boxPolicy = sweets -> true;
    }

    /**
     * Метод явного задания политики фильтрации в виде нового лямбда-выражения
     *
     * @param boxPolicy новая политика фильтрации (как правило, в виде лямбда-выражения)
     */
    void setBoxPolicy(Predicate<Sweets> boxPolicy) {
        this.boxPolicy = boxPolicy;
    }

    /**
     * Включение/выключение отображения общей стоимости коробки в иностранной валюте
     *
     * @param priceConverted для включения передайте true, для отключения конвертации - false
     */
    void setPriceConverted(boolean priceConverted) {
        isPriceConverted = priceConverted;
    }

    /**
     * Метод явного задания иностранной валюты для дальнейшей конвертации в неё общей стоимости коробки.
     *
     * @param currency значение валюты в виде строкового значения (EUR, USD, GBP)
     */
    void setCurrency(String currency) {
        this.currency = currency;
        switch (currency) {
            case "EUR":
                rate = 0.013;
                break;
            case "USD":
                rate = 0.015;
                break;
            case "GBP":
                rate = 0.012;
                break;
            default:
                rate = 1.0;
                this.currency = "RUB";
                break;
        }

    }

    /**
     * Метод получения названия классов, входящих в коробку сладостей.
     * Имена классов будут системными (например, Chocolate вместо "Шоколад").
     * На основе Stream API.
     */
    void getClassNamesOfSweets() {
        sweetsInTheBox.stream()
                .forEach(sweets -> System.out.println(sweets.getClass().toString().substring(13)));
    }

    /**
     * Метод подсчёта количество сладостей каждого вида в коробке.
     * На основе Stream API.
     */
    void countSweetsTypeInTheBox() {
        System.out.println("Количество шоколадок в коробке = " + sweetsInTheBox.stream()
                .filter(sweets -> sweets.getClass().toString().contains("Chocolate")).count());
        System.out.println("Количество мармелада в коробке = " + sweetsInTheBox.stream()
                .filter(sweets -> sweets.getClass().toString().contains("Jellybean")).count());
        System.out.println("Количество леденцов в коробке = " + sweetsInTheBox.stream()
                .filter(sweets -> sweets.getClass().toString().contains("Lollipop")).count());
    }

    /**
     * Метод вывода всех сладостей в коробке в виде списка, стоимость которых не больше параметра limitPrice.
     * На основе Stream API.
     *
     * @param limitPrice граничная стоимость сладостей, выше которого сладости не отображаются.
     */
    void getCheapSweets(Double limitPrice) {
        sweetsInTheBox.stream().filter(sweets -> sweets.getPrice() <= limitPrice).forEach(System.out::println);
    }

    /**
     * Метод добавления сладостей в подарочную коробку.
     *
     * @param sweetsToAdd сладость для добавления из класса sweets.Sweets
     */
    void addSweetsToTheBox(Sweets sweetsToAdd) {
        if (sweetsToAdd != null & boxPolicy.test(sweetsToAdd)) {
            sweetsInTheBox.add(sweetsToAdd);
            boxPrice += sweetsToAdd.getPrice(); //увеличиваем стоимость и вес коробки по добавленной сладости
            boxWeight += sweetsToAdd.getWeight();
        }
    }

    /**
     * Метод удаления сладостей из подарочной коробки
     *
     * @param sweetsToRemove удаляемая сладость из класса sweets.Sweets
     */
    void removeSweetsFromTheBox(Sweets sweetsToRemove) {
        if (sweetsInTheBox.remove(sweetsToRemove)) { //контролируем передаваемое значение
            boxPrice -= sweetsToRemove.getPrice(); //уменьшаем стоимость и вес коробки по удаляемой сладости
            boxWeight -= sweetsToRemove.getWeight();
        }
    }

    /**
     * Вывод на консоль содержимого коробки в виде пронумерованного списка
     */
    void showBoxContent() {
        if (sweetsInTheBox.isEmpty()) {
            System.out.println("Ваша подарочная коробка пуста.");
        } else {
            System.out.println("Содержимое коробки следующее: ");
            int i = 0;
            for (Sweets s : sweetsInTheBox
            ) {
                System.out.println(++i + ". " + s);
            }
        }
    }

    /**
     * Вывод на консоль содержимого коробки в виде пронумерованного списка.
     * Данный метод также выводит общий вес и стоимость коробки с учетом её содержимого.
     */
    void showBoxContentWithPriceWeight() {
        if (sweetsInTheBox.isEmpty()) {
            System.out.println("Ваша подарочная коробка пуста.");
        } else {
            System.out.println("Содержимое коробки следующее: ");
            int i = 0;
            for (Sweets s : sweetsInTheBox
            ) {
                System.out.println(++i + ". " + s);
            }
            System.out.printf("Общий вес коробки = %.2f г.\n", boxWeight);
            if (isPriceConverted) { //если конвертация включена, то выводим стоимость в виде валютного значения
                System.out.printf("Общая стоимость коробки = %s\n", rubToCurrency.convertCurrency(boxPrice, rate, currency));
            } else {
                System.out.printf("Общая стоимость коробки = %.2f руб.\n", boxPrice);
            }
        }
    }
}


