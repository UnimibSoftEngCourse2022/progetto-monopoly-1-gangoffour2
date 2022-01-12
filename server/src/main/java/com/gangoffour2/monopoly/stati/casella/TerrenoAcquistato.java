package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoAcquistato extends EventoTerreno{


    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca){
        terreno.setEvento(
                TerrenoIpotecato
                .builder()
                        .terreno(terreno)
                        .build());
    }
}
