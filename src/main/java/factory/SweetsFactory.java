package factory;
import sweets.Sweets;

@FunctionalInterface
public interface SweetsFactory<S extends Sweets> {
    S createSweets(String name);
}
