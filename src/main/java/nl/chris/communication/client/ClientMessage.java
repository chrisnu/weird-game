package nl.chris.communication.client;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;
import nl.chris.communication.server.ServerMessage;

public abstract class ClientMessage extends Message {

    public ClientMessage(MessageType type) {
        super(type);
    }

    public abstract ServerMessage generateResponse();
}
