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
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class IPartita implements Serializable {
    protected String id;

    @Builder.Default
    protected ArrayList<Giocatore> giocatori = new ArrayList<>();

    protected Configurazione config;

    protected ITabellone tabellone;

    @JsonIgnore
    protected IMazzo mazzo;

    protected Turno turnoCorrente;

    protected StatoPartita stato;

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

    public abstract void continuaTurno();

    public abstract Giocatore getGiocatoreByNick(String nick);

    public abstract void inizializza();

    public abstract void fermaAttesa();

    public abstract void attendiAzione();

    public abstract void continua();

    public abstract void memorizzaStato(StatoPartita statoPartita);
}
