package nl.chris.game.actor;

import lombok.Data;

@Data
public class Actor {
    protected String name;
    protected int score;
    protected int size;
    protected Coordinate coordinate;
}
