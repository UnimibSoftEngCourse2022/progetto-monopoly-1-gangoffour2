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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.gangoffour2.monopoly.MonopolyApplicationTests.creaPartita;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
class TestProprieta {




    @ParameterizedTest
    @ValueSource(ints = {1, 5, 12})
    void acquistoProprieta(int posizione) throws GiocatoreEsistenteException, IOException {
        Partita partita = creaPartita();
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
    void ipotecaTerreno() throws GiocatoreEsistenteException, IOException {
        Partita p = creaPartita();
        p.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = p.getGiocatori().get(0);
        Casella c = p.getTabellone().getCaselle().get(1);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(TerrenoIpotecato.class, c.getEvento());
    }

    @Test
    void ipotecaSocieta() throws GiocatoreEsistenteException, IOException {

        Partita p = creaPartita();
        p.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = p.getGiocatori().get(0);
        Casella c = p.getTabellone().getCaselle().get(12);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(SocietaIpotecata.class, c.getEvento());
    }


    @Test
    void ipotecaStazione() throws GiocatoreEsistenteException, IOException {
        Partita p = creaPartita();
        p.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = p.getGiocatori().get(0);
        Casella c = p.getTabellone().getCaselle().get(5);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(StazioneIpotecata.class, c.getEvento());
    }


    @Test
    void testAsta() throws GiocatoreEsistenteException, IOException {
        Partita p = creaPartita();
        p.onAzioneGiocatore(EntraInPartita.builder().giocatore(Giocatore.builder().nick("Ciao").build()).build());
        Giocatore g = p.getGiocatori().get(0);
        p.getTabellone().muoviGiocatore(g, 1);

        g.getCasellaCorrente().arrivo();
        p.getListenerTimeoutEventi().stopTimeout();
        System.out.println(p.getStato().getTipo());
        p.getStato().onTimeout();
        System.out.println(p.getStato().getTipo());

        p.onAzioneGiocatore(Offerta.builder().valore(100).giocatore(g).build());
        p.getListenerTimeoutEventi().stopTimeout();
        p.getStato().onTimeout();

        assertEquals(1, g.getProprietaPossedute().size());
    }


}
