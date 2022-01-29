package com.gangoffour2.monopoly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.strategyCarte.RandomCarteStrategy;
import com.gangoffour2.monopoly.model.carta.strategyCarte.StrategiaCarteVanilla;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.Queue;

@Builder
@Data
public class Mazzo implements IMazzo {
    @Builder.Default
    private Queue<Carta> probabilita = new LinkedList<>();

    @Builder.Default
    private Queue<Carta> imprevisti = new LinkedList<>();

    @JsonIgnore
    @Builder.Default
    private transient RandomCarteStrategy strategiaMazzo = new StrategiaCarteVanilla();

    public void pescaImprevisto(Giocatore giocatore) throws ModificaDenaroException {
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
        Carta carta = giocatore.popCartaEsciDiPrigione();
        probabilita.add(carta);
    }

    @Override
    public void randomizzaCarte() {
        probabilita.forEach(c -> strategiaMazzo.randomizzaCarta(c));
        imprevisti.forEach(c -> strategiaMazzo.randomizzaCarta(c));
    }
}
