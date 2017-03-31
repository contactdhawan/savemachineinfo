package com.murdock.infotech.readipaddress;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Configuration {

    private static Boolean configFileOnClassPath = false;
    public static Config getConfiguration(){
        Config config = ConfigFactory.load();
        configFileOnClassPath = false;
        try{

            configFileOnClassPath = "true".equals(config.getString("savemachineinfo"));

        } catch (Exception e){ //we didn't find any application.conf files on the classpath (example, standalone jar)

            File configFile = new File("/Applications/savemachineinfo/application.conf");
            config = ConfigFactory.parseFile(configFile);
        }
        return config;
    }

    public static Boolean isConfigFileOnClassPath(){
        return configFileOnClassPath;
    }
}

