package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Albergo;
import com.gangoffour2.monopoly.model.Casa;
import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@SuperBuilder
public abstract class Proprieta extends Casella {
    protected int costoBase;
    protected int ipoteca;
    protected Giocatore proprietario;
    @Builder.Default
    protected ArrayList<Casa> listaCase = new ArrayList<>(4);
    protected Albergo albergo = null;


    protected Proprieta() {
    }

}
