package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class EntraInPartita extends AzioneGiocatore{

    @Override
    public void accept(EventoCasella eventoCasella) {
        eventoCasella.onAzioneGiocatore(this);
    }

    @Override
    public synchronized boolean accept(StatoPartita statoPartita) {
        return statoPartita.onAzioneGiocatore(this);
    }
}
