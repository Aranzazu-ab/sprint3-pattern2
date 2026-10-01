package level1;

public interface MainCourseStep {
    MainCourseStep isVegan();
    MainCourseStep isGlutenFree();
    MainCourseStep withSuplement(String name);

    DrinkStep withDessert (String name);
    DrinkStep withCoffee (String name);
    BuildStep withDrink (String name);
    Menu build();
}
