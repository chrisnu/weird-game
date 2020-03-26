package nl.chris.util;

import nl.chris.communication.MessageType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageDecoderTest {
    private MessageDecoder messageDecoder;

    @BeforeEach
    public void setUp() {
        messageDecoder = new MessageDecoder();
    }

    @Test
    public void testDecodeType() {
        Assertions.assertEquals(MessageType.LOGIN, messageDecoder.decode("{type: 'LOGIN'}").getType());
        Assertions.assertEquals(MessageType.START, messageDecoder.decode("{type: 'START'}").getType());
        Assertions.assertEquals(MessageType.QUEUED, messageDecoder.decode("{type: 'QUEUED'}").getType());
        Assertions.assertEquals(MessageType.END, messageDecoder.decode("{type: 'END'}").getType());
        Assertions.assertEquals(MessageType.SHOT, messageDecoder.decode("{type: 'SHOT'}").getType());
        Assertions.assertEquals(MessageType.HIT, messageDecoder.decode("{type: 'HIT'}").getType());
    }
}
