package com.gangoffour2.monopoly.model;

import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.LinkedList;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.eccezioni.NoPlayerException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.stati.partita.AttesaPrigione;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Partita implements PartitaObserver {
    private int id;
    @Builder.Default
    private ArrayList<Giocatore> giocatori = new ArrayList<>(Configurazione.MAX_PLAYERS);
    @Builder.Default
    private ArrayList<Casa> listaCase = new ArrayList<>(Configurazione.MAX_CASE_VENDIBILI);
    @Builder.Default
    private ArrayList<Albergo> alberghi = new ArrayList<>(Configurazione.MAX_ALBERGHI_VENDIBILI);


    private Tabellone tabellone;
    private Giocatore turnoGiocatore;
    private Configurazione config;
    private StatoPartita stato;

    private LinkedList<AzioneGiocatore> codaAzioniGiocatore;
    private AzioneGiocatore azioneRicevuta; // Contiene l'azione da eseguire in quel momento

    public void start() {

        turnoGiocatore.getCasellaCorrente().inizioTurno();

        do {
            int spostamento = tabellone.lanciaDadi();
            tabellone.applicaEffetti(turnoGiocatore, spostamento);
            tabellone.muoviGiocatore(turnoGiocatore, spostamento);

            if(tabellone.getDadiUguali() == Configurazione.MAX_DADI_UGUALI)
                setStato(AttesaPrigione.builder().build());

        } while(tabellone.isDadiUguali());

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
        setTurnoGiocatore(giocatori.get((giocatori.indexOf(curr) + 1) % giocatori.size()));
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
        azione.accept(stato);
    }


    public synchronized void onAzioneGiocatore(AzioneGiocatore azione){
        codaAzioniGiocatore.addLast(azioneRicevuta);
        azioneRicevuta = azione;
        notifyAll();
    }

    public synchronized void attendiAzione() throws InterruptedException {
        azioneRicevuta = null;
        while (azioneRicevuta == null){
            wait();
        }
        azioneRicevuta.accept(stato);
        // Se non è più in wait, allora si può rimuovere dalla lista l'evento.
        codaAzioniGiocatore.removeLast();
    }


    public void setStato(StatoPartita nuovoStato){
        stato = nuovoStato;
        stato.setPartita(this);
    }
}
