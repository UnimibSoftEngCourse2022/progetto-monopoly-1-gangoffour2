package com.gangoffour2.monopoly.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;



enum Difficoltà{
    easy,
    medium,
    hard
}

@Data
@Builder
@AllArgsConstructor
public class Configurazione {
    private boolean randomCaselle;
    private boolean randomEconomia;
    private int soldiIniziali;
    private Difficoltà difficoltà;

    Configurazione(){
        randomCaselle = false;
        randomEconomia = false;
        soldiIniziali = 1500;
        difficoltà = Difficoltà.easy;
    }
}
