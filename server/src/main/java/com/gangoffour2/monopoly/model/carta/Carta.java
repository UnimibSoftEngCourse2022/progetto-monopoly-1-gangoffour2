package com.gangoffour2.monopoly.model.carta;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Tabellone;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

@Data
@SuperBuilder
public abstract class Carta implements Serializable {
    protected String testo;
    protected Tabellone tabellone;
    public abstract void effetto(Giocatore g);
}
