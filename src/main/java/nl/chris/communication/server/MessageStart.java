package nl.chris.communication.server;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Player;

import java.util.Collection;

public class MessageStart extends ServerMessage {

    @Getter
    @Setter
    Collection<Player> players;

    public MessageStart() {
        super(MessageType.START);
    }

    public MessageStart(Collection<Player> players) {
        this();
        this.players = players;
    }

}
