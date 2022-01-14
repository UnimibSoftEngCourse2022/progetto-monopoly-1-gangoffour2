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
    private Partita partita;
    private ArrayList<Casella> caselle;

    @Builder.Default
    private ArrayList<Probabilita> probabilita = new ArrayList<>();

    @Builder.Default
    private ArrayList<Imprevisto> imprevisti = new ArrayList<>();
    @Builder.Default
    private ArrayList<Integer> lanci = new ArrayList<>();

    private int tiraDado(){
        return new SecureRandom().nextInt(partita.getConfig().getFacceDadi()) + 1;
    }

    public int lanciaDadi(){

        for (int i = 0; i < partita.getConfig().getNumeroDadi(); i++) {
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
