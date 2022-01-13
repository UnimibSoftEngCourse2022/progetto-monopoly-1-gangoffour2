package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;

public class InizioTurno extends StatoPartita {
    @Override
    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi){
        partita.setStato(LancioDadi.builder().build());
    }

    @Override
    public void onAzioneCasella(AttesaPrigione attesaPrigione){
        partita.setStato(AttesaPrigione.builder().build());
    }
}
