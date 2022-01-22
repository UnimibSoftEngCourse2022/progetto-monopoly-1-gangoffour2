package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.services.PartiteRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import java.util.HashMap;
import java.util.Map;





@Controller
public class EventiController {

    @EventListener
    public void onApplicationEvent(SessionConnectEvent applicationEvent) {
        // Eventuali operazioni al momento della connessione
    }

    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent applicationEvent) {
        Giocatore g = PartiteRespository.getInstance().getGiocatoreByIdSessione(applicationEvent.getSessionId());
        if(g != null){
            g.getPartita().rimuoviGiocatore(g);
            PartiteRespository.getInstance().rimuoviGiocatoreById(applicationEvent.getSessionId());
        }
    }

    @MessageMapping("/partite/{id}/entra")
    public void entraInPartita(@Payload String nick, @DestinationVariable String id,
                               SimpMessageHeaderAccessor head)  {
        Partita partita = PartiteRespository.getInstance().getPartitaByid(id);
        if(partita != null){
            try{
                EntraInPartita azione = EntraInPartita.builder()
                        .giocatore(Giocatore.builder()
                                .nick(nick)
                                .idSessione(head.getSessionId())
                                .build())
                        .build();
                partita.onAzioneGiocatore(azione);
                PartiteRespository.getInstance().registraGiocatore(head.getSessionId(), partita.getGiocatoreByNick(nick));
            }catch(PartitaPienaException e){
                Map<String, Object> headers = new HashMap<>();
                headers.put("nickname", nick);
                MessageBrokerSingleton.getInstance().getTemplate()
                        .convertAndSend("/topic/partite/" + id, "Partita piena", headers);
            }
        }
    }

    @MessageMapping("/partite/{id}/esci")
    public void esci(@Payload String giocatore, @DestinationVariable String id){
        Partita partita = PartiteRespository.getInstance().getPartitaByid(id);
        partita.rimuoviGiocatore(Giocatore.builder()
                .nick(giocatore)
                .build());
    }

    @MessageMapping("partite/{id}/acquista")
    public void acquista(@Payload AcquistaProprieta azione, @DestinationVariable String id) {
        Partita partita = PartiteRespository.getInstance().getPartitaByid(id);
        if (partita != null){
            partita.onAzioneGiocatore(azione);
        }
    }

    @MessageMapping("/partite/{id}/lanciaDadi")
    public void lanciaDadi(@Payload LanciaDadi lanciaDadi, @DestinationVariable String id)  {
        Partita partita = PartiteRespository.getInstance().getPartitaByid(id);
        if (partita != null){
            partita.onAzioneGiocatore(lanciaDadi);
        }
    }

    @MessageMapping("/partite/{id}/ipoteca")
    public void ipoteca(@Payload Ipoteca ipoteca, @DestinationVariable String id){
        throw new UnsupportedOperationException();
    }

    @MessageMapping("/partite/{id}/offri")
    public void offri(@Payload Offerta offerta, @DestinationVariable String id) {
        throw new UnsupportedOperationException();
    }

    @Autowired
    public EventiController(SimpMessagingTemplate template) {
        MessageBrokerSingleton.setInstance(MessageBrokerSingleton.builder()
                .template(template)
                .build());
    }
}
