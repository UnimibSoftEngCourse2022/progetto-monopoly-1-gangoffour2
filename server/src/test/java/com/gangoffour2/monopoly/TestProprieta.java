package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.EntraInPartita;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.stati.partita.AttesaAcquisto;
import com.gangoffour2.monopoly.stati.partita.LancioDadi;
import com.gangoffour2.monopoly.stati.partita.Lobby;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
public class TestProprieta {

    @Test
    void acquistoProprieta() throws GiocatoreEsistenteException, IOException {
        Partita partita = MonopolyApplicationTests.creaPartita();
        assertInstanceOf(Lobby.class, partita.getStato());
        Giocatore giocatore = partita.getTurnoCorrente().getGiocatore();
        Giocatore g = Giocatore.builder()
                .nick("Prova")
                .conto(partita.getConfig().getSoldiIniziali())
                .casellaCorrente(partita.getTabellone().getCaselle().get(0))
                .build();

        EntraInPartita azione = EntraInPartita.builder()
                .giocatore(g)
                .build();
        partita.onAzioneGiocatore(azione);
        assertInstanceOf(LancioDadi.class, partita.getStato());

        Casella proprieta = partita.getTabellone().getCaselle().get(1);
        partita.getTurnoCorrente().getGiocatore().setCasellaCorrente(proprieta);
        System.out.println(partita.getStato().getTipo());
        proprieta.arrivo();
        assertInstanceOf(AttesaAcquisto.class, partita.getStato());

        giocatore = partita.getTurnoCorrente().getGiocatore();
        AcquistaProprieta acquistaProprieta = AcquistaProprieta.builder().giocatore(giocatore).build();
        partita.onAzioneGiocatore(acquistaProprieta);
        assertEquals(giocatore.getProprietaPossedute().size(), 1);
    }

}
