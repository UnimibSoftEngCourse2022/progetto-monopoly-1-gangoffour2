package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.RimuoviIpoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoIpotecato extends StatoTerreno {
    @Override
    public void onAzioneGiocatore(RimuoviIpoteca rimuoviIpoteca) {
        terreno.setEvento(TerrenoAcquistato.builder()
                .terreno(terreno)
                .build());
    }
}
