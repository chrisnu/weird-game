package nl.chris.communication.client;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Player;

public class MessageShot extends ClientMessage {

    @Getter
    @Setter
    private Player player;

    public MessageShot() {
        super(MessageType.SHOT);
    }

}
