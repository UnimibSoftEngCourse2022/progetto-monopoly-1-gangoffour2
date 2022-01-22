package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

@Data
@SuperBuilder
public abstract class Carta implements Serializable {
    protected String testo;
    public abstract void effetto(Giocatore g);
}
