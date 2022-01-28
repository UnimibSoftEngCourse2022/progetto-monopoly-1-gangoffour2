package com.gangoffour2.monopoly.model.casella;

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
        evento = TerrenoNonAcquistato.builder().terreno(this).build();
        listaCase = new ArrayList<>();
    }
    @Override
    public void randomizzaCasella(float m){
        setCostoBase((int) Math.floor(getCostoBase()*m));
        setIpoteca((int) Math.floor(getIpoteca()*m));
        setRendita((int) Math.floor(getRendita()*m));
        for(int i = 0; i < affitti.size(); i++)
            getAffitti().set(i, (int) Math.floor(getAffitti().get(i)*m));

    }


    @Override
    public int calcolaAffitto() {
        if (albergo != null) {
            return affitti.get(affitti.size() - 1);
        } else if (listaCase.size() > affitti.size()) {
            return affitti.get(affitti.size() - 2);
        }

        if (!listaCase.isEmpty()) {
            return affitti.get(listaCase.size() - 1);
        }

        return rendita;
    }

    public void aggiungiEdificio() {
        if (listaCase.size() == maxCase) {
            listaCase.add(Casa.builder().build());
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
