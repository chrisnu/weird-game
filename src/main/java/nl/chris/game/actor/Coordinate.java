package nl.chris.game.actor;

import lombok.Getter;

/**
 * Coordinate representation to be used for shot coordinate and target coordinate
 */
public class Coordinate {
    @Getter
    private final int x;

    @Getter
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }

        Coordinate coordinate = (Coordinate) object;
        return coordinate.x == x && coordinate.y == y;
    }

    @Override
    public int hashCode() {
        return (("x".hashCode() * x) + ("y".hashCode() * y)) * 31;
    }
}
