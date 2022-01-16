package com.gangoffour2.monopoly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;

@Data
@Builder
public class Turno implements Serializable {
    private Giocatore giocatore;
    @JsonIgnore
    private Partita partita;

    @Builder.Default
    private int lanciConsecutivi = 0;

    @Builder.Default
    private int casellaDaVisitare = -1;

    @Builder.Default
    private ArrayList<Integer> valoreDadi = new ArrayList<>();

    @Builder.Default
    private SecureRandom random = new SecureRandom("Ciao".getBytes(StandardCharsets.UTF_8));

    public void inizializzaDadi(){
        if(valoreDadi == null || valoreDadi.size() == 0){
            for(int i = 0; i < partita.getConfig().getNumeroDadi(); ++i){
                valoreDadi.add(-1);
            }
        }
    }

    public void lancioDadi(){
        for(int i = 0; i < valoreDadi.size(); ++i){
            valoreDadi.set(i, random.nextInt(partita.getConfig().getFacceDadi()) + 1);
        }
        System.out.println(valoreDadi);
        ++lanciConsecutivi;
        casellaDaVisitare = sommaDadi();
    }

    public boolean dadiUguali(){
        boolean dadiUguali = true;
        int valorePrecedente = valoreDadi.get(0);
        Iterator<Integer> iter = valoreDadi.iterator();
        while (iter.hasNext() && dadiUguali){
            Integer successivo = iter.next();
            if(valorePrecedente != successivo){
                dadiUguali = false;
            }
        }
        return dadiUguali;
    }

    public boolean inVisita(){
        return casellaDaVisitare > 0;
    }

    public void prossimoEffetto(){
        --casellaDaVisitare;
        partita.getTabellone().applicaEffetto(giocatore, casellaDaVisitare);
    }

    public int sommaDadi(){
        return valoreDadi.stream().reduce(0, Integer::sum);
    }
}
