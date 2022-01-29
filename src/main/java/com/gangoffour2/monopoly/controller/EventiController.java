package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.services.PartiteRepository;
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

    @Autowired
    public EventiController(SimpMessagingTemplate template) {
        MessageBrokerSingleton.setInstance(MessageBrokerSingleton.builder()
                .template(template)
                .build());
    }

    @EventListener
    public void onApplicationEvent(SessionConnectEvent applicationEvent) {
        // Eventuali operazioni al momento della connessione
    }

    @EventListener
    public void onApplicationEvent(SessionDisconnectEvent applicationEvent) {
        Giocatore g = PartiteRepository.getInstance().getGiocatoreByIdSessione(applicationEvent.getSessionId());
        if (g != null) {
            g.getPartita().rimuoviGiocatore(g);
            PartiteRepository.getInstance().rimuoviGiocatoreById(applicationEvent.getSessionId());
        }
    }

    @MessageMapping("/partite/{id}/entra")
    public void entraInPartita(
            @Payload String nick,
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        if (partita != null) {
            try {
                EntraInPartita azione = EntraInPartita.builder()
                        .giocatore(Giocatore.builder()
                                .nick(nick)
                                .idSessione(head.getSessionId())
                                .build())
                        .build();
                partita.onAzioneGiocatore(azione);
                PartiteRepository.getInstance().registraGiocatore(head.getSessionId(), partita.getGiocatoreByNick(nick));
            } catch (PartitaPienaException e) {
                Map<String, Object> headers = new HashMap<>();
                headers.put("nickname", nick);
                MessageBrokerSingleton.getInstance().getTemplate()
                        .convertAndSend("/topic/partite/" + id, "Partita piena", headers);
            }
        }
    }

    @MessageMapping("/partite/{id}/esci")
    public void esci(
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        partita.rimuoviGiocatore(giocatore);
    }

    @MessageMapping("partite/{id}/acquista")
    public void acquista(
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        AcquistaProprieta acquistaProprieta = AcquistaProprieta.builder().giocatore(giocatore).build();
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        if (partita != null) {
            partita.onAzioneGiocatore(acquistaProprieta);
        }
    }

    @MessageMapping("/partite/{id}/lanciaDadi")
    public void lanciaDadi(
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        LanciaDadi lanciaDadi = LanciaDadi.builder().giocatore(giocatore).build();
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        if (partita != null) {
            partita.onAzioneGiocatore(lanciaDadi);
        }
    }

    @MessageMapping("/partite/{id}/ipoteca")
    public void ipoteca(@DestinationVariable String id, SimpMessageHeaderAccessor head) {
        throw new UnsupportedOperationException();
    }

    @MessageMapping("/partite/{id}/avviaAsta")
    public void avviaAsta(@DestinationVariable String id, SimpMessageHeaderAccessor head) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        partita.onAzioneGiocatore(AvviaAsta.builder().giocatore(giocatore).build());

    }

    @MessageMapping("/partite/{id}/offri")
    public void offri(
            @Payload int valore,
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        Offerta offerta = Offerta.builder().giocatore(giocatore).valore(valore).build();
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        if (partita != null) {
            partita.onAzioneGiocatore(offerta);
        }
    }


    @MessageMapping("/partite/{id}/terminaTurno")
    public void terminaTurno(@DestinationVariable String id, SimpMessageHeaderAccessor header){
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(header.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        if (partita != null) {
            partita.onAzioneGiocatore(TerminaTurno.builder().giocatore(giocatore).build());
        }
    }

    @MessageMapping("/partite/{id}/paga")
    public void pagaAffitto(@DestinationVariable String id, SimpMessageHeaderAccessor header){
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(header.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaByid(id);
        if (partita != null){
            partita.onAzioneGiocatore(Paga.builder().giocatore(giocatore).build());
        }
    }
}
