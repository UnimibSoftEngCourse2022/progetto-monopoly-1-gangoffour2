package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;



@Controller
public class EventiController {



    @MessageMapping("/partite/{id}/entra")
    public void entraInPartita(@Payload String message) {

    }

    @MessageMapping("/partite/{id}/esci")
    public void esci(@Payload String giocatore){

    }

    @MessageMapping("partite/{id}/acquista")
    public void acquista(@Payload AcquistaProprieta azione){

    }

    @MessageMapping("/partite/{id}/lanciaDadi")
    public void lanciaDadi(){

    }

    @MessageMapping("/partite/{id}/ipoteca")
    public void ipoteca(@Payload Ipoteca ipoteca){

    }

    @MessageMapping("/partite/{id}/offri")
    public void offri(@Payload Offerta offerta) {

    }

    @Autowired
    public EventiController(SimpMessagingTemplate template) {
        MessageBrokerSingleton.setInstance(MessageBrokerSingleton.builder()
                .template(template)
                .build());
    }
}
