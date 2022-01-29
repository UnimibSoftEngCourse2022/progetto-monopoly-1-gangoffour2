package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.TerminaTurno;
import lombok.Builder;

@Builder
public class FineTurno extends StatoPartita {

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onTimeout() {
        partita.cambiaTurno();
    }

    @Override
    public void esegui(){
        partita.attendiAzione();
    }

    @Override
    public void onAzioneGiocatore(TerminaTurno terminaTurno){
        partita.fermaAttesa();
        partita.cambiaTurno();
    }
}
