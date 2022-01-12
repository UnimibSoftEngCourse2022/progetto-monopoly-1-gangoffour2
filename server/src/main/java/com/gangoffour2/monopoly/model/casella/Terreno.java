package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.stati.casella.EventoTerreno;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;



@Data
@SuperBuilder
public class Terreno extends Proprieta {
    private ArrayList<Integer> affitti;
    private Colore colore;
    private int costoCasa;
    private int costoAlbergo;

    public enum Colore{
        ROSSO,
        BLU,
        AZZURRO,
        GIALLO,
        ARANCIONE,
        MARRONE,
        VIOLA,
        VERDE
    }

    protected Terreno(){
    }

    @Override
    public void arrivo() {
        evento.arrivo();
    }

    @Override
    public void passaggio() {
        evento.passaggio();
    }

    @Override
    public void fineGiro() {
        evento.fineGiro();
    }
}
