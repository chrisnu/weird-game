package nl.chris.communication;

import lombok.Getter;
import lombok.Setter;
import nl.chris.game.actor.Player;
import nl.chris.game.actor.Target;

import java.util.ArrayList;
import java.util.List;

public class MessageHit extends ServerMessage {

    @Getter
    @Setter
    private List<Target> targets;

    @Getter
    @Setter
    private Player player;

    public MessageHit() {
        super(MessageType.HIT);
        this.targets = new ArrayList<>();
    }

    public void addTarget(Target target) {
        targets.add(target);
    }
}
