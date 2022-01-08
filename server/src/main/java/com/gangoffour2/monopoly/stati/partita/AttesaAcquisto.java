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
        attendiAzioneGiocatore();
        // Ora l'attributo azioneRicevuta contiene la richiesta ricevuta
        // e si può gestire.
    }

    @Override
    synchronized void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        azioneRivecuta = (AzioneGiocatore) acquistaProprieta;
        notifyAll();
    }

    @Override
    synchronized void onAzioneGiocatore(Ipoteca ipoteca) {

    }


}
