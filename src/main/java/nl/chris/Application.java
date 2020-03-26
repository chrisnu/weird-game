package nl.chris;

import nl.chris.core.PropertiesLoader;
import nl.chris.core.TomcatBootstrapper;
import nl.chris.game.Game;
import nl.chris.game.factory.TargetFactory;
import nl.chris.game.tool.BruteForceTargetFinder;
import org.apache.catalina.LifecycleException;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {

    private static final String WEB_APP_LOCATION = "src/main/webapp";
    private static final String CONFIG_PROPERTIES_FILENAME = "config.properties";
    private static final String CONFIG_APP_PORT = "app.port";
    private static Game game;
    private static final String CONFIG_MAX_PLAYERS = "game.max_players";
    private static final String CONFIG_MAX_TARGETS = "game.max_targets";
    private static final String CONFIG_MAX_SIMULTANEOUS_TARGETS = "game.max_simultaneous_targets";
    private static final String CONFIG_MAX_DELAY = "game.max_delay";

    public static void main(String[] args) throws LifecycleException, IOException {
        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.load(CONFIG_PROPERTIES_FILENAME);

        int delay = Integer.parseInt(propertiesLoader.get(CONFIG_MAX_DELAY));

        game = new Game(
                new TargetFactory(),
                new BruteForceTargetFinder(),
                Integer.parseInt(propertiesLoader.get(CONFIG_MAX_PLAYERS)),
                Integer.parseInt(propertiesLoader.get(CONFIG_MAX_TARGETS)),
                Integer.parseInt(propertiesLoader.get(CONFIG_MAX_SIMULTANEOUS_TARGETS)),
                delay
        );

        executor.scheduleWithFixedDelay(() -> game.gameCheck(), delay, delay, TimeUnit.SECONDS);

        TomcatBootstrapper tomcat = new TomcatBootstrapper(WEB_APP_LOCATION, Integer.parseInt(propertiesLoader.get(CONFIG_APP_PORT)));
        tomcat.start();
    }

    public static Game getGame() {
        return game;
    }
}
