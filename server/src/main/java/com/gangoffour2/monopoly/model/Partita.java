package com.gangoffour2.monopoly.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.services.TimeoutHandler;
import com.gangoffour2.monopoly.stati.partita.FineTurno;
import com.gangoffour2.monopoly.stati.partita.InizioTurno;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(value = {"stato", "codaAzioniGiocatore", "azioneRicevuta"})
public class Partita implements PartitaObserver {
    private String id;
    @Builder.Default
    private ArrayList<Giocatore> giocatori = new ArrayList<>();
    private Configurazione config;
    @Builder.Default
    private ArrayList<Casa> listaCase = new ArrayList<>();
    @Builder.Default
    private ArrayList<Albergo> alberghi = new ArrayList<>();

    private Tabellone tabellone;

    private Turno turnoCorrente;

    private StatoPartita stato;

    @Builder.Default
    private LinkedList<AzioneGiocatore> codaAzioniGiocatore = new LinkedList<>();
    private boolean azioneAttesaRicevuta;

    @JsonIgnore
    @Builder.Default
    private transient TimeoutHandler listenerTimeoutEventi = new TimeoutHandler();

    public synchronized void inizioPartita(){
        turnoCorrente = Turno.builder()
                .giocatore(giocatori.get(0))
                .partita(this)
                .build();
        cambiaTurno();
    }


    public synchronized void aggiungiGiocatore(Giocatore g) throws PartitaPienaException, GiocatoreEsistenteException {
        if(giocatori.size() == config.getNumeroGiocatori()) {
            throw new PartitaPienaException();
        }

        if (giocatori.contains(g)){
            throw new GiocatoreEsistenteException();
        }

        g.setCasellaCorrente(tabellone.getCaselle().get(0));
        g.setConto(config.getSoldiIniziali());
        giocatori.add(g);
        g.setPartita(this);
    }


    public synchronized void rimuoviGiocatore(Giocatore g) {
        Giocatore giocatore = getGiocatoreByNick(g.getNick());
        if (giocatore != null){
            giocatore.abbandona();
            giocatori.remove(giocatore);
        }
    }

    public synchronized void cambiaTurno(){
        setStato(InizioTurno.builder().build());
        Giocatore curr = turnoCorrente.getGiocatore();
        setTurnoCorrente(Turno.builder()
                .partita(this)
                .giocatore(giocatori.get((giocatori.indexOf(curr) + 1) % giocatori.size()))
                .build());

        turnoCorrente.inizializzaDadi();
        turnoCorrente.getGiocatore().getCasellaCorrente().inizioTurno();
    }
    public void fineGiro(){
        //Eventi per fine giro (Economia random o altro)
    }
    public void broadcast(){
        //Broadcast sync per tutti i player stato partita
    }

    @Override
    public synchronized void onAzioneCasella(AzioneCasella azione) throws InterruptedException {
        azione.accept(stato);
        stato.esegui(azione);
    }

    public synchronized void onAzioneGiocatore(AzioneGiocatore azione)  {
        codaAzioniGiocatore.addLast(azione);
        azioneAttesaRicevuta = azione.accept(stato);
        if(azioneAttesaRicevuta){
            listenerTimeoutEventi.stopTimeout();
            codaAzioniGiocatore.removeLast();
        }
    }


    public synchronized void setStato(StatoPartita nuovoStato){
        stato = nuovoStato;
        stato.setPartita(this);
    }

    public synchronized void turnoStandard() {
        if (turnoCorrente.inVisita()){
            turnoCorrente.prossimoEffetto();
        }
        else if(turnoCorrente.getLanciConsecutivi() == 0 || turnoCorrente.dadiUguali()){
            turnoCorrente.lancioDadi();
            tabellone.muoviGiocatore(turnoCorrente.getGiocatore(), turnoCorrente.sommaDadi());
            turnoCorrente.prossimoEffetto();
        }
        else {
            setStato(FineTurno.builder().build());
            attendiAzione();
        }
    }

    public synchronized Giocatore getGiocatoreByNick(String nick){
        Iterator<Giocatore> iter = giocatori.iterator();
        Giocatore res = null;
        while (iter.hasNext() && (res == null || !res.getNick().equals(nick))){
            res = iter.next();
        }
        return res;
    }

    public synchronized void inizializza(){
        attendiAzione();
    }

    public synchronized void attendiAzione() {
        listenerTimeoutEventi.setTimeout(() -> stato.onTimeout(), 2000);
    }

}
