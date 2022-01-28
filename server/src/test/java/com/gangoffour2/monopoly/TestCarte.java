package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.CartaModificaDenaro;
import com.gangoffour2.monopoly.model.carta.CartaMuoviPosizioneIntero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestCarte {

    IPartita partita;

    @BeforeEach
    void setup() throws GiocatoreEsistenteException, IOException {
        partita = MonopolyApplicationTests.creaPartita();
    }

    @Test
    void muovi() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        Carta c = CartaMuoviPosizioneIntero.builder().movimento(1).tabellone(partita.getTabellone()).build();
        c.effetto(g);
        assertEquals(g.getCasellaCorrente(), partita.getTabellone().getCasella(1));
    }

    @Test
    void modificaDenaro() {
        Giocatore g = Giocatore.builder().conto(1000).nick("Ciao").build();
        Carta c = CartaModificaDenaro.builder().denaro(-10).build();
        c.effetto(g);
        assertEquals(990, g.getConto());
    }
}
