package nl.chris.communication;

import lombok.Getter;
import lombok.Setter;
import nl.chris.game.actor.Player;

public class MessageEnd extends ServerMessage {

    @Getter
    @Setter
    private Player winner;

    public MessageEnd() {
        super(MessageType.END);
    }

}
