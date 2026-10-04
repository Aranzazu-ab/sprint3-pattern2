package level2Test;

import level2.Observer;
import level2.StockAgent;
import level2.StockBrokerAgency;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockAgentTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream output;
    private final String nl = System.lineSeparator();

    @BeforeEach
    public void setUp () {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void setDown () {
        System.setOut(originalOut);
    }

    @Test
    public void shouldNotifyAllObserversWhenMarketChange(){
        StockAgent agent = new StockAgent();

        Observer alphaBrokers = new StockBrokerAgency("Alpha Brokers");
        Observer zenithInvestments = new StockBrokerAgency("Zenith Investments");

        agent.addObserver(alphaBrokers);
        agent.addObserver(zenithInvestments);

        agent.stockMarketUp(150.75);
        agent.stockMarketDown(145.50);

        String expected =
                "Alpha Brokers received notification: Stock market went UP to 150.75" + nl +
                "Zenith Investments received notification: Stock market went UP to 150.75" + nl +
                "Alpha Brokers received notification: Stock market went DOWN to 145.50" + nl +
                "Zenith Investments received notification: Stock market went DOWN to 145.50" + nl;

        assertEquals(expected,output.toString());
    }

    @Test
    public void testRemovedObserverDoesNotReceiveNotifications() {
        StockAgent agent = new StockAgent();

        Observer alphaBrokers = new StockBrokerAgency("Alpha Brokers");
        Observer zenithInvestments = new StockBrokerAgency("Zenith Investments");

        agent.addObserver(alphaBrokers);
        agent.addObserver(zenithInvestments);
        agent.removeObserver(zenithInvestments);

        agent.stockMarketUp(150.75);

        String expected =
                "Alpha Brokers received notification: Stock market went UP to 150.75" + nl;

        assertEquals(expected, output.toString());
    }
}
