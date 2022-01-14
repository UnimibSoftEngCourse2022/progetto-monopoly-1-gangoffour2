package com.gangoffour2.monopoly.model;

import java.util.ArrayList;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.eccezioni.NoPlayerException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(value = {"stato", "codaAzioniGiocatore", "azioneRicevuta"})
public class Partita implements PartitaObserver, Runnable {
    private String id;
    @Builder.Default
    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Configurazione config;
    @Builder.Default
    private ArrayList<Casa> listaCase = new ArrayList<>();
    @Builder.Default
    private ArrayList<Albergo> alberghi = new ArrayList<>();

    @JsonIgnore
    private Thread thread;

    private Tabellone tabellone;
    private Giocatore turnoGiocatore;

    private StatoPartita stato;

    @Builder.Default
    private LinkedList<AzioneGiocatore> codaAzioniGiocatore = new LinkedList<>();

    private AzioneGiocatore azioneRicevuta; // Contiene l'azione da eseguire in quel momento


    public void inizioPartita(){
        turnoGiocatore = giocatori.get(0);
        start();
    }

    public void start() {
        turnoGiocatore.getCasellaCorrente().inizioTurno();
    }



    public void aggiungiGiocatore(Giocatore g) throws PartitaPienaException, GiocatoreEsistenteException {
        if(giocatori.size() == config.getNumeroGiocatori()) {
            throw new PartitaPienaException();
        }

        if (giocatori.contains(g)){
            throw new GiocatoreEsistenteException();
        }

        g.setCasellaCorrente(tabellone.getCaselle().get(0));
        g.setConto(config.getSoldiIniziali());
        giocatori.add(g);
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
    public void onAzioneCasella(AzioneCasella azione) throws InterruptedException {
        azione.accept(stato);
        stato.esegui(azione);
    }

    public synchronized void onAzioneGiocatore(AzioneGiocatore azione) {
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
        azioneRicevuta = null;
    }


    public void setStato(StatoPartita nuovoStato){
        stato = nuovoStato;
        stato.setPartita(this);
    }

    public void inizializza(){
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            attendiAzione();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
