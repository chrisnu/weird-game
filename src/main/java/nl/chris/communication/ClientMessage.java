package nl.chris.communication;

public abstract class ClientMessage extends Message {

    public ClientMessage(MessageType type) {
        super(type);
    }

    public abstract ServerMessage generateResponse();
}
