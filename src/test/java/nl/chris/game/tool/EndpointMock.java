package nl.chris.game.tool;

import nl.chris.communication.Endpoint;
import nl.chris.communication.Message;

public class EndpointMock implements Endpoint {

    private int sessionId = 0;

    @Override
    public String getSessionId() {
        return String.valueOf(sessionId);
    }

    @Override
    public void close() {
    }

    @Override
    public void sendMessage(Message message) {
    }

    public void createNewSession() {
        sessionId++;
    }
}
