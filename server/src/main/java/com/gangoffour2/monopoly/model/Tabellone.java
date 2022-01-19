package com.gangoffour2.monopoly.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Partita partita;
    private ArrayList<Casella> caselle;

    @Builder.Default
    private ArrayList<Probabilita> probabilita = new ArrayList<>();

    @Builder.Default
    private ArrayList<Imprevisto> imprevisti = new ArrayList<>();
    @Builder.Default
    private ArrayList<Integer> lanci = new ArrayList<>();


    public void muoviGiocatore(Giocatore giocatore, int quantita){
        Casella corrente = giocatore.getCasellaCorrente();
        giocatore.setCasellaCorrente(caselle.get((caselle.indexOf(corrente) + quantita) % caselle.size()));
    }

    public void applicaEffetto(Giocatore giocatore, int offset){
        if(offset == 0){
            giocatore.getCasellaCorrente().arrivo();
        }
        else {
            int posizioneCorrente = caselle.indexOf(giocatore.getCasellaCorrente());
            caselle.get((posizioneCorrente - offset + caselle.size()) % caselle.size()).passaggio();
        }
    }

}
