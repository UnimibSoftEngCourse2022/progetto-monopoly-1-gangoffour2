package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.RimuoviIpoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaIpotecata extends EventoSocieta {

    @Override
    public void onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca){
        societa.setEvento(SocietaAcquistata.builder()
                .societa(societa)
                .build());
    }
}
