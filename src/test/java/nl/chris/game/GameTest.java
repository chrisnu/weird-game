package nl.chris.game;

import nl.chris.communication.client.MessageLogin;
import nl.chris.game.actor.Player;
import nl.chris.game.factory.TargetFactory;
import nl.chris.game.tool.BruteForceTargetFinder;
import nl.chris.game.tool.EndpointMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.websocket.EncodeException;
import java.io.IOException;

class GameTest {

    private Game game;
    private EndpointMock gameEndpoint;

    @BeforeEach
    void setUp() {
        game = new Game(new TargetFactory(), new BruteForceTargetFinder());
        gameEndpoint = new EndpointMock();
    }

    @Test
    void processMessage() throws IOException, EncodeException, InterruptedException {
        gameEndpoint.createNewSession();
        Player player1  = new Player(gameEndpoint.getSessionId());
        game.processMessage(new MessageLogin(player1), gameEndpoint);

        gameEndpoint.createNewSession();
        Player player2  = new Player(gameEndpoint.getSessionId());
        game.processMessage(new MessageLogin(player2), gameEndpoint);

        Assertions.assertEquals(GameStatus.WAIT, game.getStatus());
        Thread.sleep(5000);
        Assertions.assertEquals(GameStatus.PLAY, game.getStatus());

        game.closeConnection(gameEndpoint);
        Thread.sleep(5000);
        Assertions.assertEquals(GameStatus.END, game.getStatus());
    }
}
