package level1;

public interface DrinkStep {
    BuildStep withDrink(String name);
    Menu build();
}
