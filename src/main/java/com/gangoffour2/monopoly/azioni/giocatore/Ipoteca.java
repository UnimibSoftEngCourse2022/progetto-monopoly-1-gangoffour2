package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.stati.casella.StatoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class Ipoteca extends AzioneGiocatore {

    Proprieta proprieta;

    protected Ipoteca() {

    }

    @Override
    public void accept(StatoCasella statoCasella) {
        statoCasella.onAzioneGiocatore(this);
    }

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneGiocatore(this);
    }
}