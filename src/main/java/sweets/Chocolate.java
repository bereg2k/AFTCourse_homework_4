package sweets;

/**
 * Класс, описывающий свойства сладости типа "ШОКОЛАД".
 */
public class Chocolate extends Sweets {
    private String chocType; //тип шоколада - тёмный, белый или молочный

    public enum ChocolateTypes { //enum-множество для типов шоколада
        MILK, DARK, WHITE
    }
    public Chocolate(String name, double weight, double price, String chocType) {
        super(name, weight, price);
        this.chocType = chocType;
    }

    public Chocolate(String name) {
        super(name, 70, 80);
        chocType = String.valueOf(ChocolateTypes.MILK);
    }

    @Override
    public String toString() {
        return "Шоколад [" + super.toString() + ", тип: " + chocType + "]";
    }
}
