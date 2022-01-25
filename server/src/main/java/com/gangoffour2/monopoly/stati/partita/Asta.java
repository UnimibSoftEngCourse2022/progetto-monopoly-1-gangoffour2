package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import com.gangoffour2.monopoly.model.casella.Proprieta;

public class Asta extends StatoPartita {
    private Proprieta proprieta;
    @Override
    public boolean onAzioneGiocatore(Offerta offerta){
        return true;
    }
}
