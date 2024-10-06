package ships;

import java.util.List;
import java.util.Objects;

public class Ship {
    private final String name;
    private final String type;
    private final double speed;
    private final List<String> weapons;

    public Ship(String name, String type, double speed, List<String> weapons) throws Exception {
        if (speed < 0)
            throw new Exception("Speed should be positive");
        this.name = name;
        this.type = type;
        this.speed = speed;
        this.weapons = weapons == null ? null : weapons.stream().toList();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getSpeed() {
        return speed;
    }

    public List<String> getWeapons() {
        return weapons == null ? null : weapons.stream().toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship ship)) return false;
        return Double.compare(speed, ship.speed) == 0 && Objects.equals(name, ship.name) && Objects.equals(type, ship.type) && Objects.equals(weapons, ship.weapons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, speed);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", speed=" + speed +
                ", weapons=" + weapons +
                '}';
    }
}
