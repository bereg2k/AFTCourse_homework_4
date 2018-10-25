package sweets;

public class Lollipop extends Sweets {
    private String size; //размер - S, M, L или XL

    public Lollipop(String name, double weight, double price, String size) {
        super(name, weight, price);
        this.size = size;
    }

    public Lollipop(String name) {
        super(name, 15, 30);
        size = "S";
    }

    @Override
    public String toString() {
        return "Леденец [" + super.toString() + ", размер: " + size + "]";
    }
}
