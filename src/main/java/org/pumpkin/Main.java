package org.pumpkin;

import com.alibaba.fastjson2.JSONObject;
import org.pumpkin.entity.ChatBot;
import org.pumpkin.utils.ConfigureUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("You:");
            String prompt = scanner.nextLine();
            String answer = bot.ask(prompt);
            System.out.println("ChatBot:");
            System.out.println(answer);
        }
    }

    private static void reverseChatGpt(String config, String conversationId, String parentId) {
        ChatBot bot = new ChatBot()
                .toBuilder()
                .config(config)
                .conversationId(conversationId)
                .parentId(parentId)
                .build();
        System.out.println("You:");
        String hello = bot.ask("hello", conversationId, parentId, null, 360);
        System.out.println(hello);

    }
}