package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;
import com.gangoffour2.monopoly.azioni.casella.VaiInAttesaPrigione;
import lombok.Builder;

@Builder
public class InizioTurno extends StatoPartita {

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) {
        partita.setStato(LancioDadi.builder().build());
        partita.getStato().esegui();
    }

    @Override
    public void onAzioneCasella(VaiInAttesaPrigione vaiInAttesaPrigione) {
        partita.setStato(AttesaPrigione.builder().build());
        partita.getStato().esegui();
    }
}
