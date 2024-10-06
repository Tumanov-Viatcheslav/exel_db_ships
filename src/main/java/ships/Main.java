package ships;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.spi.LoggingEvent;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure(new AppenderSkeleton() {
            @Override
            protected void append(LoggingEvent event) {

            }

            @Override
            public void close() {

            }

            @Override
            public boolean requiresLayout() {
                return false;
            }
        });
        List<Ship> shipList = ShipsLoader.loadExelShips("src/main/resources/input.xlsx");
        System.out.println(shipList);
    }
}