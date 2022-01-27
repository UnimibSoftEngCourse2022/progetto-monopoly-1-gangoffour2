package com.gangoffour2.monopoly.model;

import java.io.Serializable;

public interface IMazzo extends Serializable {
    void pescaImprevisto(Giocatore giocatore);
    void pescaProbabilita(Giocatore giocatore);
    void utilizzaCarta(Giocatore giocatore);
}
