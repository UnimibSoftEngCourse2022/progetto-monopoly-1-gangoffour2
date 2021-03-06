package com.gangoffour2.monopoly.controller;


import com.gangoffour2.monopoly.model.IPartita;
import lombok.Builder;
import lombok.Data;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Data
@Builder
public class MessageBrokerSingleton {
    private static MessageBrokerSingleton instance;
    private final SimpMessagingTemplate template;

    public static MessageBrokerSingleton getInstance() {
        return instance;
    }

    public static void setInstance(MessageBrokerSingleton instance) {
        MessageBrokerSingleton.instance = instance;
    }

    public synchronized void broadcast(IPartita partita) {
        template.convertAndSend("/topic/partite/" + partita.getId(), partita);
    }


    public <T> void broadcast(String idPartita, String name,T oggetto){
        template.convertAndSend("/topic/partite/" + idPartita + "/" + name, oggetto);
    }
}
