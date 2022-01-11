package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.model.PartitaObserver;
import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;


@Data
@SuperBuilder
public abstract class Casella extends EventoCasella implements SubjectStatoPartita, Serializable {

    @Builder.Default
    protected ArrayList<PartitaObserver> subscribers = new ArrayList<>();
    protected String nome;
    protected EventoCasella evento;

    protected Casella(){

    }

    /**
     * Da overrideare per i comportamenti diversi
     */
    public void inizioTurno() {
    }

    @Override
    public void notificaTutti(AzioneCasella azione){
        subscribers.forEach(subscriber -> subscriber.notifica(azione));
    }

    @Override
    public void aggiungi(PartitaObserver observer){
        subscribers.add(observer);
    }

    @Override
    public void rimuovi(PartitaObserver observer){
        subscribers.remove(observer);
    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){

    }
}
