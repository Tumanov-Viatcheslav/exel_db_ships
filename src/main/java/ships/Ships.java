package ships;

import java.util.List;

public class Ships {

    public static void sortBySpeedDesc(List<Ship> ships) throws Exception {
        if (ships == null)
            throw  new Exception("Expected list but null encountered");
        if (ships.isEmpty())
            return;
        ships.sort((s1, s2) -> {
            if (s2.getSpeed() == Double.POSITIVE_INFINITY)
                return 1;
            if (s1.getSpeed() == Double.POSITIVE_INFINITY)
                return -1;
            return Double.compare(s2.getSpeed(), s1.getSpeed());
        });
    }

    public static double calculateAverageSpeed(List<Ship> ships) throws Exception {
        if (ships == null)
            throw  new Exception("Expected list but null encountered");
        if (ships.isEmpty())
            throw new Exception("Trying to calculate average speed of empty list");
        double sum = 0;
        for (Ship ship : ships) {
            sum += ship.getSpeed();
        }
        return sum / ships.size();
    }

    public static double calculateMaximumSpeed(List<Ship> ships) throws Exception {
        if (ships == null)
            throw  new Exception("Expected list but null encountered");
        if (ships.isEmpty())
            throw new Exception("Trying to calculate maximum speed of empty list");
        double max = Double.MIN_VALUE;
        for (Ship ship : ships) {
            max = Math.max(max, ship.getSpeed());
        }
        return max;
    }

    public static double calculateMinimumSpeed(List<Ship> ships) throws Exception {
        if (ships == null)
            throw  new Exception("Expected list but null encountered");
        if (ships.isEmpty())
            throw new Exception("Trying to calculate minimum speed of empty list");
        double min = Double.MAX_VALUE;
        for (Ship ship : ships) {
            min = Math.min(min, ship.getSpeed());
        }
        return min;
    }
}
