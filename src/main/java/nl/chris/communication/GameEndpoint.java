package nl.chris.communication;

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
public class GameEndpoint implements Endpoint {

    private static Game game = new Game(new TargetFactory(), new BruteForceTargetFinder());

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

    public String getSessionId() {
        return session.getId();
    }

    public void close() throws IOException {
        session.close();
    }

    public void sendMessage(Message message) throws IOException, EncodeException {
        session.getBasicRemote().sendObject(message);
    }
}
