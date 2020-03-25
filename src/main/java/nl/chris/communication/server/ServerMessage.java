package nl.chris.communication.server;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;

public abstract class ServerMessage extends Message {

    public ServerMessage(MessageType type) {
        super(type);
    }
}
