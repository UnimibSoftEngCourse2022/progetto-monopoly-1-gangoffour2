package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.model.PartitaObserver;
import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;


@Data
@SuperBuilder
public abstract class Casella implements  SubjectStatoPartita, EventoCasella{
    protected ArrayList<PartitaObserver> subscribers;
    protected String nome;

    protected Casella(){
    }

    @Override
    public void notificaTutti(AzioneCasella azione){

    }

    @Override
    public void aggiungi(PartitaObserver observer){

    }

    @Override
    public void rimuovi(PartitaObserver observer){

    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){

    }


}
