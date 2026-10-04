package level2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        String message = String.format(Locale.US, "Stock market went UP to %.2f", value);
        notifyObservers(message);
    }

    public void stockMarketDown (double value){
        String message = String.format(Locale.US, "Stock market went DOWN to %.2f", value);
        notifyObservers(message);
    }

}
