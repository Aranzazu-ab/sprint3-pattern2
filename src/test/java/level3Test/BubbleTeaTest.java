package level3Test;

import level3.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleTeaTest {
    @Test
    void bubleTeaWithLatteBaseTapiocaSugarAndTwoFlavors () {
        BubbleTea tea = new LatteBase();
        tea = new Tapioca(tea);
        tea = new Sugar(tea);
        tea = new Flavor(tea, "Maduixa");
        tea = new Flavor(tea, "Mango");

        assertEquals("Latte Bubble Tea + Tapioca + Sugar + Flavor: Maduixa + Flavor: Mango",
                tea.getDescription());
        assertEquals(5.50, tea.getCost(), 0.001);
    }

    @Test
    void bubbleTeaWithIceAndTeaBase() {
        BubbleTea tea = new Ice(new TeaBase());
        assertEquals("Tea Bubble Tea + Ice", tea.getDescription());
        assertEquals(3.25, tea.getCost(), 0.001);
    }
}
