package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Asta;
import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Proprieta extends Casella {
    protected int costoBase;
    protected int ipoteca;
    protected Giocatore proprietario;

    protected Proprieta() {
    }

    public void rimuoviIpoteca(){

    }
}
