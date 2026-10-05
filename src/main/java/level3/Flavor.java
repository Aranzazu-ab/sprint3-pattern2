package level3;

public class Flavor extends BubbleTeaDecorator{
    private String flavor;

    public Flavor(BubbleTea tea, String flavor) {
        super(tea);
        this.flavor = flavor;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Flavor: " + flavor;
    }

    @Override
    public double getCost() { return super.getCost() + 0.60; }
}

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class BubbleTeaTest {
//
//    @Test
//    void latteConTapiocaSugarYDosSabores() {
//        BubbleTea tea = new LatteBase();
//        tea = new Tapioca(tea);
//        tea = new Sugar(tea);
//        tea = new Flavor(tea, "Maduixa");
//        tea = new Flavor(tea, "Mango");
//
//        assertEquals("Latte Bubble Tea + Tapioca + Sugar + Flavor: Maduixa + Flavor: Mango",
//                tea.getDescription());
//        assertEquals(5.50, tea.getCost(), 0.001);
//    }
//
//    @Test
//    void teaSoloConHielo() {
//        BubbleTea tea = new Ice(new TeaBase());
//        assertEquals("Tea Bubble Tea + Ice", tea.getDescription());
//        assertEquals(3.25, tea.getCost(), 0.001);
//    }
//}