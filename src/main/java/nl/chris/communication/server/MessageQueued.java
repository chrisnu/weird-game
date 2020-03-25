package nl.chris.communication.server;

import nl.chris.communication.MessageType;

public class MessageQueued extends ServerMessage {

    public MessageQueued() {
        super(MessageType.QUEUED);
    }

}
