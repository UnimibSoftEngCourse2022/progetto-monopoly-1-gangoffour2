package com.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.Partita;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public abstract class StatoPartita {
    Partita partita;

    protected StatoPartita(){
    }


    /**
     * I metodi onAzioneCasella vengono chiamati a partire dagli
     * stati delle caselle per capire che azione è necessaria nella partita, se necessaria.
     */

    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore){

    }


    public void onAzioneCasella(PagaAffitto pagaAffitto){

    }

    public void onAzioneCasella(PescaImprevisto pescaImprevisto){

    }

    public void onAzioneCasella(RichiediAcquisto richiediAcquisto){

    }

    /**
     * I metodi esegui vengono chiamati dalla partita sulla casella: ad esempio, prima di attendere il lancio dei dadi,
     * sarà necessario un cambio di stato attraverso onAzioneCasella; poi, il nuovo stato si potrà effettivamente
     * mettere in attesa del lancio dei dadi.
     */
    void esegui(ArrestaGiocatore arrestaGiocatore){

    }

    void esegui(PagaAffitto pagaAffitto){

    }

    void esegui(PescaImprevisto pescaImprevisto){

    }

    void esegui(RichiediAcquisto richiediAcquisto) throws InterruptedException {

    }


    /**
     * Di default, gli eventi arrivati dall'esterno non sbloccano la partita.
     * Ogni stato ridefinirà il suo comportamento in base al tipo dell'azione.
     */
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) throws InterruptedException {
        partita.attendiAzione();
    }

    public  void onAzioneGiocatore(Ipoteca ipoteca) throws InterruptedException {
        partita.attendiAzione();
    }
}