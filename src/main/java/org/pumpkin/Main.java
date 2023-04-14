package org.pumpkin;

import com.alibaba.fastjson2.JSONObject;
import org.pumpkin.entity.ChatBot;
import org.pumpkin.utils.ConfigureUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        String config = ConfigureUtil.getConfigure();
        System.out.println(JSONObject.parseObject(config));
    }

    private void reverseChatGpt(String config, String conversationId, String parentId) {
        ChatBot bot = new ChatBot()
                .toBuilder()
                .config(config)
                .conversationId(conversationId)
                .parentId(parentId)
                .build();
        System.out.println("You:");

    }
}