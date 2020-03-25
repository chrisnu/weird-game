package nl.chris.communication;

import lombok.Getter;
import lombok.Setter;
import nl.chris.game.actor.Player;

public class MessageLogin extends ClientMessage {

    @Getter
    @Setter
    Player player;

    public MessageLogin() {
        super(MessageType.LOGIN);
    }

    @Override
    public ServerMessage generateResponse() {
        return null;
    }
}
