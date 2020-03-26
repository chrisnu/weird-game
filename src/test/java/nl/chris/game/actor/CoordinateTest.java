package nl.chris.game.actor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CoordinateTest {

    private static final int EXPECTED_COORDINATE_X = 10;
    private static final int EXPECTED_COORDINATE_Y = 20;

    private Coordinate coordinate;

    @BeforeEach
    public void setUp() {
        coordinate = new Coordinate(10, 20);
    }

    @Test
    public void testCoordinateDetail() {
        Assertions.assertEquals(EXPECTED_COORDINATE_X, coordinate.getX());
        Assertions.assertEquals(EXPECTED_COORDINATE_Y, coordinate.getY());
    }

    @Test
    public void testHashCode() {
        Coordinate newCoordinate = new Coordinate(10, 20);
        Assertions.assertEquals(coordinate.hashCode(), newCoordinate.hashCode());
    }

    @Test
    public void testEquals() {
        Coordinate newCoordinate = new Coordinate(10, 20);
        Assertions.assertEquals(coordinate, newCoordinate);
    }
}
