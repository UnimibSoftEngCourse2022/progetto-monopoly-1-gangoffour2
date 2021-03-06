package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Configurazione;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.Turno;
import com.gangoffour2.monopoly.services.FactoryPartita;
import com.gangoffour2.monopoly.stati.partita.AttesaAcquisto;
import com.gangoffour2.monopoly.stati.partita.LancioDadi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonopolyApplicationTests {

    IPartita partita;

    static IPartita creaPartita() throws GiocatoreEsistenteException, IOException {
        Configurazione conf = Configurazione.builder()
                .difficolta(Configurazione.Difficolta.MEDIUM)
                .randomCaselle(true)
                .soldiIniziali(5000)
                .randomEconomia(true)
                .numeroGiocatori(2)
                .build();

        IPartita partita = FactoryPartita.getInstance().creaPartita(conf);

        partita.getStato().setPartita(partita);

        Giocatore g = Giocatore.builder()
                .nick("CancaroMan")
                .conto(partita.getConfig().getSoldiIniziali())
                .build();
        partita.aggiungiGiocatore(g);
        partita.setTurnoCorrente(Turno.builder().giocatore(g).build());

        return partita;
    }

    static void fakeTiro(IPartita partita, Giocatore giocatore, int spostamento) {
        ArrayList<Integer> dadi = new ArrayList<>();
        dadi.add(spostamento - 1);
        dadi.add(1);
        Turno t =   Turno.builder()
                .casellaDaVisitare(spostamento)
                .lanciConsecutivi(1)
                .valoreDadi(dadi)
                .giocatore(giocatore)
                .build();
        t.inizializzaDadi(2);
        partita.setTurnoCorrente(t);
    }

    @BeforeEach
    public void setup() throws GiocatoreEsistenteException, IOException {
        partita = MonopolyApplicationTests.creaPartita();
    }

    @Test
    void creazionePartita() {
        assertEquals("CancaroMan", partita.getTurnoCorrente().getGiocatore().getNick());
        assertNotNull(partita.getStato());
        assertNotNull(partita.getStato().getPartita());
        assertNotNull(partita.getTabellone());
        assertEquals(1, partita.getGiocatori().size());
    }

    @Test
    void cambioStatiPartita() {
        Giocatore g = partita.getTurnoCorrente().getGiocatore();
        partita.setStato(LancioDadi.builder().build());
        partita.getTabellone().muoviGiocatore(g, 9);
        assertEquals("Viale Vesuvio", g.getCasellaCorrente().getNome());

        g.getCasellaCorrente().arrivo(g);
        assertInstanceOf(AttesaAcquisto.class, partita.getStato());
        assertNotNull(partita.getStato().getPartita());
    }
}
