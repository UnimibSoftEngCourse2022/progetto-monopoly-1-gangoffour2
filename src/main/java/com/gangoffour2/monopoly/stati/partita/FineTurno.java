package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.TerminaTurno;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Builder;

import java.util.Optional;

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

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        Optional<Proprieta> qry = ipoteca.getGiocatore().getProprietaPossedute().stream()
                .filter(c -> c.getId() == ipoteca.getProprieta().getId()).findFirst();
        qry.ifPresent(proprieta -> proprieta.onAzioneGiocatore(ipoteca));
        partita.continua(this);
    }
}
