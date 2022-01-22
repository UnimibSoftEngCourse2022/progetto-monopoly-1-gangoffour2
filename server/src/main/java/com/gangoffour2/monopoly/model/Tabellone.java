package com.gangoffour2.monopoly.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.carta.Carta;
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
    private Queue<Carta> probabilita = new LinkedList<>();

    @Builder.Default
    private Queue<Carta> imprevisti = new LinkedList<>();


    public void muoviGiocatore(Giocatore giocatore, int quantita){
        if(quantita != 0){
            Turno turno = partita.getTurnoCorrente();
            Casella corrente = giocatore.getCasellaCorrente();
            giocatore.setCasellaCorrente(caselle.get((caselle.indexOf(corrente) + quantita) % caselle.size()));
            if(quantita > 0) {
                turno.setCasellaDaVisitare(quantita);
                partita.turnoStandard();
                //eventualmente stati?
            }
            else
                giocatore.getCasellaCorrente().arrivo();
            }
    }

    public void muoviGiocatoreACasella(Giocatore giocatore, Casella c){
        giocatore.setCasellaCorrente(c);
    }

    public void muoviGiocatoreProssimoTipoCasella(Giocatore giocatore, Class <? extends Casella> c){
        Casella corrente = giocatore.getCasellaCorrente();
        int i = caselle.indexOf(corrente);
        Casella prossimaCasella = caselle.get(i+1);
        while(!prossimaCasella.getClass().equals(c)){
            if(i + 1 > caselle.size())
                i = 0;
            i++;
            if(caselle.get(i).getClass().equals(c))
                prossimaCasella = caselle.get(i);
        }
        giocatore.setCasellaCorrente(prossimaCasella);
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

    public void pescaImprevisto(Giocatore giocatore){
        Carta carta = imprevisti.remove();
        carta.effetto(giocatore);
    }

    public void pescaOpportunita(Giocatore giocatore){
        Carta carta = probabilita.remove();
        carta.effetto(giocatore);
    }

}
