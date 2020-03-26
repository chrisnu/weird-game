package nl.chris.game.actor;

import lombok.Getter;

public class Target extends Actor {

    @Getter
    private final int id;

    public Target(Coordinate coordinate, int score, int size, int id) {
        this.coordinate = coordinate;
        this.score = score;
        this.size = size;
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }

        Target target = (Target) object;
        return target.id == id;
    }

    @Override
    public int hashCode() {
        return id * 31;
    }

}
