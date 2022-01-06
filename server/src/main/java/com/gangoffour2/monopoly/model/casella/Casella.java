package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public abstract class Casella {
    protected String nome;
    public abstract void arrivo();
    public abstract void passaggio();
    public abstract void fineGiro();

    protected Casella(){
    }
}
