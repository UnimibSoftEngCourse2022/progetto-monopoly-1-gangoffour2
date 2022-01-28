package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.Turno;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.CartaModificaDenaro;
import com.gangoffour2.monopoly.model.carta.CartaMuoviPosizioneACasella;
import com.gangoffour2.monopoly.model.carta.CartaMuoviPosizioneIntero;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.model.casella.VaiInPrigione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        TestProprieta.fakeTiro(partita, g, 3);
        Carta c = CartaMuoviPosizioneIntero.builder().movimento(10).tabellone(partita.getTabellone()).build();
        c.effetto(g);
        assertEquals(partita.getTabellone().getCasella(10), g.getCasellaCorrente());
    }

    @Test
    void muoviPosizioneACasella() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        TestProprieta.fakeTiro(partita, g, 3);
        Casella casella = partita.getTabellone().getCasella(10);
        Carta c = CartaMuoviPosizioneACasella.builder().casella(casella).tabellone(partita.getTabellone()).build();
        c.effetto(g);
        assertEquals(casella, g.getCasellaCorrente());
    }

    @Test
    void modificaDenaro() {
        Giocatore g = Giocatore.builder().conto(1000).nick("Ciao").build();
        Carta c = CartaModificaDenaro.builder().denaro(-10).build();
        c.effetto(g);
        assertEquals(990, g.getConto());
    }
}
