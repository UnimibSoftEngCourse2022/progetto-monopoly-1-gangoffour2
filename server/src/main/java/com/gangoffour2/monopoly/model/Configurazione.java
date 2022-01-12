package com.gangoffour2.monopoly.model;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
public class Configurazione implements Serializable {
    public enum Difficolta{
        EASY,
        MEDIUM,
        HARD
    }
    public static final int MAX_PLAYERS = 8;
    public static final int MAX_DADI_FACCE = 6;
    public static final int MAX_CASE_VENDIBILI = 32;
    public static final int MAX_ALBERGHI_VENDIBILI = 12;

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
