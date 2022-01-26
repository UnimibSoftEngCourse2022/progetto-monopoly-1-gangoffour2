package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.carta.CartaModificaDenaro;
import com.gangoffour2.monopoly.model.carta.CartaMuoviPosizioneIntero;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.gangoffour2.monopoly.MonopolyApplicationTests.creaPartita;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TestCarte {

    @Test
    void muovi() throws GiocatoreEsistenteException, IOException {
        Partita p = creaPartita();
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        p.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        Carta c = CartaMuoviPosizioneIntero.builder().movimento(1).tabellone(p.getTabellone()).build();
        c.effetto(g);
        assertEquals(g.getCasellaCorrente(), p.getTabellone().getCaselle().get(1));
    }

    @Test
    void modificaDenaro() {
        Giocatore g = Giocatore.builder().conto(1000).nick("Ciao").build();
        Carta c = CartaModificaDenaro.builder().denaro(-10).build();
        c.effetto(g);
        assertEquals(990, g.getConto());
    }
}
