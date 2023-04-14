package org.pumpkin.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
