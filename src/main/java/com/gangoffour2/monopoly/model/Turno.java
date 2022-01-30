package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;

@Data
@Builder
public class Turno implements Serializable {
    private Giocatore giocatore;

    @Builder.Default
    private int lanciConsecutivi = 0;

    @Builder.Default
    private int casellaDaVisitare = -1;

    @Builder.Default
    private ArrayList<Integer> valoreDadi = new ArrayList<>();

    @Builder.Default
    private SecureRandom random = new SecureRandom();

    private int triggerDadiUguali;

    public void inizializzaDadi(int numeroDadi) {
        if (valoreDadi.isEmpty()) {
            for (int i = 0; i < numeroDadi; ++i) {
                valoreDadi.add(-1);
            }
        }
    }

    public void lancioDadi(int facceDadi) {
        for (int i = 0; i < valoreDadi.size(); ++i) {
            valoreDadi.set(i, random.nextInt(facceDadi) + 1);
        }

        ++lanciConsecutivi;
        casellaDaVisitare = sommaDadi();
    }

    public int sommaDadi() {
        return valoreDadi.stream().reduce(0, Integer::sum);
    }

    public boolean dadiUguali() {
        if (valoreDadi.isEmpty()){
            return false;
        }
        boolean dadiUguali = true;
        int valorePrecedente = valoreDadi.get(0);
        Iterator<Integer> iter = valoreDadi.iterator();
        while (iter.hasNext() && dadiUguali) {
            Integer successivo = iter.next();
            if (valorePrecedente != successivo) {
                dadiUguali = false;
            }
        }
        return dadiUguali;
    }

    public boolean inVisita() {
        return casellaDaVisitare > 0;
    }

    public void prossimoEffetto(ITabellone tabellone) {
        --casellaDaVisitare;
        tabellone.applicaEffetto(giocatore, casellaDaVisitare);
    }


    public boolean isTerminato(){
        return !dadiUguali() && lanciConsecutivi > 0;
    }

    public boolean limitePrigione(){
        return lanciConsecutivi == triggerDadiUguali && dadiUguali();
    }
}
