package com.gangoffour2.monopoly.azioni.giocatore;

import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;

public interface AzioneGiocatore {
    void accept(Casella casella);
    void accept(StatoPartita statoPartita) throws InterruptedException;
}
