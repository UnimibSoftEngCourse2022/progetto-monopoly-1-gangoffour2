package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CartaMuoviPosizioneACasella extends Carta {
    private Casella casella;

    protected CartaMuoviPosizioneACasella(){

    }

    @Override
    public boolean effetto(Giocatore giocatore) {
        tabellone.muoviAProssimaCasella(giocatore, c -> c.getNome().equals(casella.getNome()));
        return true;
    }
}
