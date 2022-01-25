package com.gangoffour2.monopoly.stati.partita;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AttesaAcquisto extends StatoPartita{
    @JsonIgnore
    Proprieta daAcquistare;

    @Override
    public void onTimeout(){
        partita.setStato(Asta.builder().build());
        partita.getStato().esegui(RichiediAcquisto.builder().proprieta(daAcquistare).build());
    }


    @Override
    public void esegui(RichiediAcquisto richiediAcquisto) {
        daAcquistare = richiediAcquisto.getProprieta();
        partita.attendiAzione();
    }

    @Override
    public boolean onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        acquistaProprieta.getGiocatore().getCasellaCorrente().onAzioneGiocatore(acquistaProprieta);
        partita.turnoStandard();
        return true;
    }


}