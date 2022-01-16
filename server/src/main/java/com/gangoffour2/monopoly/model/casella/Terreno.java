package com.gangoffour2.monopoly.model.casella;

import lombok.Data;
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


}
