package nl.chris.communication.server;

import nl.chris.communication.MessageType;

public class MessageStart extends ServerMessage {

    public MessageStart() {
        super(MessageType.START);
    }

}
