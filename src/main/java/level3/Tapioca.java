package level3;

public class Tapioca extends BubbleTeaDecorator{
    public Tapioca(BubbleTea tea) {
        super(tea);
    }

    @Override
    public String getDescription() { return super.getDescription() + " + Tapioca"; }

    @Override
    public double getCost() { return super.getCost() + 0.50; }
}
