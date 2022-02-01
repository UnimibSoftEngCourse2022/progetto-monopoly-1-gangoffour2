package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.carta.*;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.model.casella.Terreno;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.stati.casella.TerrenoAcquistato;
import com.gangoffour2.monopoly.stati.partita.AttesaAffitto;
import com.gangoffour2.monopoly.stati.partita.FineTurno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestCarte {

    IPartita partita;

    @BeforeEach
    void setup() throws GiocatoreEsistenteException, IOException {
        partita = MonopolyApplicationTests.creaPartita();
    }

    @Test
    void muoviPosizioneIntero() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        MonopolyApplicationTests.fakeTiro(partita, g, 3);
        Carta c = CartaMuoviPosizioneIntero.builder().movimento(10).tabellone(partita.getTabellone()).build();
        c.effetto(g);
        assertEquals(partita.getTabellone().getCasella(10), g.getCasellaCorrente());
    }

    @Test
    void muoviPosizioneACasella() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        MonopolyApplicationTests.fakeTiro(partita, g, 3);
        Casella casella = partita.getTabellone().getCasella(10);
        Carta c = CartaMuoviPosizioneACasella.builder().casella(casella).tabellone(partita.getTabellone()).build();
        c.effetto(g);
        assertEquals(casella, g.getCasellaCorrente());
    }

    @Test
    void muoviPosizioneACasellaAcquistata() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        Giocatore g2 = Giocatore.builder().nick("Player2").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        MonopolyApplicationTests.fakeTiro(partita, g, 3);
        Casella casella = partita.getTabellone().getCasella(9);
        ((Terreno) casella).setProprietario(g2);
        casella.setStato(TerrenoAcquistato.builder().terreno((Terreno) casella).build());
        Carta c = CartaMuoviPosizioneACasella.builder().casella(casella).tabellone(partita.getTabellone()).build();
        c.effetto(g);
        assertInstanceOf(AttesaAffitto.class, partita.getStato());
    }

    @Test
    void usaEsciGratisDiPrigione() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        Carta c = CartaEsciGratisPrigione.builder().tabellone(partita.getTabellone()).build();
        c.effetto(g);
        MonopolyApplicationTests.fakeTiro(partita, g, 30);
        partita.continuaTurno();
        assertFalse(g.haCartaEsciGratis());
    }

    @Test
    void modificaDenaro() {
        Giocatore g = Giocatore.builder().conto(1000).nick("Ciao").build();
        Carta c = CartaModificaDenaro.builder().denaro(-10).build();
        c.effetto(g);
        assertEquals(990, g.getConto());
    }

}
