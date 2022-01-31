package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.giocatore.TermineAsta;
import com.gangoffour2.monopoly.model.casella.Terreno;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoTerreno implements StatoCasella {
    @JsonIgnore
    protected Terreno terreno;

    protected StatoTerreno() {

    }

    @Override
    public void onAzioneGiocatore(TermineAsta termineAsta){
        terreno.setStato(TerrenoAcquistato.builder().terreno(terreno).build());
        termineAsta.getGiocatore().acquistaProprieta(terreno, terreno.getProprietario(),
                termineAsta
                        .getAsta()
                        .getOffertaAttuale());
    }
}
