package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager manager;
    private static final Properties prop = new Properties();

    private ConfigManager() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("config/config.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }

    public static ConfigManager getInstance()  {
        if (manager == null){
            synchronized (ConfigManager.class){
                try {
                    manager = new ConfigManager();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException( e );
                }
            }
        }
        return manager;
    }

    public String getString(String key){
        return System.getProperty(key, prop.getProperty(key));
    }
}