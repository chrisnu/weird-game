package nl.chris.game.tool;

import nl.chris.game.actor.Coordinate;
import nl.chris.game.actor.Target;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BruteForceTargetFinderTest {

    private BruteForceTargetFinder bruteForceTargetFinder;

    @BeforeEach
    void setUp() {
        bruteForceTargetFinder = new BruteForceTargetFinder();

        Target target1 = new Target(new Coordinate(10, 20), 1, 4, 1);
        Target target2 = new Target(new Coordinate(40, 60), 1, 6, 2);
        Target target3 = new Target(new Coordinate(44, 60), 1, 8, 3);
        bruteForceTargetFinder.registerTarget(target1);
        bruteForceTargetFinder.registerTarget(target2);
        bruteForceTargetFinder.registerTarget(target3);
    }

    @Test
    void hitTarget() {
        Assertions.assertEquals(1, bruteForceTargetFinder.hitTarget(new Coordinate(12, 20)).size());
        Assertions.assertEquals(0, bruteForceTargetFinder.hitTarget(new Coordinate(12, 20)).size());
        Assertions.assertEquals(2, bruteForceTargetFinder.hitTarget(new Coordinate(43, 60)).size());
    }
}
