package nl.chris.game.actor;

public class Target extends Actor {

    public Target(Coordinate coordinate, int score, int size) {
        this.coordinate = coordinate;
        this.score = score;
        this.size = size;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }

        Target target = (Target) object;
        return target.coordinate.equals(coordinate) && target.score == score && target.size == size;
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode() * score * size * 31;
    }

}
