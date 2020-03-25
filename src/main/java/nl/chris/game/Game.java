package nl.chris.game;

import nl.chris.communication.*;
import nl.chris.communication.client.MessageLogin;
import nl.chris.communication.server.*;
import nl.chris.game.actor.Player;
import nl.chris.game.actor.Target;
import nl.chris.game.factory.TargetFactory;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private final int MAX_PLAYERS = 2;
    private final int MAX_TARGETS = 10;
    private final int MAX_SIMULTANEOUS_TARGETS = 5;
    private final int MAX_DELAY_BETWEEN_TARGETS = 4;

    private final Queue<Player> queue = new ConcurrentLinkedQueue<>();
    private final Set<Player> livePlayers = new CopyOnWriteArraySet<>();
    private final Set<Target> targets = new CopyOnWriteArraySet<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
    private final TargetFactory targetFactory;

    private GameStatus status = GameStatus.WAIT;

    private AtomicInteger shownTargets = new AtomicInteger(0);

    {
        // Loop check the game
        executor.scheduleWithFixedDelay(this::gameCheck, 5, 3, TimeUnit.SECONDS);
    }

    public Game(TargetFactory targetFactory) {
        this.targetFactory = targetFactory;
    }

    public void closeConnection(GameEndpoint gameEndpoint) {
        Player player = new Player(gameEndpoint.getSession().getId());
        queue.remove(player);
        livePlayers.remove(player);
    }

    public void processMessage(Message message, GameEndpoint gameEndpoint) throws IOException, EncodeException {
        Player player = new Player(gameEndpoint.getSession().getId());
        if (message instanceof MessageLogin) {
            if (queue.contains(player) || livePlayers.contains(player)) {
                return;
            }

            player = ((MessageLogin) message).getPlayer();
            player.setGameEndpoint(gameEndpoint);
            player.setSession(gameEndpoint.getSession().getId());
            queue.add(player);

            MessageLoginConfirmed messageLoginConfirmed = new MessageLoginConfirmed(player);
            player.broadcast(messageLoginConfirmed);
            if (status.equals(GameStatus.WAIT)) {
                player.broadcast(new MessageWait());
            } else if (status.equals(GameStatus.PLAY)) {
                player.broadcast(new MessageQueued());
            }
        }
    }

    private void broadcast(Message message) {
        livePlayers.parallelStream().forEach(
                player -> {
                    try {
                        player.broadcast(message);
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void gameCheck() {
        switch (status) {
            case WAIT:
                if (queue.size() >= MAX_PLAYERS) {
                    for (int i = 0; i < MAX_PLAYERS; i++) {
                        livePlayers.add(queue.poll());
                    }

                    status = GameStatus.PLAY;
                    broadcast(new MessageStart());
                    executor.schedule(this::randomlyCreateTarget, new Random().nextInt(MAX_DELAY_BETWEEN_TARGETS -1) + 1, TimeUnit.SECONDS);
                }
                break;
            case PLAY:
                if (livePlayers.size() <= 1 || (shownTargets.get() >= MAX_TARGETS && targets.isEmpty())) {
                    status = GameStatus.END;
                    shownTargets.set(0);
                    broadcast(new MessageEnd());
                }
                break;
            case END:
                livePlayers.parallelStream().forEach(
                        player -> {
                            closeConnection(player.getGameEndpoint());
                            player.disconnect();
                        }
                );
                status = GameStatus.WAIT;
                break;
        }
    }

    private void randomlyCreateTarget() {
        if (shownTargets.get() >= MAX_TARGETS) {
            return;
        }

        Random random = new Random();
        int numberNewTargets = random.nextInt(Math.min(MAX_SIMULTANEOUS_TARGETS, MAX_TARGETS - shownTargets.get())) + 1;

        ArrayDeque<Target> messageTargets = new ArrayDeque<>();
        for (int i = 0; i < numberNewTargets; i++) {
            Target target = targetFactory.newTarget();
            messageTargets.add(target);
            targets.add(target);
        }

        MessageTarget message = new MessageTarget();
        message.setTargets(messageTargets);

        broadcast(message);
        int newShownTargets = shownTargets.addAndGet(numberNewTargets);

        if (newShownTargets < MAX_TARGETS) {
            executor.schedule(this::randomlyCreateTarget, random.nextInt(MAX_DELAY_BETWEEN_TARGETS -1) + 1, TimeUnit.SECONDS);
        }
    }
}
