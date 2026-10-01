package level1;

public class Menu {
    private final Dish entrant;
    private final Dish mainCourse;
    private final String dessert;
    private final String coffee;
    private final String drink;

    public Menu(Dish entrant, Dish mainCourse, String dessert, String coffee, String drink) {
        this.entrant = entrant;
        this.mainCourse = mainCourse;
        this.dessert = dessert;
        this.coffee = coffee;
        this.drink = drink;
    }

    public Dish getEntrant() {
        return entrant;
    }

    public Dish getMainCourse() {
        return mainCourse;
    }

    public String getDessert() {
        return dessert;
    }

    public String getCoffee() {
        return coffee;
    }

    public String getDrink() {
        return drink;
    }
}
