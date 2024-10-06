import org.junit.jupiter.api.*;
import ships.Ship;
import ships.Ships;

import java.util.ArrayList;
import java.util.List;

public class TestShips {
    @BeforeEach
    void testStarted(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + "\n============[started]============");
    }

    @AfterEach
    void testEnded() {
        System.out.println("=============[ended]=============\n");
    }

    @Nested
    @DisplayName("Test sortBySpeed")
    public class TestSortBySpeed {

        @BeforeAll
        static void testSortBySpeedStarted() {
            System.out.println("[Testing sortBySpeed started]");
        }

        @AfterAll
        static void testSortBySpeedEnded() {
            System.out.println("[Testing sortBySpeed ended]");
        }

        @Test
        @DisplayName("Test null argument: ")
        void test1() {
            Exception exceptionCollection = Assertions.assertThrows(Exception.class, () -> Ships.sortBySpeedDesc(null));
            Assertions.assertEquals("Expected list but null encountered", exceptionCollection.getMessage());
        }

        @Test
        @DisplayName("Test empty: ")
        void test2() {
            List<Ship> shipList = new ArrayList<>();
            Assertions.assertDoesNotThrow(() -> Ships.sortBySpeedDesc(shipList));
            Assertions.assertIterableEquals(new ArrayList<>(), shipList);
        }

        @Test
        @DisplayName("Test correct: ")
        void testCorrect() {
            List<Ship> shipList = new ArrayList<>();
            List<Ship> shipListExpected = new ArrayList<>();
            try {
                //To sort
                shipList.add(new Ship("name1", "type1", 10, new ArrayList<>()));
                shipList.add(new Ship("name2", "type2", 1, new ArrayList<>()));
                shipList.add(new Ship("name3", "type3", Double.POSITIVE_INFINITY, new ArrayList<>()));
                shipList.add(new Ship("name4", "type4", 100, new ArrayList<>()));
                shipList.add(new Ship("name5", "type5", 10, new ArrayList<>()));

                //Expected
                shipListExpected.add(new Ship("name3", "type3", Double.POSITIVE_INFINITY, new ArrayList<>()));
                shipListExpected.add(new Ship("name4", "type4", 100, new ArrayList<>()));
                shipListExpected.add(new Ship("name1", "type1", 10, new ArrayList<>()));
                shipListExpected.add(new Ship("name5", "type5", 10, new ArrayList<>()));
                shipListExpected.add(new Ship("name2", "type2", 1, new ArrayList<>()));
            }
            catch (Exception _) {}
            Assertions.assertDoesNotThrow(() -> Ships.sortBySpeedDesc(shipList));
            Assertions.assertIterableEquals(shipListExpected, shipList);
        }
    }

    @Nested
    @DisplayName("Test calculateAverageSpeed")
    public class TestCalculateAverageSpeed {

        @BeforeAll
        static void testCalculateAverageSpeedStarted() {
            System.out.println("[Testing calculateAverageSpeed started]");
        }

        @AfterAll
        static void testCalculateAverageAreaEnded() {
            System.out.println("[Testing calculateAverageSpeed ended]");
        }

        @Test
        @DisplayName("Test null argument: ")
        void test1() {
            Exception exception = Assertions.assertThrows(Exception.class, () -> Ships.calculateAverageSpeed(null));
            Assertions.assertEquals("Expected list but null encountered", exception.getMessage());
        }

        @Test
        @DisplayName("Test empty: ")
        void test2() {
            Exception exception = Assertions.assertThrows(Exception.class, () -> Ships.calculateAverageSpeed(new ArrayList<>()));
            Assertions.assertEquals("Trying to calculate average speed of empty list", exception.getMessage());
        }

        @Test
        @DisplayName("Test correct: ")
        void testCorrect() {
            List<Ship> shipList = new ArrayList<>();
            try {
                shipList.add(new Ship("name1", "type1", 15, new ArrayList<>()));
                shipList.add(new Ship("name2", "type2", 5, new ArrayList<>()));
                shipList.add(new Ship("name3", "type3", 25, new ArrayList<>()));
                shipList.add(new Ship("name4", "type4", 15, new ArrayList<>()));
            } catch (Exception _) {}
            double averageSpeed = Assertions.assertDoesNotThrow(() -> Ships.calculateAverageSpeed(shipList));
            Assertions.assertEquals(15, averageSpeed);
        }
    }

    @Nested
    @DisplayName("Test calculateMinimumSpeed")
    public class TestCalculateMinimumSpeed {

        @BeforeAll
        static void testCalculateMinimumSpeedStarted() {
            System.out.println("[Testing calculateMinimumSpeed started]");
        }

        @AfterAll
        static void testCalculateMinimumAreaEnded() {
            System.out.println("[Testing calculateMinimumSpeed ended]");
        }

        @Test
        @DisplayName("Test null argument: ")
        void test1() {
            Exception exception = Assertions.assertThrows(Exception.class, () -> Ships.calculateMinimumSpeed(null));
            Assertions.assertEquals("Expected list but null encountered", exception.getMessage());
        }

        @Test
        @DisplayName("Test empty: ")
        void test2() {
            Exception exception = Assertions.assertThrows(Exception.class, () -> Ships.calculateMinimumSpeed(new ArrayList<>()));
            Assertions.assertEquals("Trying to calculate minimum speed of empty list", exception.getMessage());
        }

        @Test
        @DisplayName("Test correct: ")
        void testCorrect() {
            List<Ship> shipList = new ArrayList<>();
            try {
                shipList.add(new Ship("name1", "type1", 15, new ArrayList<>()));
                shipList.add(new Ship("name2", "type2", 5, new ArrayList<>()));
                shipList.add(new Ship("name3", "type3", 25, new ArrayList<>()));
                shipList.add(new Ship("name4", "type4", 15, new ArrayList<>()));
            } catch (Exception _) {}
            double minimumSpeed = Assertions.assertDoesNotThrow(() -> Ships.calculateMinimumSpeed(shipList));
            Assertions.assertEquals(5, minimumSpeed);
        }
    }

    @Nested
    @DisplayName("Test calculateMaximumSpeed")
    public class TestCalculateMaximumSpeed {

        @BeforeAll
        static void testCalculateMaximumSpeedStarted() {
            System.out.println("[Testing calculateMaximumSpeed started]");
        }

        @AfterAll
        static void testCalculateMaximumAreaEnded() {
            System.out.println("[Testing calculateMaximumSpeed ended]");
        }

        @Test
        @DisplayName("Test null argument: ")
        void test1() {
            Exception exception = Assertions.assertThrows(Exception.class, () -> Ships.calculateMaximumSpeed(null));
            Assertions.assertEquals("Expected list but null encountered", exception.getMessage());
        }

        @Test
        @DisplayName("Test empty: ")
        void test2() {
            Exception exception = Assertions.assertThrows(Exception.class, () -> Ships.calculateMaximumSpeed(new ArrayList<>()));
            Assertions.assertEquals("Trying to calculate maximum speed of empty list", exception.getMessage());
        }

        @Test
        @DisplayName("Test correct: ")
        void testCorrect() {
            List<Ship> shipList = new ArrayList<>();
            try {
                shipList.add(new Ship("name1", "type1", 15, new ArrayList<>()));
                shipList.add(new Ship("name2", "type2", 5, new ArrayList<>()));
                shipList.add(new Ship("name3", "type3", 25, new ArrayList<>()));
                shipList.add(new Ship("name4", "type4", 15, new ArrayList<>()));
            } catch (Exception _) {}
            double maximumSpeed = Assertions.assertDoesNotThrow(() -> Ships.calculateMaximumSpeed(shipList));
            Assertions.assertEquals(25, maximumSpeed);
        }
    }
}
