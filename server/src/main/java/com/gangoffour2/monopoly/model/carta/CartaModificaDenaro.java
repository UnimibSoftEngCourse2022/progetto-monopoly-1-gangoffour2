package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
public class CartaModificaDenaro extends Carta{
    private int denaro;

    @Override
    public boolean effetto(Giocatore giocatore) {
        giocatore.setConto(giocatore.getConto() + denaro);
        return true;
    }
}
