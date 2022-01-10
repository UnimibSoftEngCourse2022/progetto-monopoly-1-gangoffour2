package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AttesaAcquisto extends StatoPartita{

    @Override
    void esegui(RichiediAcquisto richiediAcquisto) throws InterruptedException {
        partita.attendiAzione();
    }

    @Override
    public synchronized void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        // Chiama sulla casella interessata l'evento generato dal giocatore.
    }
}