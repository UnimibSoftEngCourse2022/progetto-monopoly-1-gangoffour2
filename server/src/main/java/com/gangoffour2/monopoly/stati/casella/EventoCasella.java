package com.gangoffour2.monopoly.stati.casella;


import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class EventoCasella {

    protected EventoCasella() {

    }

    public abstract void arrivo();
    public abstract void passaggio();
    public abstract void fineGiro();

    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){

    }

    public void onAzioneGiocatore(Ipoteca ipoteca){

    }
}
