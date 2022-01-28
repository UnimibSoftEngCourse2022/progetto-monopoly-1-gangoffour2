package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoNonAcquistato extends StatoTerreno {

    @Override
    public AzioneCasella arrivo() {
        return RichiediAcquisto.builder().proprieta(terreno).build();
    }


    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        acquistaProprieta.getGiocatore().acquistaProprieta(terreno);
        terreno.setEvento(
                TerrenoAcquistato.builder()
                        .terreno(terreno)
                        .build());
    }
}
