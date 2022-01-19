package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.TerrenoNonAcquistato;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;



@Data
@SuperBuilder
@JsonTypeName("Terreno")
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

    @Override
    public String getTipo() {
        return "Terreno";
    }

    protected Terreno(){
        evento = TerrenoNonAcquistato.builder().terreno(this).build();
    }


}
