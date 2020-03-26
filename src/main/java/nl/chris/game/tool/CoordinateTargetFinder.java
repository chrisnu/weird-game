package nl.chris.game.tool;

import nl.chris.game.actor.Coordinate;
import nl.chris.game.actor.Target;

import java.util.List;

/**
 * Interface for finding target based on coordinate
 * We can have different implementations depends on the number of targets, size of canvas, number of players, etc
 * For the sake of demo purpose, we implement the brute force version since the number of targets and players are small
 */
public interface CoordinateTargetFinder {

    void registerTarget(Target target);
    List<Target> hitTarget(Coordinate coordinate);
}
