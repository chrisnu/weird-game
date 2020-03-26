package nl.chris.communication.client;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageShotTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new MessageShot();
    }

    @Test
    public void testMessageDetail() {
        Assertions.assertEquals(MessageType.SHOT, message.getType());
    }

}
