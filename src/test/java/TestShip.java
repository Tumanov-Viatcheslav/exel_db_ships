import org.junit.jupiter.api.*;
import ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class TestShip {
    @BeforeEach
    void testStarted(TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName() + "\n============[started]============");
    }

    @AfterEach
    void testEnded() {
        System.out.println("=============[ended]=============\n");
    }

    @Test
    @DisplayName("Test constructor: ")
    void test1() {
        Assertions.assertDoesNotThrow(() -> new Ship("nameTest", "typeTest", 10.3, new ArrayList<>()));
    }

    @Test
    @DisplayName("Test name getter: ")
    void test2() {
        Ship ship = null;
        try {
            ship = new Ship("nameTest", "typeTest", 10.3, new ArrayList<>());
        } catch (Exception _) {}
        String name = Assertions.assertDoesNotThrow(ship::getName);
        Assertions.assertEquals("nameTest", name);
    }

    @Test
    @DisplayName("Test type getter: ")
    void test3() {
        Ship ship = null;
        try {
            ship = new Ship("nameTest", "typeTest", 10.3, new ArrayList<>());
        } catch (Exception _) {}
        String type = Assertions.assertDoesNotThrow(ship::getType);
        Assertions.assertEquals("typeTest", type);
    }

    @Test
    @DisplayName("Test type getter: ")
    void test4() {
        Ship ship = null;
        try {
            ship = new Ship("nameTest", "typeTest", 10.3, new ArrayList<>());
        } catch (Exception _) {}
        Double speed = Assertions.assertDoesNotThrow(ship::getSpeed);
        Assertions.assertEquals(10.3, speed);
    }

    @Test
    @DisplayName("Test null weapon getter: ")
    void test5() {
        Ship ship = null;
        try {
            ship = new Ship("nameTest", "typeTest", 10.3, null);
        } catch (Exception _) {}
        List<String> weapons = Assertions.assertDoesNotThrow(ship::getWeapons);
        Assertions.assertIterableEquals(null, weapons);
    }

    @Test
    @DisplayName("Test weapon getter: ")
    void test6() {
        List<String> weapons = new ArrayList<>();
        weapons.add("weapon1");
        weapons.add("weapon2");
        Ship ship = null;
        try {
            ship = new Ship("nameTest", "typeTest", 10.3, weapons);
        } catch (Exception _) {}
        weapons = Assertions.assertDoesNotThrow(ship::getWeapons);
        List<String> weaponsExpected = new ArrayList<>();
        weaponsExpected.add("weapon1");
        weaponsExpected.add("weapon2");
        Assertions.assertIterableEquals(weaponsExpected, weapons);
    }

    @Test
    @DisplayName("Test equals #1: ")
    public void test7(){
        List<String> weapons = new ArrayList<>();
        weapons.add("weapon1");
        weapons.add("weapon2");
        try {
            Ship s1 = new Ship("nameTest", "typeTest", 10.3, weapons);
            Ship s2 = new Ship("nameTest", "typeTest", 10.3, weapons);
            Assertions.assertTrue(s1.equals(s2));
            Assertions.assertTrue(s2.equals(s1));
        } catch (Exception _) {}
    }

    @Test
    @DisplayName("Test equals #2: ")
    public void test8(){
        List<String> weapons = new ArrayList<>();
        weapons.add("weapon1");
        weapons.add("weapon2");
        try {
            Ship s1 = new Ship("nameTest", "typeTest", 100.3, weapons);
            Ship s2 = new Ship("nameTest", "typeTest", 100.3, weapons);
            Assertions.assertTrue(s1.equals(s2));
            Assertions.assertTrue(s2.equals(s1));
        } catch (Exception _) {}
    }

    @Test
    @DisplayName("Test hashCode #1: ")
    public void test9() {
        List<String> weapons = new ArrayList<>();
        weapons.add("weapon1");
        weapons.add("weapon2");
        try {
            Ship s1 = new Ship("nameTest", "typeTest", 10.3, weapons);
            Ship s2 = new Ship("nameTest", "typeTest", 10.3, weapons);
            Assertions.assertEquals(s2.hashCode(), s1.hashCode());
        } catch (Exception _) {}
    }

    @Test
    @DisplayName("Test hashCode #2: ")
    public void test10() {
        List<String> weapons = new ArrayList<>();
        weapons.add("weapon1");
        weapons.add("weapon2");
        try {
            Ship s1 = new Ship("nameTest", "typeTest", 100.3, weapons);
            Ship s2 = new Ship("nameTest", "typeTest", 100.3, weapons);
            Assertions.assertEquals(s2.hashCode(), s1.hashCode());
        } catch (Exception _) {}
    }

    @Test
    @DisplayName("Test hashCode #3: ")
    public void test11() {
        List<String> weapons = new ArrayList<>();
        weapons.add("weapon1");
        weapons.add("weapon2");
        try {
            Ship s1 = new Ship("nameTest", "typeTest", 100.3, weapons);
            Ship s2 = new Ship("nameTest", "typeTest", 101.3, weapons);
            Assertions.assertNotEquals(s2.hashCode(), s1.hashCode());
        } catch (Exception _) {}
    }
}
