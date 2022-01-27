package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.PagaAffitto;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaAcquistata extends StatoSocieta {

    @Override
    public void arrivo() {
        societa.notificaTutti(PagaAffitto.builder().build());
    }

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca){
        ipoteca.getGiocatore().modificaDenaro(societa.getIpoteca());
        societa.setEvento(SocietaIpotecata.builder()
                        .societa(societa)
                .build());
    }
}
