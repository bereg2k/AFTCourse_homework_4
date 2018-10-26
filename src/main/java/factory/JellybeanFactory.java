package factory;

import sweets.Jellybean;

/**
 * Класс фабрики по производству мармелада.
 */

public class JellybeanFactory implements SweetsFactory {
    /**
     * Переопределение абстрактного метода создания сладостей.
     *
     * @param name имя для сладости
     * @return мармелад, объект класса Jellybean
     */
    @Override
    public Jellybean createSweets(String name) {
        return new Jellybean(name, 40, 50, String.valueOf(Jellybean.Flavours.BANANA));
    }
}
