package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CartaEsciGratisPrigione extends Carta {


    protected CartaEsciGratisPrigione(){

    }

    @Override
    public boolean effetto(Giocatore giocatore) {
        giocatore.aggiungiEsciGratis(this);
        return false;
    }
}
