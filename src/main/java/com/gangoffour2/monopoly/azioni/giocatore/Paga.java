package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.stati.casella.StatoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Paga extends AzioneGiocatore {
    @Override
    public void accept(StatoCasella statoCasella) {
        statoCasella.onAzioneGiocatore(this);
    }

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneGiocatore(this);
    }

}
