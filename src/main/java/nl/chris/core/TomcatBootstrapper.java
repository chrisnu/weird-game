package nl.chris.core;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.util.logging.Logger;

public class TomcatBootstrapper {

    private final String webappLocation;
    private final int port;
    private boolean isStarted;

    public TomcatBootstrapper(String webappLocation, int port) {
        this.webappLocation = webappLocation;
        this.port = port;
        this.isStarted = false;
    }

    public void start() throws LifecycleException {
        if (this.isStarted) {
            Logger.getGlobal().warning("Tomcat is started already");
            return;
        }

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(port);
        tomcat.addWebapp("", new File(webappLocation).getAbsolutePath());

        tomcat.start();
        this.isStarted = true;
        tomcat.getServer().await();
    }

}
