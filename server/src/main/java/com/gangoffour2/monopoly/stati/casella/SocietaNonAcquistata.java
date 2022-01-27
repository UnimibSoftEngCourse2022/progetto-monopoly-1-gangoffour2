package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaNonAcquistata extends StatoSocieta {

    @Override
    public void arrivo() {
        RichiediAcquisto richiediAcquisto = RichiediAcquisto.builder().proprieta(societa).build();
        societa.notificaTutti(richiediAcquisto);
    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        acquistaProprieta.getGiocatore().acquistaProprieta(societa);
        societa.setProprietario(acquistaProprieta.getGiocatore());
        societa.setEvento(SocietaAcquistata.builder()
                .societa(societa)
                .build());

    }
}
