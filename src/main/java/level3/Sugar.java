package level3;

public class Sugar extends BubbleTeaDecorator{
    public Sugar(BubbleTea tea) {
        super(tea);
    }

    @Override
    public String getDescription() { return super.getDescription() + " + Sugar"; }

    @Override
    public double getCost() { return super.getCost() + 0.30; }
}
