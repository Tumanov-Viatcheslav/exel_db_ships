import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.jupiter.api.*;
import ships.Ship;
import ships.ShipsLoader;

import java.util.ArrayList;
import java.util.List;

public class TestShipsLoader {
    @BeforeEach
    void testStarted(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + "\n============[started]============");
    }

    @AfterEach
    void testEnded() {
        System.out.println("=============[ended]=============\n");
    }

    @Test
    @DisplayName("Test FileNotFoundException: ")
    void test1() {
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
        Assertions.assertDoesNotThrow(() -> ShipsLoader.loadExelShips("a.xlsx"));
    }

    @Test
    @DisplayName("Test wrong number of arguments: ")
    void test2() {
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
        List<Ship> shipList = Assertions.assertDoesNotThrow(() -> ShipsLoader.loadExelShips("src/test/resources/inputWrongNumberOfArguments.xlsx"));
        List<Ship> shipListExpected = new ArrayList<>();
        try {
            List<String> weapons = new ArrayList<>();
            weapons.add("weapon1");
            weapons.add("weapon2");
            shipListExpected.add(new Ship("name4", "type4", 1, weapons));
        } catch (Exception _) {}
        Assertions.assertIterableEquals(shipListExpected, shipList);
    }

    @Test
    @DisplayName("Test wrong parameters format: ")
    void test3() {
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
        List<Ship> shipList = Assertions.assertDoesNotThrow(() -> ShipsLoader.loadExelShips("src/test/resources/inputInvalidParameters.xlsx"));
        List<Ship> shipListExpected = new ArrayList<>();
        try {
            List<String> weapons = new ArrayList<>();
            weapons.add("weapon1");
            weapons.add("weapon2");
            shipListExpected.add(new Ship("name2", "type2", 1, weapons));
        } catch (Exception _) {}
        Assertions.assertIterableEquals(shipListExpected, shipList);
    }

    @Test
    @DisplayName("Test empty: ")
    void test4() {
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
        List<Ship> shipList = Assertions.assertDoesNotThrow(() -> ShipsLoader.loadExelShips("src/test/resources/inputEmpty.xlsx"));
        List<Ship> shipListExpected = new ArrayList<>();
        Assertions.assertIterableEquals(shipListExpected, shipList);
    }

    @Test
    @DisplayName("Test SpeedException: ")
    void test5() {
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
        List<Ship> shipList = Assertions.assertDoesNotThrow(() -> ShipsLoader.loadExelShips("src/test/resources/inputSpeedException.xlsx"));
        List<Ship> shipListExpected = new ArrayList<>();
        try {
            List<String> weapons = new ArrayList<>();
            weapons.add("weapon1");
            weapons.add("weapon2");
            shipListExpected.add(new Ship("name2", "type2", 1, weapons));
        } catch (Exception _) {}
        Assertions.assertIterableEquals(shipListExpected, shipList);
    }

    @Test
    @DisplayName("Test correct: ")
    void testCorrect() {
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
        List<Ship> shipList = Assertions.assertDoesNotThrow(() -> ShipsLoader.loadExelShips("src/test/resources/inputCorrect.xlsx"));
        List<Ship> shipListExpected = new ArrayList<>();
        try {
            List<String> weapons = new ArrayList<>();
            weapons.add("weapon1");
            weapons.add("weapon2");
            shipListExpected.add(new Ship("name1", "type1", Double.POSITIVE_INFINITY, weapons));
            weapons.clear();

            weapons.add("weapon2");
            weapons.add("weapon3");
            shipListExpected.add(new Ship("name2", "type2", 5.5, weapons));
            weapons.clear();

            shipListExpected.add(new Ship("name3", "type3", 23.4, new ArrayList<>()));

            weapons.add("weapon5");
            weapons.add("weapon4");
            shipListExpected.add(new Ship("name4", "type4", 0, weapons));
        } catch (Exception _) {}
        Assertions.assertIterableEquals(shipListExpected, shipList);
    }
}
