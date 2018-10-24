package factory;
import sweets.Sweets;

public interface ChocolateFactory extends SweetsFactory {
    @Override
    Sweets createSweets(String name);
}
