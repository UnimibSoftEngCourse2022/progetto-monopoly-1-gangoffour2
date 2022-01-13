package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.controller.MessageBrokerSingleton;

public class Lobby extends StatoPartita {
    @Override
    public void onAzioneGiocatore(EntraInPartita entraInPartita) throws InterruptedException {
        // Aggiorna i client e poi si rimette in attesa se non è stato raggiunto il numero
        MessageBrokerSingleton.getInstance().getTemplate().convertAndSend(partita);
        if (partita.getGiocatori().size() == partita.getConfig().getNumeroGiocatori()){
            partita.setStato(InizioTurno.builder().build());
            partita.start();
        }
        else {
            partita.attendiAzione();
        }
    }

}
