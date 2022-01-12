package com.gangoffour2.monopoly.stati.casella;


import com.gangoffour2.monopoly.azioni.giocatore.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class EventoCasella {

    protected EventoCasella() {

    }

    public void arrivo(){

    }
    public void passaggio(){

    }
    public void fineGiro(){

    }


    public void onAzioneGiocatore(Ipoteca ipoteca){

    }

    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){

    }
}
