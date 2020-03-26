package nl.chris.game.actor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TargetTest {

    private static final int EXPECTED_SCORE = 8;
    private static final int EXPECTED_SIZE = 9;
    private static final int EXPECTED_ID = 1;
    private static final int EXPECTED_COORDINATE_X = 5;
    private static final int EXPECTED_COORDINATE_Y = 10;

    private Target target;

    @BeforeEach
    public void setUp() {
        target = new Target(new Coordinate(5, 10), 8, 9, 1);
    }

    @Test
    public void testTargetDetail() {
        Assertions.assertEquals(EXPECTED_ID, target.getId());
        Assertions.assertEquals(EXPECTED_SCORE, target.getScore());
        Assertions.assertEquals(EXPECTED_SIZE, target.getSize());
        Assertions.assertEquals(EXPECTED_COORDINATE_X, target.getCoordinate().getX());
        Assertions.assertEquals(EXPECTED_COORDINATE_Y, target.getCoordinate().getY());
    }

    @Test
    public void testHashCode() {
        Target newTarget = new Target(new Coordinate(5, 10), 8, 9, 1);
        Assertions.assertEquals(target.hashCode(), newTarget.hashCode());
    }

    @Test
    public void testEquals() {
        Target newTarget = new Target(new Coordinate(5, 10), 8, 9, 1);
        Assertions.assertEquals(target, newTarget);
    }
}
