package com.gangoffour2.monopoly.stati.casella;


import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;

public interface EventoCasella {
    void arrivo();
    void passaggio();
    void fineGiro();


    default void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){

    }

    default void onAzioneGiocatore(Ipoteca ipoteca){

    }
}
