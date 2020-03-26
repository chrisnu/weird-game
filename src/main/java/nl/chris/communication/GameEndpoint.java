package nl.chris.communication;

import nl.chris.Application;
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

    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
    }

    @OnMessage
    public void onMessage(Session session, Message message) throws IOException, EncodeException {
        Application.getGame().processMessage(message, this);
    }

    @OnClose
    public void onClose(Session session) {
        Application.getGame().closeConnection(this);
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
