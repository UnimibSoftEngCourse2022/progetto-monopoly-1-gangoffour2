package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;

import java.io.Serializable;

public interface IMazzo extends Serializable {
    void pescaImprevisto(Giocatore giocatore);

    void pescaProbabilita(Giocatore giocatore);

    void utilizzaCarta(Giocatore giocatore);

    void randomizzaCarte();

    Carta nextProbabilita();

    Carta nextImprevisto();
}
