package org.pumpkin;

import com.alibaba.fastjson2.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String configPath = home + "/.config/revChatGPT/config.json";
        Path filePath = Paths.get(configPath);

        try {
            String content = Files.readString(filePath);
            JSONObject config = JSONObject.parseObject(content);
            System.out.println(config); // 输出 JSON 格式化后的内容
        } catch (IOException e) {
            System.err.println("Error reading the config file: " + e.getMessage());
        }
    }
}