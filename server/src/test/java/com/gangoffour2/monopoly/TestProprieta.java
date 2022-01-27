package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Partita;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.stati.casella.SocietaIpotecata;
import com.gangoffour2.monopoly.stati.casella.StazioneIpotecata;
import com.gangoffour2.monopoly.stati.casella.TerrenoIpotecato;
import com.gangoffour2.monopoly.stati.partita.AttesaAcquisto;
import com.gangoffour2.monopoly.stati.partita.LancioDadi;
import com.gangoffour2.monopoly.stati.partita.Lobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
class TestProprieta {

    Partita partita;

    @BeforeEach
    public void setup() throws GiocatoreEsistenteException, IOException {
        partita = MonopolyApplicationTests.creaPartita();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 12})
    void acquistoProprieta(int posizione) {
        assertInstanceOf(Lobby.class, partita.getStato());
        Giocatore g = Giocatore.builder()
                .nick("Prova")
                .conto(partita.getConfig().getSoldiIniziali())
                .build();

        EntraInPartita azione = EntraInPartita.builder()
                .giocatore(g)
                .build();
        partita.onAzioneGiocatore(azione);
        assertInstanceOf(LancioDadi.class, partita.getStato());
        Giocatore giocatore = partita.getTurnoCorrente().getGiocatore();

        partita.getTabellone().muoviGiocatore(giocatore, posizione);
        giocatore.getCasellaCorrente().arrivo();
        assertInstanceOf(AttesaAcquisto.class, partita.getStato());
        AcquistaProprieta acquistaProprieta = AcquistaProprieta.builder().giocatore(giocatore).build();

        partita.onAzioneGiocatore(acquistaProprieta);
        assertEquals(1, giocatore.getProprietaPossedute().size());
    }

    @Test
    void ipotecaTerreno() throws GiocatoreEsistenteException {
        partita.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = partita.getGiocatori().get(0);
        Casella c = partita.getTabellone().getCasella(1);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(TerrenoIpotecato.class, c.getEvento());
    }

    @Test
    void ipotecaSocieta() throws GiocatoreEsistenteException {
        partita.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = partita.getGiocatori().get(0);
        Casella c = partita.getTabellone().getCasella(12);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(SocietaIpotecata.class, c.getEvento());
    }


    @Test
    void ipotecaStazione() throws GiocatoreEsistenteException {
        partita.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = partita.getGiocatori().get(0);
        Casella c = partita.getTabellone().getCasella(5);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(StazioneIpotecata.class, c.getEvento());
    }


    @Test
    void testAsta() {
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(Giocatore.builder().nick("Ciao").build()).build());
        Giocatore g = partita.getGiocatori().get(0);
        partita.getTabellone().muoviGiocatore(g, 1);

        g.getCasellaCorrente().arrivo();
        partita.getListenerTimeoutEventi().stopTimeout();
        System.out.println(partita.getStato().getTipo());
        partita.getStato().onTimeout();
        System.out.println(partita.getStato().getTipo());

        partita.onAzioneGiocatore(Offerta.builder().valore(100).giocatore(g).build());
        partita.getListenerTimeoutEventi().stopTimeout();
        partita.getStato().onTimeout();

        assertEquals(1, g.getProprietaPossedute().size());
    }
}
