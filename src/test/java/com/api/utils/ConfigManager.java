package com.api.utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop = new Properties();
    private static String path;
    private static String env;

    private ConfigManager() {
        ///  private constructor - to block object creation
    }

    static {
        /// env = System.getProperty("env");
        env = System.getProperty("env", "qa");
        env = env.toLowerCase().trim();

        switch (env) {
            case "dev" -> path = "config/config.dev.properties";
            case "qa" -> path = "config/config.qa.properties";
            case "uat" -> path = "config/config.uat.properties";
            default  -> path = "config/config.qa.properties";
        }

        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        if (input == null) {
            throw new RuntimeException("Cannot find the file at the path -----> " + path);
        }

        try {
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
