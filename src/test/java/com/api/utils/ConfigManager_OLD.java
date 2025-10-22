package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager_OLD {
    private ConfigManager_OLD(){
        ///  private constructor
    }

    private static Properties prop = new Properties();

    static {
        File configFile = new File(
                System.getProperty("user.dir") + File.separator +"src"+ File.separator +"test"+ File.separator +"resources"
                        + File.separator +"config"+ File.separator +"config.uat.properties");
        /// --- Using file separator So we can get path on windows or mac or linux
        /// --- windows uses - \
        /// ---Mac uses      - /

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configFile);
            prop.load(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        return prop.getProperty(key);
    }
}
