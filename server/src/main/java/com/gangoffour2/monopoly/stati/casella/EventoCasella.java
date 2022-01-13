package com.gangoffour2.monopoly.stati.casella;


import com.gangoffour2.monopoly.azioni.giocatore.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public abstract class EventoCasella implements Serializable {

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

    public void onAzioneGiocatore(UpgradaTerreno upgradaTerreno){

    }

    public void onAzioneGiocatore(DowngradaTerreno downgradaTerreno){

    }

    public void onAzioneGiocatore(VendiProprieta vendiProprieta){

    }
}
