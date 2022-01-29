package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PagaAffitto;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaAcquistata extends StatoSocieta {

    @Override
    public AzioneCasella arrivo() {
        return PagaAffitto.builder()
                .proprieta(societa)
                .build();
    }

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        ipoteca.getGiocatore().aggiungiDenaro(societa.getIpoteca());
        societa.setStato(SocietaIpotecata.builder()
                .societa(societa)
                .build());
    }
}
