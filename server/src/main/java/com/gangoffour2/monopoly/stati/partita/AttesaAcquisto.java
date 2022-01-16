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
    public boolean onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        System.out.println("Acquisto della proprietà");
        partita.turnoStandard();
        return true;
    }
}