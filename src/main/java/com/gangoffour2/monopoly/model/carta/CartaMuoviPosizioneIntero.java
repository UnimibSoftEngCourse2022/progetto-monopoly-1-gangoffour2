package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CartaMuoviPosizioneIntero extends Carta {
    int movimento;

    protected CartaMuoviPosizioneIntero(){

    }


    @Override
    public boolean effetto(Giocatore giocatore) {
        tabellone.muoviGiocatoreIntero(giocatore, movimento);
        return true;
    }
}
