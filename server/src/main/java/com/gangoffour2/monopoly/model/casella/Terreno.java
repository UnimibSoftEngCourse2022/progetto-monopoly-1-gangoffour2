package com.gangoffour2.monopoly.model.casella;


import com.gangoffour2.monopoly.model.Giocatore;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

enum Colore{
    ROSSO,
    BLU,
    AZZURRO,
    GIALLO,
    ARANCIONE,
    MARRONE,
    VIOLA,
    VERDE
}

@Data
@Builder
public class Terreno extends Proprietà{
    private ArrayList<Integer> affitti;
    private Colore colore;
    private int costoCasa;
    private int costoAlbergo;


    @Override
    public void arrivo(Giocatore g) {

    }

    @Override
    public void passaggio(Giocatore g) {

    }

    @Override
    public void fineGiro() {

    }
}
