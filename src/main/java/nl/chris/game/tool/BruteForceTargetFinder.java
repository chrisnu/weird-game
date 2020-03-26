package nl.chris.game.tool;

import nl.chris.game.actor.Coordinate;
import nl.chris.game.actor.Target;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

public class BruteForceTargetFinder implements CoordinateTargetFinder {
    private final ConcurrentLinkedDeque<Target> targets = new ConcurrentLinkedDeque<>();

    @Override
    public void registerTarget(Target target) {
        targets.add(target);
    }

    @Override
    public List<Target> hitTarget(Coordinate coordinate) {
        List<Target> foundTargets = targets.stream().filter(target -> {
            final int xStart = target.getCoordinate().getX() - (target.getSize() * 4);
            final int xEnd = target.getCoordinate().getX() + (target.getSize() * 4);
            final int yStart = target.getCoordinate().getY() - (target.getSize() * 4);
            final int yEnd = target.getCoordinate().getY() + (target.getSize() * 4);
            final int x = coordinate.getX();
            final int y = coordinate.getY();

            return x >= xStart && x <= xEnd && y >= yStart && y <= yEnd;
        }).collect(Collectors.toList());

        targets.removeAll(foundTargets);

        return foundTargets;
    }
}
