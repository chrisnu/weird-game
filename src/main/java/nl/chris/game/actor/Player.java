package nl.chris.game.actor;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.Endpoint;
import nl.chris.communication.Message;

import javax.websocket.EncodeException;
import java.io.IOException;

public class Player extends Actor {

    @Getter
    @Setter
    @Expose(deserialize = false, serialize = false)
    private transient Endpoint gameEndpoint;

    @Getter
    @Setter
    private String session;

    public Player() {
        super();
    }

    public Player(String session) {
        this();
        this.session = session;
    }

    public void disconnect() {
        try {
            gameEndpoint.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Message message) throws IOException, EncodeException {
        this.gameEndpoint.sendMessage(message);
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }

        Player player = (Player) object;

        if (player.getSession() == null) {
            return false;
        }

        return player.getSession().equals(session);
    }

    @Override
    public int hashCode() {
        return session.hashCode() * 31;
    }
}
