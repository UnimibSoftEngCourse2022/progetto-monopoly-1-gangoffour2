package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.TerminaTurno;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.casella.Societa;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.stati.partita.AttesaAcquisto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
class TestPrigione {

    IPartita partita;

    @BeforeEach
    public void setup() throws GiocatoreEsistenteException, IOException {
        partita = MonopolyApplicationTests.creaPartita();
    }

    @Test
    void muoviPosizioneACasellaAcquistata() {
        Giocatore g = Giocatore.builder().nick("Ciao").build();
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(g).build());
        MonopolyApplicationTests.fakeTiro(partita, g, 30);
        partita.continuaTurno();
        MonopolyApplicationTests.fakeTiro(partita, partita.getTurnoCorrente().getGiocatore(), 10);
        partita.continuaTurno();
        partita.onAzioneGiocatore(TerminaTurno.builder().giocatore(partita.getTurnoCorrente().getGiocatore()).build());
        partita.getConfig().setFacceDadi(1);
        partita.onAzioneGiocatore(LanciaDadi.builder().giocatore(g).build());
        assertInstanceOf(AttesaAcquisto.class, partita.getStato());
        assertInstanceOf(Societa.class, g.getCasellaCorrente());
    }
}
