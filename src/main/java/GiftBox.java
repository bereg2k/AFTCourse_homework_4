import sweets.Sweets;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания свойств и методов подарочной коробки.
 * Коробка нужна для упаковки в неё сладостей из класса sweets.Sweets.
 */
class GiftBox {
    private List<Sweets> sweetsInTheBox = new ArrayList<>(); //массив сладостей
    private double boxWeight; //общий вес коробки
    private double boxPrice; //общая стоимость коробки

    GiftBox() {
    }

    List<Sweets> getSweetsInTheBox() {
        return sweetsInTheBox;
    }

    /**
     * Метод добавления сладостей в подарочную коробку.
     * @param sweetsToAdd сладость для добавления из класса sweets.Sweets
     */
    void addSweetsToTheBox(Sweets sweetsToAdd) {
        sweetsInTheBox.add(sweetsToAdd);
        boxPrice += sweetsToAdd.getPrice(); //увеличиваем стоимость и вес коробки по добавленной сладости
        boxWeight += sweetsToAdd.getWeight();
    }

    /**
     * Метод удаления сладостей из подарочной коробки
     * @param sweetsToRemove удаляемая сладсть из класса sweets.Sweets
     */
    void removeSweetsFromTheBox(Sweets sweetsToRemove) {
        if (sweetsToRemove != null & sweetsInTheBox.contains(sweetsToRemove)) { //контролируем передаваемое значение
            sweetsInTheBox.remove(sweetsToRemove);
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
            int i = 0;
            for (Sweets s : sweetsInTheBox
            ) {
                System.out.println(++i + ". " + s);
            }
            System.out.printf("Общий вес коробки = %.2f г.\n", boxWeight);
            System.out.printf("Общая стоимость коробки = %.2f руб.\n", boxPrice);
        }
    }
}
