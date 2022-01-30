package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SocietaIpotecata extends StatoSocieta {
    protected SocietaIpotecata(){

    }

    @Override
    public void onAzioneGiocatore(Ipoteca rimuoviIpoteca) {
        try {
            societa.getProprietario().aggiungiDenaro(-societa.getIpoteca());
            societa.setStato(SocietaAcquistata.builder()
                    .societa(societa)
                    .build());
        }catch (ModificaDenaroException e){
            //Il metodo deve essere vuoto perché se non ha i soldi il terreno non viene disipotecato
        }
    }
}
