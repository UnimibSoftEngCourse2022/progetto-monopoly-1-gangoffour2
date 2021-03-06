package com.gangoffour2.monopoly.controller;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.model.casella.Terreno;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.giocatore.Imprenditore;
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
            if (g.getPartita().getGiocatori().isEmpty()){
                PartiteRepository.getInstance().rimuoviPartitaById(g.getPartita().getId());
                g.getPartita().distruggi();
            }
        }
    }

    @MessageMapping("/partite/{id}/entra")
    public void entraInPartita(
            @Payload GiocatoreEntraRequestBody body,
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null) {
            try {
                Giocatore g;

                if(Boolean.TRUE.equals(body.getIsImprenditore())) {
                    g = Imprenditore.builder().nick(body.getNickname()).idSessione(head.getSessionId()).build();
                } else {
                    g = Giocatore.builder()
                            .nick(body.getNickname())
                            .idSessione(head.getSessionId())
                            .build();
                }

                EntraInPartita azione = EntraInPartita.builder()
                        .giocatore(g)
                        .build();
                partita.onAzioneGiocatore(azione);
                PartiteRepository.getInstance().registraGiocatore(head.getSessionId(), partita.getGiocatoreByNick(body.getNickname()));
            } catch (PartitaPienaException e) {
                Map<String, Object> headers = new HashMap<>();
                headers.put("nickname", body.getNickname());
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
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        partita.rimuoviGiocatore(giocatore);
    }

    @MessageMapping("partite/{id}/acquista")
    public void acquista(
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        AcquistaProprieta acquistaProprieta = AcquistaProprieta.builder().giocatore(giocatore).build();
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
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
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null) {
            partita.onAzioneGiocatore(lanciaDadi);
        }
    }

    @MessageMapping("/partite/{id}/ipoteca")
    public void ipoteca(@Payload Proprieta proprieta, @DestinationVariable String id, SimpMessageHeaderAccessor head) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        partita.onAzioneGiocatore(Ipoteca.builder().giocatore(giocatore).proprieta(proprieta).build());
    }

    @MessageMapping("/partite/{id}/avviaAsta")
    public void avviaAsta(@Payload Proprieta proprieta, @DestinationVariable String id, SimpMessageHeaderAccessor head) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        IPartita partita = PartiteRepository
                .getInstance()
                .getPartitaById(id);
        partita.onAzioneGiocatore(AvviaAsta.builder()
                .proprieta(proprieta)
                .giocatore(giocatore).build());
    }

    @MessageMapping("/partite/{id}/offri")
    public void offri(
            @Payload int valore,
            @DestinationVariable String id,
            SimpMessageHeaderAccessor head
    ) {
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(head.getSessionId());
        Offerta offerta = Offerta.builder().giocatore(giocatore).valore(valore).build();
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null) {
            partita.onAzioneGiocatore(offerta);
        }
    }


    @MessageMapping("/partite/{id}/terminaTurno")
    public void terminaTurno(@DestinationVariable String id, SimpMessageHeaderAccessor header){
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(header.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null) {
            partita.onAzioneGiocatore(TerminaTurno.builder().giocatore(giocatore).build());
        }
    }

    @MessageMapping("/partite/{id}/paga")
    public void pagaAffitto(@DestinationVariable String id, SimpMessageHeaderAccessor header){
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(header.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null){
            partita.onAzioneGiocatore(Paga.builder().giocatore(giocatore).build());
        }
    }


    @MessageMapping("/partite/{id}/upgradeTerreno")
    public void upgradeTerreno(@Payload Terreno terreno, @DestinationVariable String id, SimpMessageHeaderAccessor headers){
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(headers.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null){
            partita.onAzioneGiocatore(UpgradaTerreno.builder()
                    .giocatore(giocatore)
                    .terreno(terreno)
                    .build());
        }
    }

    @MessageMapping("/partite/{id}/downgradeTerreno")
    public void downgradeTerreno(@Payload Terreno terreno, @DestinationVariable String id, SimpMessageHeaderAccessor headers){
        Giocatore giocatore = PartiteRepository.getInstance().getGiocatoreByIdSessione(headers.getSessionId());
        IPartita partita = PartiteRepository.getInstance().getPartitaById(id);
        if (partita != null){
            partita.onAzioneGiocatore(DowngradaTerreno.builder()
                    .giocatore(giocatore)
                    .terreno(terreno)
                    .build());
        }
    }
}
