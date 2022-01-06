package com.gangoffour2.monopoly.stati.partita;

import java.util.ArrayList;
import java.util.Random;

import com.gangoffour2.monopoly.model.Configurazione;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Tabellone;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Partita {
    private int id;
    private ArrayList<Giocatore> giocatori;
    private Tabellone tabellone;
    private Giocatore turnoGiocatore;
    private Configurazione config;

    public int tiraDadi(){
        return new Random().nextInt(Configurazione.MAX_DADI_FACCE) + 1;
    }
    
    public boolean aggiungiGiocatore(Giocatore g){
        if(giocatori.size() == Configurazione.MAX_PLAYERS)
            //Oppure aggiungere eccezioni da throware?
            return false;
        giocatori.add(g);
        return true;
    }
    public boolean rimuoviGiocatore(Giocatore g){
        return giocatori.remove(g);
        //Oppure aggiungere eccezioni da throware?
    }
    public void cambiaTurno(){
        //logica per cambiare turno???
    }
    public void fineGiro(){
        //Eventi per fine giro
    }
    public void broadcast(){
        //Broadcast sync per tutti i player stato partita
    }
}
