package nl.chris.communication.server;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MessageTargetTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new MessageTarget();
    }

    @Test
    public void testMessageDetail() {
        Assertions.assertEquals(MessageType.TARGET, message.getType());
    }
}
