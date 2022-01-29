package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import lombok.Builder;

@Builder
public class Lobby extends StatoPartita {
    @Override
    public void onAzioneGiocatore(EntraInPartita entraInPartita) {
        partita.fermaAttesa();
        // Aggiorna i client e poi si rimette in attesa se non è stato raggiunto il numero
        try {
            partita.aggiungiGiocatore(entraInPartita.getGiocatore());
        } catch (GiocatoreEsistenteException e) {
            // Lancia un messaggio di errore
        }
        if (partita.getGiocatori().size() == partita.getConfig().getNumeroGiocatori()) {
            partita.inizioPartita();
        }
    }
}
