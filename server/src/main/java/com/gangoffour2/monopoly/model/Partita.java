package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.giocatore.AzioneGiocatore;
import com.gangoffour2.monopoly.controller.MessageBrokerSingleton;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.eccezioni.PartitaPienaException;
import com.gangoffour2.monopoly.stati.partita.AttesaPrigione;
import com.gangoffour2.monopoly.stati.partita.FineTurno;
import com.gangoffour2.monopoly.stati.partita.InizioTurno;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

@Data
@SuperBuilder
public class Partita extends IPartita implements PartitaObserver {

    protected Partita(IPartitaBuilder<?, ?> b) {
        super(b);
    }

    public synchronized void inizioPartita() {
        turnoCorrente = Turno.builder()
                .giocatore(giocatori.get(0))
                .build();
        cambiaTurno();
    }

    public synchronized void aggiungiGiocatore(Giocatore g) throws PartitaPienaException, GiocatoreEsistenteException {
        if (giocatori.size() == config.getNumeroGiocatori()) {
            throw new PartitaPienaException();
        }
        if (giocatori.contains(g)) {
            throw new GiocatoreEsistenteException();
        }
        g.setCasellaCorrente(tabellone.getCasella(0));
        g.setConto(config.getSoldiIniziali());
        giocatori.add(g);
        g.setPartita(this);
    }

    public synchronized void rimuoviGiocatore(Giocatore g) {
        Giocatore giocatore = getGiocatoreByNick(g.getNick());
        if (giocatore != null) {
            giocatore.abbandona();
            giocatori.remove(giocatore);
        }
    }

    public synchronized void cambiaTurno() {
        setStato(InizioTurno.builder().build());
        Giocatore curr = turnoCorrente.getGiocatore();
        setTurnoCorrente(Turno.builder()
                .giocatore(giocatori.get((giocatori.indexOf(curr) + 1) % giocatori.size()))
                .build());

        turnoCorrente.inizializzaDadi(config.getNumeroDadi());
        turnoCorrente.getGiocatore().getCasellaCorrente().inizioTurno(turnoCorrente.getGiocatore());
        broadcast();
    }

    public void fineGiro() {
        //Eventi per fine giro (Economia random o altro)
    }

    public void broadcast() {
        MessageBrokerSingleton.getInstance().broadcast(this);
    }

    @Override
    public synchronized void onAzioneCasella(AzioneCasella azione) {
        azione.accept(stato);
        broadcast();
    }

    @Override
    public synchronized void onAzioneGiocatore(AzioneGiocatore azione) {
        azione.accept(stato);
        broadcast();
    }

    public synchronized void setStato(StatoPartita nuovoStato) {
        stato = nuovoStato;
        stato.setPartita(this);
    }

    public void continuaTurno() {
        broadcast();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Logger logger = LoggerFactory.getLogger(Partita.class);
            logger.error("Wait fallita");
            Thread.currentThread().interrupt();
        }

        if (turnoCorrente.inVisita()) {
            turnoCorrente.prossimoEffetto(tabellone);
        } else if (turnoCorrente.getLanciConsecutivi() == 0 || turnoCorrente.dadiUguali()) {
            turnoCorrente.lancioDadi(config.getFacceDadi());
            if (turnoCorrente.getLanciConsecutivi() == 3) {
                setStato(AttesaPrigione.builder().build());
            } else {
                turnoCorrente.prossimoEffetto(tabellone);
            }
        } else {
            setStato(FineTurno.builder().build());
            attendiAzione();
        }
    }

    public synchronized Giocatore getGiocatoreByNick(String nick) {
        Iterator<Giocatore> iter = giocatori.iterator();
        Giocatore res = null;
        while (iter.hasNext() && (res == null || !res.getNick().equals(nick))) {
            res = iter.next();
        }
        return res;
    }

    public synchronized void inizializza() {
        attendiAzione();
    }

    public synchronized void fermaAttesa() {
        listenerTimeoutEventi.stopTimeout();
    }

    public synchronized void attendiAzione() {
        listenerTimeoutEventi.setTimeout(() -> stato.onTimeout(), 10000);
    }
}
