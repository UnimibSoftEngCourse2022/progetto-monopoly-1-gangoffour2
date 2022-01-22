package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public abstract class Carta implements Serializable {
    private String testo;
    public abstract void esegui(Giocatore g);
    public abstract void effetto();
}
