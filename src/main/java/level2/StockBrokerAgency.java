package level2;

public class StockBrokerAgency implements Observer{
    private String name;

    public StockBrokerAgency(String name) {
        this.name = name;
    }

    @Override
    public void uptade(String message) {
        System.out.println(name + " received notification: "+ message);
    }
}
