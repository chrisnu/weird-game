package nl.chris.communication.server;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Player;
import nl.chris.game.actor.Target;

import java.util.List;

public class MessageHit extends ServerMessage {

    @Getter
    @Setter
    private List<Target> targets;

    @Getter
    @Setter
    private Player player;

    public MessageHit(List<Target> targets, Player player) {
        super(MessageType.HIT);
        this.targets = targets;
        this.player = player;
    }
}
