package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CartaModificaDenaro extends Carta {
    private int denaro;

    protected CartaModificaDenaro() { }

    @Override
    public boolean effetto(Giocatore giocatore) throws ModificaDenaroException {
        giocatore.aggiungiDenaro(denaro);
        return true;
    }

    @Override
    public void randomizzaCarta(float m){
        denaro = (int) Math.floor(denaro * m);
    }
}
