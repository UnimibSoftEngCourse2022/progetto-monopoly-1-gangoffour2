package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.model.casella.Stazione;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestTabellone {
    @Test
    void muoviAProssimaCasella() throws GiocatoreEsistenteException, IOException {
        Partita p = MonopolyApplicationTests.creaPartita();
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        p.aggiungiGiocatore(g);
        p.getTabellone().muoviAProssimaCasella(g, (casella) -> casella.getNome().equals("Bastioni Gran Sasso"));
        assertEquals("Bastioni Gran Sasso", g.getCasellaCorrente().getNome());
    }

    @Test
    void muoviAProssimoTipoCasella() throws GiocatoreEsistenteException, IOException {
        Partita p = MonopolyApplicationTests.creaPartita();
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        p.aggiungiGiocatore(g);
        p.getTabellone().muoviAProssimaCasella(g, (casella) -> casella.getClass().equals(Stazione.class));
        assertEquals("Stazione Sud", g.getCasellaCorrente().getNome());
    }
}
