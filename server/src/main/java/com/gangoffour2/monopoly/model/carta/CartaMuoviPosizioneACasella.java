package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
public class CartaMuoviPosizioneACasella extends Carta{
    private Casella casella;

    @Override
    public boolean effetto(Giocatore giocatore) {
        tabellone.muoviGiocatoreACasella(giocatore, casella);
        return true;
    }
}
