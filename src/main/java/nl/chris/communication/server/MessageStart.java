package nl.chris.communication.server;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Player;

import java.util.Set;

public class MessageStart extends ServerMessage {

    @Getter
    @Setter
    Set<Player> players;

    public MessageStart() {
        super(MessageType.START);
    }

    public MessageStart(Set<Player> players) {
        this();
        this.players = players;
    }

}
