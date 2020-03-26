package nl.chris.communication;

import lombok.Getter;
import nl.chris.game.Game;
import nl.chris.game.factory.TargetFactory;
import nl.chris.game.tool.BruteForceTargetFinder;
import nl.chris.util.MessageDecoder;
import nl.chris.util.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(
        value = "/game",
        decoders = MessageDecoder.class,
        encoders = MessageEncoder.class
)
public class GameEndpoint {

    private static Game game = new Game(new TargetFactory(), new BruteForceTargetFinder());

    @Getter
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        game.processMessage(message, this);
    }

    @OnClose
    public void onClose(Session session) {
        game.closeConnection(this);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }
}
