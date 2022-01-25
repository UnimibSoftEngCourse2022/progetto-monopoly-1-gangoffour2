package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.stati.casella.EventoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Offerta extends AzioneGiocatore {

    int valore;

    @Override
    public void accept(EventoCasella eventoCasella) {
        eventoCasella.onAzioneGiocatore(this);
    }

    @Override
    public boolean accept(StatoPartita statoPartita) {
        return statoPartita.onAzioneGiocatore(this);
    }


    public boolean isValida() {
        return valore > giocatore.getConto();
    }

}
