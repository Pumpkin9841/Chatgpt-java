package org.pumpkin.config;

import java.time.Duration;

public class EnvironmentConstant {
    public static final String HOME = "user.home";

    public static final String CONFIG_PATH = "/.config/revChatGPT/config.json";

    public static final String BASE_URL = "https://bypass.churchless.tech/api/conversation";

    public static final Duration TIMEOUT = Duration.ofSeconds(360);
}
