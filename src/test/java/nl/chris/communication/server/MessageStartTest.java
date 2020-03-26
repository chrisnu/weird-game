package nl.chris.communication.server;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MessageStartTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new MessageStart();
    }

    @Test
    public void testMessageDetail() {
        Assertions.assertEquals(MessageType.START, message.getType());
    }
}
