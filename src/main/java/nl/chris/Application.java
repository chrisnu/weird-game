package nl.chris;

import nl.chris.core.PropertiesLoader;
import nl.chris.core.TomcatBootstrapper;
import org.apache.catalina.LifecycleException;

import java.io.IOException;

public class Application {

    private static final String WEB_APP_LOCATION = "src/main/webapp";
    private static final String CONFIG_PROPERTIES_FILENAME = "config.properties";
    private static final String CONFIG_APP_PORT = "app.port";

    public static void main(String[] args) throws LifecycleException, IOException {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.load(CONFIG_PROPERTIES_FILENAME);

        TomcatBootstrapper tomcat = new TomcatBootstrapper(WEB_APP_LOCATION, Integer.parseInt(propertiesLoader.get(CONFIG_APP_PORT)));
        tomcat.start();
    }
}
