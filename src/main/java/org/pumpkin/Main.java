package org.pumpkin;

import org.pumpkin.config.EnvironmentConstant;
import org.pumpkin.entity.ChatBot;

import java.util.Scanner;




public class Main {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        Scanner scanner = new Scanner(System.in);
        System.setProperty(EnvironmentConstant.HOME, ".");
        while (true) {
            System.out.println("You:");
            String prompt = scanner.nextLine();
            String answer = bot.ask(prompt);
            System.out.println("ChatBot:");
            System.out.println(answer);
        }
    }
}