package com.gangoffour2.monopoly.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;

import com.gangoffour2.monopoly.model.carta.Imprevisto;
import com.gangoffour2.monopoly.model.carta.Probabilita;
import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Tabellone implements Serializable {
    private ArrayList<Casella> caselle;
    private ArrayList<Probabilita> probabilita;
    private ArrayList<Imprevisto> imprevisti;
    private ArrayList<Integer> lanci;

    private int tiraDado(){
        return new SecureRandom().nextInt(Configurazione.MAX_DADI_FACCE) + 1;
    }

    public int lanciaDadi(){

        for (int i = 0; i < Configurazione.MAX_DADI; i++) {
            lanci.add(tiraDado());
        }

        return lanci.stream().reduce(0, Integer::sum);
    }

    public boolean isDadiUguali(){
        return lanci.stream().distinct().count() <= 1;
    }


    public void muoviGiocatore(Giocatore giocatore, int quantita){
        Casella corrente = giocatore.getCasellaCorrente();
        giocatore.setCasellaCorrente(caselle.get((caselle.indexOf(corrente) + quantita) % caselle.size()));
    }

    public void applicaEffetti(Giocatore giocatore, int quantita){
        Casella casella = giocatore.getCasellaCorrente();
        casella.passaggio();
        while(quantita != 0){
            casella = caselle.get((caselle.indexOf(casella) + 1) % caselle.size());
            casella.passaggio();
            quantita--;
        }
        casella.arrivo();
    }

}
