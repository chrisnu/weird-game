package nl.chris.communication.server;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Target;

import java.util.ArrayDeque;

public class MessageTarget extends ServerMessage {

    @Getter
    @Setter
    private ArrayDeque<Target> targets;

    public MessageTarget() {
        super(MessageType.TARGET);
    }
}
