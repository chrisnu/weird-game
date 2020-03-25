package nl.chris.communication.server;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Player;

public class MessageLoginConfirmed extends ServerMessage
{

    @Getter
    @Setter
    private Player player;

    public MessageLoginConfirmed() {
        super(MessageType.LOGIN_CONFIRMED);
    }

    public MessageLoginConfirmed(Player player) {
        this();
        this.player = player;
    }

}
