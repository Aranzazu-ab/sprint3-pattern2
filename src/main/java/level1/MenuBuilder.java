package level1;

public class MenuBuilder implements StartStep {
    private Dish entrant;
    private Dish mainCourse;
    private String dessert;
    private String coffee;
    private String drink;

    @Override
    public EntrantStep withEntrant(String name) {
        this.entrant = new Dish(name);
        return new EntrantStepImpl();
    }

    @Override
    public MainCourseStep withMainCourse(String name) {
        this.mainCourse = new Dish(name);
        return new MainCourseStepImpl();
    }

    private Menu createMenu() {
        return new Menu(entrant, mainCourse, dessert, coffee, drink);
    }

    private class EntrantStepImpl implements EntrantStep {

        @Override
        public EntrantStep isVegan() {
            entrant.markVegan();
            return this;
        }

        @Override
        public EntrantStep isGlutenFree() {
            entrant.markGlutenFree();
            return this;
        }

        @Override
        public MainCourseStep withMainCourse(String name) {
            mainCourse = new Dish(name);
            return new MainCourseStepImpl();
        }
    }

    private class MainCourseStepImpl implements MainCourseStep {

        @Override
        public MainCourseStep isVegan() {
            mainCourse.markVegan();
            return this;
        }

        @Override
        public MainCourseStep isGlutenFree() {
            mainCourse.markGlutenFree();
            return this;
        }

        @Override
        public MainCourseStep withSuplement(String name) {
            mainCourse.setSupplement(name);
            return this;
        }

        @Override
        public DrinkStep withDessert(String name) {
            dessert = name;
            return new DrinkStepImpl();
        }

        @Override
        public DrinkStep withCoffee(String name) {
            coffee = name;
            return new DrinkStepImpl();
        }

        @Override
        public BuildStep withDrink(String name) {
            drink = name;
            return new BuildStepImpl();
        }

        @Override
        public Menu build() {
            return createMenu();
        }
    }

    private class DrinkStepImpl implements DrinkStep {

        @Override
        public BuildStep withDrink(String name) {
            drink = name;
            return new BuildStepImpl();
        }

        @Override
        public Menu build() {
            return createMenu();
        }
    }

    private class BuildStepImpl implements BuildStep {

        @Override
        public Menu build() {
            return createMenu();
        }
    }
}
