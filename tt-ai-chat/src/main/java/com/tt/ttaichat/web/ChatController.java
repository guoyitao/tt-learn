package com.tt.ttaichat.web;

import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.Message;
import com.plexpt.chatgpt.listener.ConsoleStreamListener;
import com.plexpt.chatgpt.util.Proxys;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Proxy;
import java.util.Arrays;

@RestController("chat")
public class ChatController {

    @PostMapping("chat3_5")
    public Object chat3_5(){
        //国内需要代理 国外不需要
        Proxy proxy = Proxys.http("127.0.0.1", 10808);

        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                                             .apiKey("")
                                             .proxy(proxy)
                                             .timeout(900)
                                             .apiHost("https://api.openai.com/") //反向代理地址
                                             .build()
                                             .init();

        Message system = Message.ofSystem("你现在是一个诗人，专门写七言绝句");
        Message message = Message.of("写一段七言绝句诗，题目是：肌肉！");
        ConsoleStreamListener listener = new ConsoleStreamListener(){
            @Override
            public void onMsg(String message) {
                System.out.println(message);
            }
            @Override
            public void onError(Throwable throwable, String response) {
                System.out.println(response);
            }
        };
        ChatCompletion chatCompletion = ChatCompletion.builder()
                                                      .model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
                                                      .messages(Arrays.asList(system, message))
                                                      .maxTokens(3000)
                                                      .temperature(0.9)
                                                      .build();
        chatGPTStream.streamChatCompletion(chatCompletion, listener);

        return null;
    }
}
