package nl.chris.communication.client;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.communication.server.ServerMessage;
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
