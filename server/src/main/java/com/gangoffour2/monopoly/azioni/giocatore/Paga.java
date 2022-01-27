package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.stati.casella.StatoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;

public class Paga extends AzioneGiocatore{
    @Override
    public void accept(StatoCasella statoCasella) {
        statoCasella.onAzioneGiocatore(this);
    }

    @Override
    public boolean accept(StatoPartita statoPartita){
        return statoPartita.onAzioneGiocatore(this);
    }

}
