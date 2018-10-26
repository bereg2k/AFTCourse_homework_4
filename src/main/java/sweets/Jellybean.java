package sweets;

/**
 * Класс, описывающий свойства сладости типа "МАРМЕЛАД".
 */
public class Jellybean extends Sweets {
    private String flavour; //вкус - клубника, малина, яблоко, банан, кола и т.д...

    public Jellybean(String name, double weight, double price, String flavour) {
        super(name, weight, price);
        this.flavour = flavour;
    }

    public enum Flavours { //enum-множество для вкусов мармелада
        STRAWBERRY, RASPBERRY, BANANA, COLA
    }

    public Jellybean(String name) {
        super(name, 40,50);
        flavour = String.valueOf(Flavours.STRAWBERRY);
    }

    @Override
    public String toString() {
        return "Мармелад [" + super.toString() + ", вкус: " + flavour + "]";
    }
}
