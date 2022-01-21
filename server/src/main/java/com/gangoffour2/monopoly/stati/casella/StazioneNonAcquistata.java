package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StazioneNonAcquistata extends EventoStazione {

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        stazione.setProprietario(acquistaProprieta.getGiocatore());
        stazione.setEvento(StazioneAcquistata.builder()
                        .stazione(stazione)
                .build());
    }

    @Override
    public void arrivo() {
        stazione.notificaTutti(RichiediAcquisto.builder().build());
    }
}
