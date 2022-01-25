package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AttesaAcquisto extends StatoPartita{

    @Override
    public void esegui(RichiediAcquisto richiediAcquisto) {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(RichiediAcquisto richiediAcquisto) {

    }

    @Override
    public boolean onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        acquistaProprieta.getGiocatore().getCasellaCorrente().onAzioneGiocatore(acquistaProprieta);
        partita.turnoStandard();
        return true;
    }


}