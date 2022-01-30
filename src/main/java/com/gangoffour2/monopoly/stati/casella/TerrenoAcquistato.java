package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PagaAffitto;
import com.gangoffour2.monopoly.azioni.giocatore.DowngradaTerreno;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.UpgradaTerreno;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoAcquistato extends StatoTerreno {

    protected TerrenoAcquistato(){

    }

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        terreno.getProprietario().aggiungiDenaro(terreno.getIpoteca());
        terreno.setStato(
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
        try {
            terreno.aggiungiEdificio();
        }catch (ModificaDenaroException e){
            System.out.println(e.getSoldiDaPagare());
            //Non deve gestire l'eccezione se cerca di aggiungere case senza i soldi
        }
    }

    @Override
    public void onAzioneGiocatore(DowngradaTerreno downgradaTerreno) {
        try {
            terreno.rimuoviEdificio();
        } catch (ModificaDenaroException e){
            //Non deve gestire l'eccezione perchè aggiunge solamente i soldi
        }
    }
}
