package nl.chris.communication;

import javax.websocket.EncodeException;
import java.io.IOException;

public interface Endpoint {
    String getSessionId();
    void close() throws IOException;
    void sendMessage(Message message) throws IOException, EncodeException;
}
