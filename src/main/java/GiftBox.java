import sweets.Sweets;
import java.util.ArrayList;
import java.util.List;

class GiftBox {
    private List<Sweets> sweetsInTheBox = new ArrayList<>();

    public double getBoxWeight() {
        return boxWeight;
    }

    public double getBoxPrice() {
        return boxPrice;
    }

    private double boxWeight;
    private double boxPrice;

    GiftBox() {
    }

    GiftBox(List<Sweets> sweetsInTheBox) {
        this.sweetsInTheBox = sweetsInTheBox;
    }

    void addSweetsToTheBox(Sweets sweetsToAdd) {
        sweetsInTheBox.add(sweetsToAdd);
        boxPrice += sweetsToAdd.getPrice();
        boxWeight += sweetsToAdd.getWeight();
    }

    void removeSweetsFromTheBox(Sweets sweetsToRemove) {
        sweetsInTheBox.remove(sweetsToRemove);
        boxPrice -= sweetsToRemove.getPrice();
        boxWeight -= sweetsToRemove.getWeight();
    }

    @Override
    public String toString() {
        if (sweetsInTheBox.isEmpty()) {
            return "К сожалению, ваша подарочная коробка пуста.";
        } else {
            return sweetsInTheBox.toString() + "\n"
                    + "boxWeight = " + boxWeight + "\n"
                    + "boxPrice = " + boxPrice;
        }
    }
}
