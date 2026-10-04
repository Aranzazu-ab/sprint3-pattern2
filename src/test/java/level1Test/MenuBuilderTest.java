package level1Test;

import level1.Menu;
import level1.MenuBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MenuBuilderTest {

    @Test
    void shouldBuildExecutiveMenu() {
        Menu executiveMenu = new MenuBuilder()
                .withEntrant("Entrant 1")
                .isVegan()
                .isGlutenFree()
                .withMainCourse("Main course 1")
                .withSuplement("Supplement 1")
                .withDessert("Dessert 1")
                .withDrink("Drink 1")
                .build();

        assertEquals("Entrant 1", executiveMenu.getEntrant().getName());
        assertTrue(executiveMenu.getEntrant().isVegan());
        assertTrue(executiveMenu.getEntrant().isGlutenFree());

        assertEquals("Main course 1", executiveMenu.getMainCourse().getName());
        assertEquals("Supplement 1", executiveMenu.getMainCourse().getSupplement());
        assertFalse(executiveMenu.getMainCourse().isVegan());
        assertFalse(executiveMenu.getMainCourse().isGlutenFree());

        assertEquals("Dessert 1", executiveMenu.getDessert());
        assertNull(executiveMenu.getCoffee());
        assertEquals("Drink 1", executiveMenu.getDrink());
    }

    @Test
    void shouldBuildKidsMenu() {
        Menu kidsMenu = new MenuBuilder()
                .withMainCourse("Main course 2")
                .withDessert("Dessert 2")
                .withDrink("Drink 2")
                .build();

        assertNull(kidsMenu.getEntrant());
        assertEquals("Main course 2", kidsMenu.getMainCourse().getName());
        assertEquals("Dessert 2", kidsMenu.getDessert());
        assertNull(kidsMenu.getCoffee());
        assertEquals("Drink 2", kidsMenu.getDrink());
    }

    @Test
    void shouldBuildHalfMenuWithCoffee() {
        Menu halfMenu = new MenuBuilder()
                .withMainCourse("Main course 3")
                .isVegan()
                .withCoffee("Coffee 1")
                .withDrink("Drink 3")
                .build();

        assertNull(halfMenu.getEntrant());
        assertEquals("Main course 3", halfMenu.getMainCourse().getName());
        assertTrue(halfMenu.getMainCourse().isVegan());
        assertFalse(halfMenu.getMainCourse().isGlutenFree());

        assertNull(halfMenu.getDessert());
        assertEquals("Coffee 1", halfMenu.getCoffee());
        assertEquals("Drink 3", halfMenu.getDrink());
    }

    @Test
    void shouldBuildMenuWithoutDrink() {
        Menu menu = new MenuBuilder()
                .withEntrant("Entrant 2")
                .withMainCourse("Main course 4")
                .withDessert("Dessert 3")
                .build(); // DrinkStep permite build() sin bebida

        assertNull(menu.getDrink());
        assertEquals("Dessert 3", menu.getDessert());
    }

    @Test
    void shouldBuildMenuWithOnlyMainCourse() {
        Menu menu = new MenuBuilder()
                .withMainCourse("Main course 5")
                .build();

        assertNotNull(menu.getMainCourse());
        assertNull(menu.getEntrant());
        assertNull(menu.getDessert());
        assertNull(menu.getCoffee());
        assertNull(menu.getDrink());
    }

    @Test
    void shouldApplyPropertiesToTheCorrectDish() {
        Menu menu = new MenuBuilder()
                .withEntrant("Entrant 3")
                .isVegan()
                .withMainCourse("Main course 6")
                .isGlutenFree()
                .build();

        assertTrue(menu.getEntrant().isVegan());
        assertFalse(menu.getEntrant().isGlutenFree());
        assertFalse(menu.getMainCourse().isVegan());
        assertTrue(menu.getMainCourse().isGlutenFree());
    }
}
