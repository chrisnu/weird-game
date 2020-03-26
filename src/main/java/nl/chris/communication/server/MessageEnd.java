package nl.chris.communication.server;

import nl.chris.communication.MessageType;

public class MessageEnd extends ServerMessage {

    public MessageEnd() {
        super(MessageType.END);
    }

}
