package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.casella.Stazione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestTabellone {

    IPartita partita;

    @BeforeEach
    void setup() throws GiocatoreEsistenteException, IOException {
        partita = MonopolyApplicationTests.creaPartita();
    }

    @Test
    void muoviAProssimaCasella() throws GiocatoreEsistenteException {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.aggiungiGiocatore(g);
        partita.getTabellone().muoviAProssimaCasella(g, (casella) -> casella.getNome().equals("Bastioni Gran Sasso"));
        assertEquals("Bastioni Gran Sasso", g.getCasellaCorrente().getNome());
    }

    @Test
    void muoviAProssimoTipoCasella() throws GiocatoreEsistenteException {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.aggiungiGiocatore(g);
        partita.getTabellone().muoviAProssimaCasella(g, (casella) -> casella.getClass().equals(Stazione.class));
        assertEquals("Stazione Sud", g.getCasellaCorrente().getNome());
    }
}
