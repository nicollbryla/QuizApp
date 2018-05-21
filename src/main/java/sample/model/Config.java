package sample.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties properties;

    public Config(String path) throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream(path));

        Database.loadParams(properties);
    }
}
