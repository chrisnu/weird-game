package nl.chris.game.factory;

import nl.chris.game.actor.Coordinate;
import nl.chris.game.actor.Target;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class TargetFactory {

    private static AtomicInteger id = new AtomicInteger(1);

    /**
     * Generates target with random coordinate and size
     * @return Target Returns a new random target
     */
    public Target newTarget() {
        final Random random = new Random();
        final Coordinate coordinate = new Coordinate(
                random.nextInt(400) + 50,
                random.nextInt(400) + 50
        );
        final int score = random.nextInt(10) + 1;
        final int size = Math.abs(score - 11);

        return new Target(coordinate, score, size, id.getAndIncrement());
    }
}
