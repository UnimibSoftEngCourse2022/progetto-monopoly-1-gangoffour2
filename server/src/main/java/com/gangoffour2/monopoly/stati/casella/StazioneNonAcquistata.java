package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StazioneNonAcquistata extends StatoStazione {

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        acquistaProprieta.getGiocatore().acquistaProprieta(stazione);
        stazione.setEvento(StazioneAcquistata.builder()
                .stazione(stazione)
                .build());
    }

    @Override
    public AzioneCasella arrivo() {
        return RichiediAcquisto.builder().build();
    }
}
