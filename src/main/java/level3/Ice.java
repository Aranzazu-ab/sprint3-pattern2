package level3;

public class Ice extends BubbleTeaDecorator {
    public Ice(BubbleTea tea) {
        super(tea);
    }

    @Override
    public String getDescription() { return super.getDescription() + " + Ice"; }

    @Override
    public double getCost() { return super.getCost() + 0.25; }
}
