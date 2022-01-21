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


    private boolean randomCaselle;
    private boolean randomEconomia;
    private int soldiIniziali;
    private Difficolta difficolta;


    /**
     *  Questi attributi sono passati dall'utente
     *  nel momento della richiesta di creazione della partitta.
     */
    @Builder.Default
    private int numeroGiocatori = 4;

    @Builder.Default
    private int facceDadi = 6;

    @Builder.Default
    private int numeroDadi = 2;

    @Builder.Default
    private int triggerDadiUguali = 3;

    @Builder.Default
    private int caseVendibili = 32;

    @Builder.Default
    private int alberghiVendibili = 16;

    @Builder.Default
    private int maxCasePerTerreno = 4;

    Configurazione(){
        randomCaselle = false;
        randomEconomia = false;
        soldiIniziali = 1500;
        difficolta = Difficolta.EASY;
        numeroGiocatori = 8;
        triggerDadiUguali = 2;
    }
}
