package com.gangoffour2.monopoly.model.carta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.ITabellone;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public abstract class Carta implements Serializable {
    protected String testo;

    @JsonIgnore
    protected ITabellone tabellone;

    public abstract boolean effetto(Giocatore g);
}
