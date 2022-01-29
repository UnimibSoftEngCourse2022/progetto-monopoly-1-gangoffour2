package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaNonAcquistata extends StatoSocieta {

    @Override
    public AzioneCasella arrivo() {
        return RichiediAcquisto.builder().proprieta(societa).build();
    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        acquistaProprieta.getGiocatore().acquistaProprieta(societa);
        societa.setProprietario(acquistaProprieta.getGiocatore());
        societa.setStato(SocietaAcquistata.builder()
                .societa(societa)
                .build());

    }
}
