package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.Configurazione;
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
    @JsonIgnore
    protected ArrayList<Casa> listaCase = new ArrayList<>();
    @JsonIgnore
    protected Albergo albergo = null;
    private ArrayList<Integer> affitti;
    @Builder.Default
    private int maxCase = 4;
    private Colore colore;
    private int costoCasa;
    private int costoAlbergo;

    @JsonProperty("numeroCase")
    public int getNumeroCase(){
        return listaCase.size();
    }

    @JsonProperty("hasAlbergo")
    public boolean hasAlbergo(){
        return albergo != null;
    }

    public Terreno() {
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

    public void aggiungiEdificio() throws ModificaDenaroException {
        if (getListaCase().size() < maxCase) {
            proprietario.aggiungiDenaro(-costoCasa);
            getListaCase().add(Casa.builder().build());
        } else if(!hasAlbergo()) {
            proprietario.aggiungiDenaro(-costoAlbergo);
            albergo = Albergo.builder().build();
        }
    }

    public void rimuoviEdificio() throws ModificaDenaroException {
        if (hasAlbergo()) {
            albergo = null;
            while(listaCase.size() < maxCase)
                listaCase.add(Casa.builder().build());
            proprietario.aggiungiDenaro(costoAlbergo);
        } else if (!listaCase.isEmpty()) {
            listaCase.remove(0);
            proprietario.aggiungiDenaro(costoAlbergo);
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
