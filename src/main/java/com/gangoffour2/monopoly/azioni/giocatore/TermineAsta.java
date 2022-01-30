package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.model.Asta;
import com.gangoffour2.monopoly.stati.casella.StatoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TermineAsta extends AzioneGiocatore{
    protected Asta asta;

    @Override
    public void accept(StatoCasella statoCasella) {
        statoCasella.onAzioneGiocatore(this);
    }

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneGiocatore(this);
    }
}
