# ChatGPT-Java
<p align='center'>
<img src="https://img.shields.io/badge/build-passing-brightgreen.svg">
<img src="https://img.shields.io/badge/platform-%20WINDOWS | MAC | LINUX%20-ff69b4.svg">
<img src="https://img.shields.io/badge/language-JAVA-orange.svg">
</p>

ChatGPT-Java 是 [ChatGPT](https://github.com/acheong08/ChatGPT) 的 Java 版本，为广大 Java 应用程序提供了 SDK 解决方案，ChatGPT-Java 允许开发人员使用由 [ChatGPT](https://github.com/acheong08/ChatGPT) 提供的逆向工程服务，从而无需翻墙即可使用 ChatGPT 的服务。

## 快速使用
### 安装
maven 仓库待建设........

### 使用

```java
import org.pumpkin.entity.AuthConfig;
import org.pumpkin.entity.ChatBot;

public class Main {
    public static void main(String[] args) {
        ChatBot bot = new ChatBot(AuthConfig.builder()
                .accessToken("your token")
                .build());

        while (true) {
            System.out.println("You:");
            String prompt = scanner.nextLine();
            String answer = bot.ask(prompt);
            System.out.println("ChatBot:");
            System.out.println(answer);
        }
    }
}
```


## 特别鸣谢
感谢 [acheong08](https://github.com/acheong08) 提供的 [ChatGPT 逆向工程解决方案](https://github.com/acheong08/ChatGPT)。