package level1;

public class Dish {
    private final String name;
    private boolean vegan;
    private boolean glutenFree;
    private String supplement;

    public Dish(String name) {
        this.name = name;
    }

    public void markVegan() { this.vegan = true; }
    public void markGlutenFree() { this.glutenFree = true; }
    public void setSupplement(String supplement) { this.supplement = supplement; }

    public String getName() { return name; }
    public boolean isVegan() { return vegan; }
    public boolean isGlutenFree() { return glutenFree; }
    public String getSupplement() { return supplement; }
}
