package nl.chris.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private Properties properties;

    public void load(String filename) throws IOException {
        if (null == filename || filename.isBlank()) {
            throw new IllegalArgumentException("Filename should be valid and not null");
        }

        InputStream input = getClass().getClassLoader().getResourceAsStream(filename);
        properties = new Properties();

        if (input == null) {
            throw new IllegalArgumentException(String.format("File %s cannot be found", filename));
        }

        properties.load(input);
    }

    public String get(String key) {
        if (properties == null) {
            throw new IllegalStateException("Properties has not been loaded yet. Run load method first");
        }

        return (String) properties.get(key);
    }
}
