package level1;

public class MenuBuilder implements StartStep, EntrantStep, MainCourseStep, DrinkStep, BuildStep{
    private Dish entrant;
    private Dish mainCourse;
    private String dessert;
    private String coffee;
    private String drink;

    private Dish currentDish;

    @Override
    public MenuBuilder isVegan() {
        this.currentDish.markVegan();
        return this;
    }

    @Override
    public MenuBuilder isGlutenFree() {
        this.currentDish.markGlutenFree();
        return this;
    }

    @Override
    public MainCourseStep withSuplement(String name) {
        this.mainCourse.setSupplement(name);
        return this;
    }

    @Override
    public DrinkStep withDessert(String name) {
        this.dessert = name;
        return this;
    }

    @Override
    public DrinkStep withCoffee(String name) {
        this.coffee = name;
        return this;
    }

    @Override
    public BuildStep withDrink(String name) {
        this.drink = name;
        return this;
    }

    @Override
    public Menu build() {
        return new Menu(entrant, mainCourse, dessert, coffee, drink);
    }

    @Override
    public EntrantStep withEntrant(String name) {
        this.entrant= new Dish(name);
        this.currentDish = this.entrant;
        return this;
    }

    @Override
    public MainCourseStep withMainCourse(String name) {
        this.mainCourse = new Dish(name);
        this.currentDish = this.mainCourse;
        return this;
    }

}
