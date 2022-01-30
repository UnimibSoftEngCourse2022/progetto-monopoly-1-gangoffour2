package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoIpotecato extends StatoTerreno {

    protected TerrenoIpotecato(){

    }

    @Override
    public void onAzioneGiocatore(Ipoteca rimuoviIpoteca) {
        try {
            terreno.getProprietario().aggiungiDenaro(-terreno.getIpoteca());
            terreno.setStato(TerrenoAcquistato.builder()
                    .terreno(terreno)
                    .build());
        }catch (ModificaDenaroException e){
            //Il metodo deve essere vuoto perché se non ha i soldi il terreno non viene disipotecato
        }
    }
}
