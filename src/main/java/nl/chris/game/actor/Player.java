package nl.chris.game.actor;

import lombok.Getter;
import lombok.Setter;
import nl.chris.communication.GameEndpoint;
import nl.chris.communication.Message;

import javax.websocket.EncodeException;
import java.io.IOException;

public class Player extends Actor {

    @Getter
    @Setter
    private GameEndpoint gameEndpoint;

    public void shoot(Coordinate coordinate) {

    }

    public void disconnect() {
        try {
            gameEndpoint.getSession().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(Message message) throws IOException, EncodeException {
        this.gameEndpoint.getSession().getBasicRemote().sendObject(message);
    }
}
