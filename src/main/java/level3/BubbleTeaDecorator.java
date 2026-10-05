package level3;

public class BubbleTeaDecorator implements BubbleTea{
    private BubbleTea tea;

    public BubbleTeaDecorator(BubbleTea tea) {
        this.tea = tea;
    }

    @Override
    public String getDescription() {
        return tea.getDescription();
    }

    @Override
    public double getCost() {
        return tea.getCost();
    }
}
