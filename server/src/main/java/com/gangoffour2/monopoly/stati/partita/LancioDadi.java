package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;

public class LancioDadi extends StatoPartita {

    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi) {
        partita.start();
    }
}
