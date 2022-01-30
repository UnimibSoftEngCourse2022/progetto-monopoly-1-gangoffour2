package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.casella.strategy.PagamentoStrategy;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public abstract class Proprieta extends Casella {
    protected int costoBase;
    protected int ipoteca;
    protected int rendita;
    @JsonIgnore
    protected Giocatore proprietario;

    protected Proprieta() {
    }

    public abstract int calcolaAffitto(PagamentoStrategy strategia);

    public abstract void reset();

    @Override
    public void randomizzaCasella(float m){
        costoBase = (int) Math.floor(costoBase*m);
        ipoteca = (int) Math.floor(ipoteca*m);
        rendita = (int) Math.floor(rendita*m);
    }

}
