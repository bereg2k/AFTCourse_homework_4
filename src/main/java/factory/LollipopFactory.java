package factory;

import sweets.Lollipop;

/**
 * Класс фабрики по производству леденцов.
 */
public class LollipopFactory implements SweetsFactory {
    /**
     * Переопределение абстрактного метода создания сладостей.
     *
     * @param name имя для сладости
     * @return леденец, объект класса Lollipop
     */
    @Override
    public Lollipop createSweets(String name) {
        return new Lollipop(name, 15, 30, "S");
    }
}
