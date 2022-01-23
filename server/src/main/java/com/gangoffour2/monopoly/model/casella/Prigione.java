package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.stati.casella.StatoPrigione;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.LinkedHashSet;

@Data
@EqualsAndHashCode(callSuper=true)
@SuperBuilder
public class Prigione extends Casella{
    private LinkedHashSet<Giocatore> giocatoriInPrigione = new LinkedHashSet<>();

    protected Prigione(){
        evento = StatoPrigione.builder().prigione(this).build();
    }

    public boolean liberaGiocatore(Giocatore giocatore){
        return giocatoriInPrigione.remove(giocatore);
    }

    public boolean imprigionaGiocatore(Giocatore giocatore){
        return giocatoriInPrigione.add(giocatore);
    }

}
