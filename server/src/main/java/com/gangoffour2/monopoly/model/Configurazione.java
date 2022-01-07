package com.gangoffour2.monopoly.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

enum Difficolta{
    EASY,
    MEDIUM,
    HARD
}

@Data
@Builder
@AllArgsConstructor
public class Configurazione {
    public static final int MAX_PLAYERS = 8;
    public static final int MAX_DADI_FACCE = 6;

    private boolean randomCaselle;
    private boolean randomEconomia;
    private int soldiIniziali;
    private Difficolta difficolta;

    Configurazione(){
        randomCaselle = false;
        randomEconomia = false;
        soldiIniziali = 1500;
        difficolta = Difficolta.EASY;
    }
}
