package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.controller.MessageBrokerSingleton;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import lombok.Builder;

@Builder
public class Lobby extends StatoPartita {
    @Override
    public boolean onAzioneGiocatore(EntraInPartita entraInPartita) throws InterruptedException {
        // Aggiorna i client e poi si rimette in attesa se non è stato raggiunto il numero
        try {
            partita.aggiungiGiocatore(entraInPartita.getGiocatore());
        } catch (GiocatoreEsistenteException e) {
            // Lancia un messaggio di errore
        }
        MessageBrokerSingleton.getInstance().getTemplate()
                .convertAndSend("/topic/partite/" + partita.getId(), partita);
        if (partita.getGiocatori().size() == partita.getConfig().getNumeroGiocatori()){
            partita.inizioPartita();
        }
        return true;
    }
}
