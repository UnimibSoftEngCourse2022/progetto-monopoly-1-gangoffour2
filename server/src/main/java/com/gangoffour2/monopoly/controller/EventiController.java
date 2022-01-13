package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.services.FactoryPartita;
import com.gangoffour2.monopoly.services.PartiteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;


@Controller
public class EventiController {

    @MessageMapping("/partite/{id}/entra")
    public void entraInPartita(@Payload String nick, @DestinationVariable String id) {
        Partita partita = PartiteRespository.getInstance().getPartitaByid(id);
        if(partita != null){
            try{
                partita.aggiungiGiocatore(FactoryPartita.getInstance().playerFromNickname(nick, partita));
            }catch(PartitaPienaException e){
                Map<String, Object> headers = new HashMap<>();
                headers.put("nickname", nick);
                MessageBrokerSingleton.getInstance().getTemplate()
                        .convertAndSend("/topic/partite/" + id, "Partita piena", headers);
            }catch(GiocatoreEsistenteException e){
                Map<String, Object> headers = new HashMap<>();
                headers.put("nickname", nick);
                MessageBrokerSingleton.getInstance().getTemplate()
                    .convertAndSend("/topic/partite/" + id,"Nome già esistente", headers);
            }
        }
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
