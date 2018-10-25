package factory;

import sweets.Sweets;

/**
 * Функциональный интерфейс фабрики по производству сладостей.
 *
 * @param <S> объект класса наследника Sweets (шоколад, мармелад, леденцы)
 */
@FunctionalInterface
public interface SweetsFactory<S extends Sweets> {
    /**
     * Абстрактный метод создания сладостей по наименованию.
     *
     * @param name имя для сладости
     * @return объект-наследник класса Sweets - Chocolate, Lollipop, Jellybean
     */
    S createSweets(String name);
}
