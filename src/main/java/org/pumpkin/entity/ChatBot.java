package org.pumpkin.entity;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pumpkin.config.EnvironmentConstant;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ChatBot {
    /**
     * 配置文件(包括 access_token)
     */
    private String config;

    /**
     * 会话id
     */
    private String conversationId;

    /**
     * 上下文id
     */
    private String parentId;

    Log log = LogFactory.get();

    /**
     * 向gpt提问
     * @param prompt 问题内容
     * @param conversationId  UUID 会话id 用于继续上一次对话 默认为 null
     * @param parentId UUID 上下文id 用于保持上下文 默认为 null
     * @param model 模型名称 比如(gpt-4) 默认为 null
     * @param timeout 超时时间 默认为 360s
     * @return
     */
    public String ask(String prompt, String conversationId, String parentId, String model, Integer timeout) {

        if(StrUtil.isBlank(conversationId) && StrUtil.isBlank(parentId)) {
            parentId = UUID.randomUUID().toString();
            log.debug("New conversation, setting parent_id to new UUID4: {}", parentId);
        }

        String finalParentId = parentId;
        List<HashMap<String, Object>> messages = new ArrayList<>(){{
           add(new HashMap<String, Object>(){{
               put("id", UUID.randomUUID().toString());
               put("role", "user");
               put("author", new HashMap<String, String>(){{
                   put("role", "user");
               }});
               put("content", new HashMap<String, Object>(){{
                   put("content_type", "text");
                   put("parts", new ArrayList<>(){{
                       add(prompt);
                   }});
               }});
           }});
        }};
        HashMap<String, Object> data = new HashMap<>(){{
            put("action", "next");
            put("conversation_id", conversationId);
            put("parent_message_id", finalParentId);
            put("model", getModel(model));
            put("messages", messages);
        }};

        log.debug("Sending the payload");
        log.debug(JSONUtil.toJsonPrettyStr(data));

        String accessToken = JSONUtil.parseObj(config).get("access_token").toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(EnvironmentConstant.BASE_URL))
                .header(Header.ACCEPT.getValue(), "text/event-stream")
                .header(Header.AUTHORIZATION.getValue(), "Bearer " + accessToken)
                .header(Header.CONTENT_TYPE.getValue(), "application/json")
                .header("X-Openai-Assistant-App-Id", "")
                .header(Header.ACCEPT_LANGUAGE.getValue(), "en-US,en;q=0.9")
                .header(Header.REFERER.getValue(), "https://chat.openai.com/chat")
                .timeout(timeout == null ? EnvironmentConstant.TIMEOUT : Duration.ofSeconds(timeout))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(JSONUtil.toJsonStr(data)))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            java.net.http.HttpResponse<String> send = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
            System.out.println(send.body());
            return send.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getModel(String model) {
        JSONObject jsonConfig = JSONObject.parseObject(config);
        return model = model != null ? model
                : jsonConfig.get("model") != null ? jsonConfig.getString("model").toString()
                : Boolean.TRUE.equals(jsonConfig.get("paid")) ? "text-davinci-002-render-paid"
                : "text-davinci-002-render-sha";
    }

    public String ask(String prompt) {
        return this.ask(prompt,36000);
    }

    public String ask(String prompt, Integer timeout) {
        return this.ask(prompt, null , null, null, timeout);
    }
}
