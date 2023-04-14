package org.pumpkin.utils;

import com.alibaba.fastjson2.JSONObject;
import org.pumpkin.config.EnvironmentConstant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConfigureUtil {
    public static String getConfigure() {
        String home = System.getProperty(EnvironmentConstant.HOME);
        String configPath = home + EnvironmentConstant.CONFIG_PATH;
        Path filePath = Paths.get(configPath);

        String content = null;

        try {
            content = Files.readString(filePath);
        } catch (IOException e) {
            System.err.println("Error reading the config file: " + e.getMessage());
        }
        return content;
    }
}
