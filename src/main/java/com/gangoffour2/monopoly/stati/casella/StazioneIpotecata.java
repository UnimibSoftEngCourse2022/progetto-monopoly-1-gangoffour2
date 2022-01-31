package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StazioneIpotecata extends StatoStazione {
    protected StazioneIpotecata(){

    }

    @Override
    public void onAzioneGiocatore(Ipoteca rimuoviIpoteca) {
        try {
            stazione.getProprietario().aggiungiDenaro(-stazione.getIpoteca());
            stazione.setStato(StazioneAcquistata.builder()
                    .stazione(stazione)
                    .build());
        }catch (ModificaDenaroException e){
            //Il metodo deve essere vuoto perché se non ha i soldi il terreno non viene disipotecato
        }
    }
}
