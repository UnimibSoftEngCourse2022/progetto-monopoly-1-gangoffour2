package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;

@Builder
public class AcquistaProprieta extends AzioneGiocatore {


    @Override
    public void accept(EventoCasella eventoCasella) {
        eventoCasella.onAzioneGiocatore(this);
    }

    @Override
    public boolean accept(StatoPartita statoPartita) {
        return statoPartita.onAzioneGiocatore(this);
    }
}
