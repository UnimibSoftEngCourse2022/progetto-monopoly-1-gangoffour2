package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;

import java.io.Serializable;
import java.util.function.Predicate;

public interface ITabellone extends Serializable {
    Casella getCasella(int posizione);

    void muoviGiocatore(Giocatore giocatore, int quantita);

    void muoviAProssimaCasella(Giocatore giocatore, Predicate<Casella> predicato);

    void muoviGiocatoreIntero(Giocatore giocatore, int quantita);

    void applicaEffetto(Giocatore giocatore, int casellaDaVisitare);

    void randomizzaCaselle();
}
