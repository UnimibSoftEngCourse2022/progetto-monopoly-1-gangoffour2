package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.Partita;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public abstract class StatoPartita {
    Partita partita;
    AzioneGiocatore azioneRivecuta;

    protected StatoPartita(){
    }


    void esegui(ArrestaGiocatore arrestaGiocatore){

    }

    void esegui(PagaAffitto pagaAffitto){

    }

    void esegui(PescaImprevisto pescaImprevisto){

    }

    void esegui(RichiediAcquisto richiediAcquisto) throws InterruptedException {

    }

    /**
     * Metodo che si mette in attesa di un'interazione dell'utente per il
     * completamento del turno.
     */
    synchronized void attendiAzioneGiocatore() throws InterruptedException {
        while(azioneRivecuta != null) {
            wait();
        }
    }


    abstract void onAzioneGiocatore(AcquistaProprieta acquistaProprieta);

    abstract  void onAzioneGiocatore(Ipoteca ipoteca);
}
