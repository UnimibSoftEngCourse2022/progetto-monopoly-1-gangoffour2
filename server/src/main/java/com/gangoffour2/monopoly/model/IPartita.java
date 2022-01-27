package com.gangoffour2.monopoly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.services.TimeoutHandler;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
@SuperBuilder
public abstract class IPartita {
    protected String id;

    @Builder.Default
    protected ArrayList<Giocatore> giocatori = new ArrayList<>();

    protected Configurazione config;

    @Builder.Default
    protected ArrayList<Casa> listaCase = new ArrayList<>();

    @Builder.Default
    protected ArrayList<Albergo> alberghi = new ArrayList<>();

    protected ITabellone tabellone;

    @JsonIgnore
    protected IMazzo mazzo;

    protected Turno turnoCorrente;

    protected StatoPartita stato;

    @Builder.Default
    @JsonIgnore
    protected LinkedList<AzioneGiocatore> codaAzioniGiocatore = new LinkedList<>();

    @JsonIgnore
    protected boolean azioneAttesaRicevuta;

    @JsonIgnore
    @Builder.Default
    protected transient TimeoutHandler listenerTimeoutEventi = new TimeoutHandler();

    public abstract void inizioPartita();

    public abstract void aggiungiGiocatore(Giocatore g) throws PartitaPienaException, GiocatoreEsistenteException;

    public abstract void rimuoviGiocatore(Giocatore g);

    public abstract void cambiaTurno();

    public abstract void fineGiro();

    public abstract void broadcast();

    public abstract void onAzioneCasella(AzioneCasella azione);

    public abstract void onAzioneGiocatore(AzioneGiocatore azione);

    public abstract void setStato(StatoPartita nuovaStato);

    public abstract void turnoStandard();

    public abstract Giocatore getGiocatoreByNick(String nick);

    public abstract void inizializza();

    public abstract void attendiAzione();
}
