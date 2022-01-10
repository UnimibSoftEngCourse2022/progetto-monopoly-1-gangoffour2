package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;

public class InizioTurno extends StatoPartita {

    @Override
    public void onAzioneCasella(RichiediAcquisto richiediAcquisto){
        partita.setStato(AttesaAcquisto.builder().build());
    }
}