package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.model.casella.Terreno;
import com.gangoffour2.monopoly.stati.casella.StatoCasella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UpgradaTerreno extends AzioneGiocatore {

    protected Terreno terreno;

    @Override
    public void accept(StatoCasella statoCasella) {
        statoCasella.onAzioneGiocatore(this);
    }

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneGiocatore(this);
    }
}
