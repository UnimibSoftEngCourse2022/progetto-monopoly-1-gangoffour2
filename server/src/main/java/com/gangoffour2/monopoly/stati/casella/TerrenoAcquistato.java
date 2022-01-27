package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PagaAffitto;
import com.gangoffour2.monopoly.azioni.giocatore.DowngradaTerreno;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.UpgradaTerreno;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoAcquistato extends StatoTerreno {

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        terreno.getProprietario().aggiungiDenaro(terreno.getIpoteca());
        terreno.setEvento(
                TerrenoIpotecato
                        .builder()
                        .terreno(terreno)
                        .build());
    }


    @Override
    public AzioneCasella arrivo() {
        return PagaAffitto.builder()
                .proprieta(terreno)
                .build();
    }


    @Override
    public void onAzioneGiocatore(UpgradaTerreno upgradaTerreno) {
        terreno.aggiungiEdificio();
    }

    @Override
    public void onAzioneGiocatore(DowngradaTerreno downgradaTerreno) {
        terreno.rimuoviEdificio();
    }
}
