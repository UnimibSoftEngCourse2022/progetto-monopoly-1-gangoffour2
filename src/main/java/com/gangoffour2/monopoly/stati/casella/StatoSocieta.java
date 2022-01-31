package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.giocatore.TermineAsta;
import com.gangoffour2.monopoly.model.casella.Societa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoSocieta implements StatoCasella {
    @JsonIgnore
    protected Societa societa;

    protected StatoSocieta() {

    }

    @Override
    public void onAzioneGiocatore(TermineAsta termineAsta){
        societa.setStato(SocietaAcquistata.builder().societa(societa).build());
        termineAsta.getGiocatore().acquistaProprieta(societa, societa.getProprietario(),
                termineAsta
                        .getAsta()
                        .getOffertaAttuale());
    }
}
