package nl.chris.communication.server;

import nl.chris.communication.MessageType;

public class MessageWait extends ServerMessage
{

    public MessageWait() {
        super(MessageType.WAIT);
    }

}
