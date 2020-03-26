package nl.chris.game;

import lombok.Getter;
import nl.chris.communication.Endpoint;
import nl.chris.communication.Message;
import nl.chris.communication.client.MessageLogin;
import nl.chris.communication.client.MessageShot;
import nl.chris.communication.server.*;
import nl.chris.game.actor.Player;
import nl.chris.game.actor.Target;
import nl.chris.game.factory.TargetFactory;
import nl.chris.game.tool.CoordinateTargetFinder;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {

    private final int maxPlayers;
    private final int maxTargets;
    private final int maxSimultaneousTargets;
    private final int maxDelayBetweenTargets;

    @Getter
    private final Queue<Player> queue = new ConcurrentLinkedQueue<>();

    @Getter
    private final Map<String, Player> livePlayers = new ConcurrentHashMap<>();
    private final Set<Target> targets = new CopyOnWriteArraySet<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
    private final TargetFactory targetFactory;
    private final CoordinateTargetFinder coordinateFinder;

    @Getter
    private GameStatus status = GameStatus.WAIT;

    private AtomicInteger shownTargets = new AtomicInteger(0);

    public Game(TargetFactory targetFactory, CoordinateTargetFinder coordinateFinder, int maxPlayers, int maxTargets, int maxSimTargets, int maxDelay) {
        this.targetFactory = targetFactory;
        this.coordinateFinder = coordinateFinder;
        this.maxPlayers = maxPlayers;
        this.maxTargets = maxTargets;
        this.maxSimultaneousTargets = maxSimTargets;
        this.maxDelayBetweenTargets = maxDelay;
    }

    public void closeConnection(Endpoint gameEndpoint) {
        String session = gameEndpoint.getSessionId();
        Player player = new Player(session);
        queue.remove(player);
        livePlayers.remove(session);
    }

    public void processMessage(Message message, Endpoint gameEndpoint) throws IOException, EncodeException {
        if (message instanceof MessageLogin) {
            handleLoginMessage(message, gameEndpoint);
        } else if (message instanceof MessageShot) {
            handleShotMessage(message, gameEndpoint);
        }
    }

    private void broadcast(Message message) {
        livePlayers.values().parallelStream().forEach(
                player -> {
                    try {
                        player.broadcast(message);
                    } catch (IOException | EncodeException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public void gameCheck() {
        switch (status) {
            case WAIT:
                if (queue.size() >= maxPlayers) {
                    for (int i = 0; i < maxPlayers; i++) {
                        Player player = queue.poll();
                        if (player != null) {
                            livePlayers.put(player.getSession(), player);
                        }
                    }

                    targets.clear();
                    status = GameStatus.PLAY;
                    broadcast(new MessageStart(livePlayers.values()));
                    executor.schedule(this::randomlyCreateTarget, new Random().nextInt(maxDelayBetweenTargets -1) + 1, TimeUnit.SECONDS);
                }
                break;
            case PLAY:
                if (livePlayers.size() <= 1 || (shownTargets.get() >= maxTargets && targets.isEmpty())) {
                    status = GameStatus.END;
                    shownTargets.set(0);
                    broadcast(new MessageEnd());
                }
                break;
            case END:
                livePlayers.values().forEach(
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
        if (shownTargets.get() >= maxTargets) {
            return;
        }

        Random random = new Random();
        int numberNewTargets = random.nextInt(Math.min(maxSimultaneousTargets, maxTargets - shownTargets.get())) + 1;

        ArrayDeque<Target> messageTargets = new ArrayDeque<>();
        for (int i = 0; i < numberNewTargets; i++) {
            Target target = targetFactory.newTarget();
            messageTargets.add(target);
            targets.add(target);
            coordinateFinder.registerTarget(target);
        }

        MessageTarget message = new MessageTarget();
        message.setTargets(messageTargets);

        broadcast(message);
        int newShownTargets = shownTargets.addAndGet(numberNewTargets);

        if (newShownTargets < maxTargets) {
            executor.schedule(this::randomlyCreateTarget, random.nextInt(maxDelayBetweenTargets -1) + 1, TimeUnit.SECONDS);
        }
    }

    private void handleLoginMessage(Message message, Endpoint gameEndpoint) throws IOException, EncodeException {
        String session = gameEndpoint.getSessionId();
        Player player = new Player(session);
        if (queue.contains(player) || livePlayers.containsKey(session)) {
            return;
        }

        player = ((MessageLogin) message).getPlayer();
        if (player == null) {
            throw new IllegalArgumentException("Message does not contain player");
        }

        player.setGameEndpoint(gameEndpoint);
        player.setSession(session);
        queue.add(player);

        MessageLoginConfirmed messageLoginConfirmed = new MessageLoginConfirmed(player);
        player.broadcast(messageLoginConfirmed);
        if (status.equals(GameStatus.WAIT)) {
            player.broadcast(new MessageWait());
        } else {
            player.broadcast(new MessageQueued());
        }
    }

    private void handleShotMessage(Message message, Endpoint gameEndpoint) {
        String session = gameEndpoint.getSessionId();
        List<Target> hitTargets = coordinateFinder.hitTarget(((MessageShot) message).getPlayer().getCoordinate());

        if (hitTargets.isEmpty()) {
            return;
        }

        targets.removeAll(hitTargets);

        Player shootingPlayer = livePlayers.get(session);
        hitTargets.forEach(
            target -> shootingPlayer.setScore(shootingPlayer.getScore() + target.getScore())
        );

        MessageHit messageHit = new MessageHit(hitTargets, shootingPlayer);
        broadcast(messageHit);
    }
}
