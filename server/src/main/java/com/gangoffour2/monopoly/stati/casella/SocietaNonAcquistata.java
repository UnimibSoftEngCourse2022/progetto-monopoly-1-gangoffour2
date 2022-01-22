package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaNonAcquistata extends EventoSocieta {

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta){
        acquistaProprieta.getGiocatore().acquistaProprieta(societa);
        societa.setProprietario(acquistaProprieta.getGiocatore());
        societa.setEvento(SocietaAcquistata.builder()
                .societa(societa)
                .build());

    }
}
