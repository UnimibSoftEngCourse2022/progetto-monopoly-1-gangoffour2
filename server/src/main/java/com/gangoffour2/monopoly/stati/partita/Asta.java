package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.Offerta;

public class Asta extends StatoPartita {
    @Override
    public boolean onAzioneGiocatore(Offerta offerta){
        return true;
    }
}
