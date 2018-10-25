package factory;

import sweets.Chocolate;

/**
 * Класс фабрики по производству шоколада.
 */
public class ChocolateFactory implements SweetsFactory {
    /**
     * Переопределение абстрактного метода создания сладостей.
     *
     * @param name имя для сладости
     * @return шоколад, объект класса Chocolate
     */
    @Override
    public Chocolate createSweets(String name) {
        return new Chocolate(name, 80, 70, "молочный");
    }
}
