package nl.chris.communication;

import lombok.Getter;
import lombok.Setter;
import nl.chris.game.actor.Player;

public class MessageShot extends ClientMessage {

    @Getter
    @Setter
    private Player player;

    public MessageShot() {
        super(MessageType.HIT);
    }

    @Override
    public ServerMessage generateResponse() {
        return null;
    }
}
