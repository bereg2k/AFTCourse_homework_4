package sweets;

/**
 * Класс, описывающий свойства сладости типа "ЛЕДЕНЕЦ".
 */
public class Lollipop extends Sweets {
    private String size; //размер - S, M, L или XL

    public enum LollipopSizes { //enum-множество для размеров леденцов
        S, M, L, XL
    }

    public Lollipop(String name, double weight, double price, String size) {
        super(name, weight, price);
        this.size = size;
    }

    public Lollipop(String name) {
        super(name, 15, 30);
        size = String.valueOf(LollipopSizes.S);
    }

    @Override
    public String toString() {
        return "Леденец [" + super.toString() + ", размер: " + size + "]";
    }
}
