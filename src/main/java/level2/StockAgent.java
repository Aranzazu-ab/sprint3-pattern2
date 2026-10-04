package level2;

import java.util.ArrayList;
import java.util.List;

public class StockAgent {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver (Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(String message){
        for(Observer observer:observers){
            observer.uptade(message);
        }
    }

    public void stockMarketUp (double value){
        String message = String.format("Stock market went UP to %2f", value);
    }

    public void stockMarketDown (double value){
        String message = String.format("Stock market went DOWN to %.2f", value);
    }

}
