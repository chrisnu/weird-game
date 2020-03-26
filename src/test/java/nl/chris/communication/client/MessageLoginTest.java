package nl.chris.communication.client;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageLoginTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new MessageLogin();
    }

    @Test
    public void testMessageDetail() {
        Assertions.assertEquals(MessageType.LOGIN, message.getType());
    }

}
