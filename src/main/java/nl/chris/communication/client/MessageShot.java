package nl.chris.communication.client;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.communication.server.ServerMessage;
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
