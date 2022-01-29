package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.RimuoviIpoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StazioneIpotecata extends StatoStazione {
    @Override
    public void onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca) {
        stazione.setStato(StazioneAcquistata.builder().stazione(stazione).build());
    }
}
