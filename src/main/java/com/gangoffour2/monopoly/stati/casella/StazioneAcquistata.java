package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PagaAffitto;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StazioneAcquistata extends StatoStazione {

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        stazione.setStato(StazioneIpotecata.builder()
                .stazione(stazione)
                .build());
    }

    @Override
    public AzioneCasella arrivo() {
        return PagaAffitto.builder()
                .proprieta(stazione)
                .build();
    }
}
