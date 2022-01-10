package com.gangoffour2.monopoly.controller;


import lombok.Builder;
import lombok.Data;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Data
@Builder
public class MessageBrokerSingleton {
    private static MessageBrokerSingleton instance;
    private final SimpMessagingTemplate template;

    public static void setInstance(MessageBrokerSingleton instance){
        MessageBrokerSingleton.instance = instance;
    }

    public static MessageBrokerSingleton getInstance(){
        return instance;
    }
}
