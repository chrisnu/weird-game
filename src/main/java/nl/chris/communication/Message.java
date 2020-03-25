package nl.chris.communication;

import lombok.Getter;
import lombok.Setter;

public class Message {

    @Getter
    @Setter
    private MessageType type;

    public Message(MessageType type) {
        this.type = type;
    }
}
