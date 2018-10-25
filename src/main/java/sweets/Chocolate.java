package sweets;

public class Chocolate extends Sweets {
    private String chocType; //тип шоколада - тёмный, белый или молочный

    public Chocolate(String name, double weight, double price, String chocType) {
        super(name, weight, price);
        this.chocType = chocType;
    }

    public Chocolate(String name) {
        super(name, 70, 80);
        chocType = "молочный";
    }

    @Override
    public String toString() {
        return "Шоколад [" + super.toString() + ", тип: " + chocType + "]";
    }
}
