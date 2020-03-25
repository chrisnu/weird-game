package nl.chris.game.actor;

import java.util.Random;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate getRandom() {
        Random random = new Random();
        return new Coordinate(
                random.nextInt(400) + 50,
                random.nextInt(400) + 50
        );
    }
}
