package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.casella.strategy.PagamentoStrategy;
import com.gangoffour2.monopoly.model.Albergo;
import com.gangoffour2.monopoly.model.Casa;
import com.gangoffour2.monopoly.stati.casella.TerrenoNonAcquistato;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Terreno extends Proprieta {
    @Builder.Default
    protected ArrayList<Casa> listaCase = new ArrayList<>();
    protected Albergo albergo = null;
    private ArrayList<Integer> affitti;
    private int maxCase;
    private Colore colore;
    private int costoCasa;
    private int costoAlbergo;

    protected Terreno() {
        stato = TerrenoNonAcquistato.builder().terreno(this).build();
        listaCase = new ArrayList<>();
    }
    @Override
    public void randomizzaCasella(float m){
        costoBase = (int) Math.floor(costoBase*m);
        ipoteca = (int) Math.floor(ipoteca*m);
        rendita = (int) Math.floor(rendita*m);
        for(int i = 0; i < getAffitti().size(); i++)
            affitti.set(i, (int) Math.floor(affitti.get(i)*m));

    }

    @Override
    public int calcolaAffitto(PagamentoStrategy strategia) {
        return strategia.calcolaAffitto(this);
    }

    public void aggiungiEdificio() {
        if (getListaCase().size() == maxCase) {
            getListaCase().add(Casa.builder().build());
        } else {
            albergo = Albergo.builder().build();
        }
    }

    public void rimuoviEdificio() {
        if (albergo != null) {
            albergo = null;
        } else if (!listaCase.isEmpty()) {
            listaCase.remove(0);
        }
    }

    public enum Colore {
        ROSSO,
        BLU,
        AZZURRO,
        GIALLO,
        ARANCIONE,
        MARRONE,
        VIOLA,
        VERDE
    }
}
