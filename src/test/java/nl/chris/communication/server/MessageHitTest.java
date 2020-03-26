package nl.chris.communication.server;

import nl.chris.communication.Message;
import nl.chris.communication.MessageType;
import nl.chris.game.actor.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class MessageHitTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        message = new MessageHit(new ArrayList<>(), new Player());
    }

    @Test
    public void testMessageDetail() {
        Assertions.assertEquals(MessageType.HIT, message.getType());
    }
}
