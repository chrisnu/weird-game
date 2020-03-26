package nl.chris.game.actor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
    private static final String EXPECTED_SESSION_ID = "session1";
    private static final String EXPECTED_NAME = "Spartans";
    private static final int EXPECTED_SCORE = 5;
    private static final int EXPECTED_SIZE = 4;

    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("session1");
        player.setName("Spartans");
        player.setScore(5);
        player.setSize(4);
    }

    @Test
    public void testPlayerDetail() {
        Assertions.assertEquals(EXPECTED_SESSION_ID, player.getSession());
        Assertions.assertEquals(EXPECTED_NAME, player.getName());
        Assertions.assertEquals(EXPECTED_SCORE, player.getScore());
        Assertions.assertEquals(EXPECTED_SIZE, player.getSize());
    }
}
