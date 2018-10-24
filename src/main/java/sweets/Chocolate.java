package sweets;

public class Chocolate extends Sweets {
    private String chocType; //тип шоколада - тёмный, белый или молочный

    public Chocolate(String name, double weight, double price, String chocType) {
        super(name, weight, price);
        this.chocType = chocType;
    }

    public Chocolate(String s) {
        super(s, 100,70);
        chocType = "молочный";
    }

    @Override
    public String toString() {
        return "Шоколад [" + super.toString() + ", тип: " + chocType + "]";
    }
}
