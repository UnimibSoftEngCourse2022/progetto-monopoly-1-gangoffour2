package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.model.PartitaObserver;
import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@SuperBuilder
@JsonIgnoreProperties(value = {"evento", "subscribers"})
public abstract class Casella implements SubjectStatoPartita, Serializable {

    protected String nome;
    @Builder.Default
    protected ArrayList<PartitaObserver> subscribers = new ArrayList<>();
    protected EventoCasella evento;

    public void arrivo(){
        evento.arrivo();
    }
    public void passaggio(){
        notificaTutti(evento.passaggio());
    }

    public void fineGiro(){
        evento.fineGiro();
    }

    protected Casella(){

    }

    /**
     * Da overrideare per i comportamenti diversi
     */
    public void inizioTurno() {
        notificaTutti(AttesaLancioDadi.builder().build());
    }

    @Override
    public void notificaTutti(AzioneCasella azione){
        subscribers.forEach(subscriber -> {
            try {
                subscriber.onAzioneCasella(azione);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public void aggiungi(PartitaObserver observer){
        subscribers.add(observer);
    }

    @Override
    public void rimuovi(PartitaObserver observer){
        subscribers.remove(observer);
    }


    public void onAzioneGiocatore(AzioneGiocatore azioneGiocatore){
        azioneGiocatore.accept(evento);
    }
}
