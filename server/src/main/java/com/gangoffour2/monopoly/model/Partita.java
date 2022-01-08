package com.gangoffour2.monopoly.model;

import java.util.ArrayList;
import java.security.SecureRandom;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.eccezioni.NoPlayerException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Partita implements PartitaObserver {
    private int id;
    private ArrayList<Giocatore> giocatori;
    private Tabellone tabellone;
    private Giocatore turnoGiocatore;
    private Configurazione config;
    private StatoPartita stato;

    public int tiraDado(){
        return new SecureRandom().nextInt(Configurazione.MAX_DADI_FACCE) +1;
    }

    public void aggiungiGiocatore(Giocatore g) throws PartitaPienaException{
        if(this.getGiocatori().size() == Configurazione.MAX_PLAYERS)
            throw new PartitaPienaException();
        this.getGiocatori().add(g);
    }
    public void rimuoviGiocatore(Giocatore g) throws NoPlayerException {
        if(!this.getGiocatori().remove(g))
            throw new NoPlayerException();
    }
    public void cambiaTurno(){
        Giocatore curr = this.getTurnoGiocatore();
        Giocatore next = this.getGiocatori().indexOf(curr) == this.getGiocatori().size()-1 ?
                this.getGiocatori().get(0) :
                this.getGiocatori().get(this.getGiocatori().indexOf(curr)+1);
        this.setTurnoGiocatore(next);
        //Eventi cambiaTurno, broadcast sync
    }
    public void fineGiro(){
        //Eventi per fine giro (Economia random o altro)
    }
    public void broadcast(){
        //Broadcast sync per tutti i player stato partita
    }

    @Override
    public void notifica(AzioneCasella azione) {

    }
}
