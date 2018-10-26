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
    private PriceConverter rubToCurrency = new PriceConverter();

    GiftBox() {
    }

    List<Sweets> getSweetsInTheBox() {
        return sweetsInTheBox;
    }

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

    void turnOffBoxPolicy() {
        boxPolicy = sweets -> true;
    }

    void setBoxPolicy(Predicate<Sweets> boxPolicy) {
        this.boxPolicy = boxPolicy;
    }


    void setPriceConverted(boolean priceConverted) {
        isPriceConverted = priceConverted;
    }

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
            if (isPriceConverted) {
                System.out.printf("Общая стоимость коробки = %s\n", rubToCurrency.convertCurrency(boxPrice, rate, currency));
            } else {
                System.out.printf("Общая стоимость коробки = %.2f руб.\n", boxPrice);
            }

        }
    }
}


