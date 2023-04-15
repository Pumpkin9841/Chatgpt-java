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
}