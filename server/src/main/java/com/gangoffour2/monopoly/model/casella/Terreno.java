package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.model.Albergo;
import com.gangoffour2.monopoly.model.Casa;
import com.gangoffour2.monopoly.stati.casella.TerrenoNonAcquistato;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;



@Data
@SuperBuilder
@JsonTypeName("Terreno")
public class Terreno extends Proprieta {
    private ArrayList<Integer> affitti;
    private int maxCase;
    private Colore colore;
    private int costoCasa;
    private int costoAlbergo;

    @Builder.Default
    protected ArrayList<Casa> listaCase = new ArrayList<>();
    protected Albergo albergo = null;

    public enum Colore{
        ROSSO,
        BLU,
        AZZURRO,
        GIALLO,
        ARANCIONE,
        MARRONE,
        VIOLA,
        VERDE
    }

    @Override
    public String getTipo() {
        return "Terreno";
    }

    protected Terreno(){
        evento = TerrenoNonAcquistato.builder().terreno(this).build();
        listaCase = new ArrayList<>();
    }

    @Override
    public int calcolaAffitto() {
        if(albergo != null){
            return affitti.get(affitti.size() - 1);
        }
        else if (listaCase.size() > affitti.size()) {
            return affitti.get(affitti.size() - 2);
        }

        return affitti.get(listaCase.size() - 1);
    }

    public void aggiungiEdificio(){
        if (listaCase.size() == maxCase){
            listaCase.add(Casa.builder().build());
        }
        else {
            albergo = Albergo.builder().build();
        }
    }


    public void rimuoviEdificio(){
        if (albergo != null){
            albergo = null;
        }
        else if (!listaCase.isEmpty()){
            listaCase.remove(0);
        }
    }
}
