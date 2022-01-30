package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.giocatore.TermineAsta;
import com.gangoffour2.monopoly.model.casella.Stazione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoStazione implements StatoCasella {
    @JsonIgnore
    protected Stazione stazione;

    protected StatoStazione() {

    }

    @Override
    public void onAzioneGiocatore(TermineAsta termineAsta){
        stazione.setStato(StazioneAcquistata.builder().stazione(stazione).build());
        termineAsta.getGiocatore().acquistaProprieta(stazione, stazione.getProprietario(),
                termineAsta
                        .getAsta()
                        .getOffertaAttuale());
    }
}
