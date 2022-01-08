package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.*;

public class InizioTurno extends StatoPartita {

    synchronized void notifica(RichiediAcquisto richiediAcquisto){
        partita.setStato(AttesaAcquisto.builder().build());
    }

    @Override
    void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {

    }

    @Override
    void onAzioneGiocatore(Ipoteca ipoteca) {

    }
}
