package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.carta.Carta;
import lombok.Builder;

import java.util.LinkedList;
import java.util.Queue;

@Builder
public class Mazzo implements IMazzo {

    @Builder.Default
    private final Queue<Carta> probabilita = new LinkedList<>();

    @Builder.Default
    private final Queue<Carta> imprevisti = new LinkedList<>();

    public void pescaImprevisto(Giocatore giocatore) {
        Carta carta = imprevisti.remove();
        if (carta.effetto(giocatore))
            imprevisti.add(carta);
    }

    public void pescaProbabilita(Giocatore giocatore) {
        Carta carta = probabilita.remove();
        if (carta.effetto(giocatore))
            probabilita.add(carta);
    }

    public void utilizzaCarta(Giocatore giocatore) {
        Carta carta = giocatore.getEsciGratis().removeLast();
        carta.effetto(giocatore);
    }
}
