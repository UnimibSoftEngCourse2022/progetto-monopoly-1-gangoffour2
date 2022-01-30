package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.model.casella.Terreno;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.IPartita;
import com.gangoffour2.monopoly.model.Turno;
import com.gangoffour2.monopoly.model.casella.Casella;
import com.gangoffour2.monopoly.stati.casella.SocietaIpotecata;
import com.gangoffour2.monopoly.stati.casella.StazioneIpotecata;
import com.gangoffour2.monopoly.stati.casella.TerrenoAcquistato;
import com.gangoffour2.monopoly.stati.casella.TerrenoIpotecato;
import com.gangoffour2.monopoly.stati.partita.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestProprieta {

    IPartita partita;

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
        giocatore.getCasellaCorrente().arrivo(g);
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

        assertInstanceOf(TerrenoIpotecato.class, c.getStato());
    }

    @Test
    void ipotecaSocieta() throws GiocatoreEsistenteException {
        partita.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = partita.getGiocatori().get(0);
        Casella c = partita.getTabellone().getCasella(12);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(SocietaIpotecata.class, c.getStato());
    }

    @Test
    void ipotecaStazione() throws GiocatoreEsistenteException {
        partita.aggiungiGiocatore(Giocatore.builder().nick("sdf").build());
        Giocatore g = partita.getGiocatori().get(0);
        Casella c = partita.getTabellone().getCasella(5);
        c.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        c.onAzioneGiocatore(Ipoteca.builder().giocatore(g).build());

        assertInstanceOf(StazioneIpotecata.class, c.getStato());
    }

    @Test
    void testAsta() {
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(Giocatore.builder().nick("Ciao").build()).build());
        Giocatore g = partita.getGiocatori().get(0);
        partita.getTabellone().muoviGiocatore(g, 1);

        g.getCasellaCorrente().arrivo(g);
        partita.getListenerTimeoutEventi().stopTimeout();
        System.out.println(partita.getStato().getTipo());
        partita.getStato().onTimeout();
        System.out.println(partita.getStato().getTipo());

        partita.onAzioneGiocatore(Offerta.builder().valore(100).giocatore(g).build());
        partita.getListenerTimeoutEventi().stopTimeout();
        partita.getStato().onTimeout();

        assertEquals(1, g.getProprietaPossedute().size());
    }

    @Test
    void testFallimento() {
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(Giocatore.builder().nick("Prova").build()).build());
        Giocatore g = partita.getTurnoCorrente().getGiocatore();
        partita.getTabellone().muoviGiocatore(g, 1);

        g.getCasellaCorrente().arrivo(g);
        partita.getListenerTimeoutEventi().stopTimeout();
        partita.getStato().onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        partita.setStato(FineTurno.builder().build());
        partita.onAzioneGiocatore(TerminaTurno.builder().build());

        Giocatore povero = partita.getTurnoCorrente().getGiocatore();
        assertNotEquals(povero, g);
        TestProprieta.fakeTiro(partita, povero, 1);
        povero.getCasellaCorrente().arrivo(g);
        povero.setConto(1);
        assertEquals(1, povero.getConto());
        assertEquals("AttesaAffitto", partita.getStato().getTipo());
        partita.onAzioneGiocatore(Paga.builder().giocatore(povero).build());
        partita.getListenerTimeoutEventi().stopTimeout();

        assertEquals("AttesaFallimento", partita.getStato().getTipo());
    }

    @Test
    void testPagamentoFallimento(){
        this.testFallimento();
        assertEquals("AttesaFallimento", partita.getStato().getTipo());
        AttesaFallimento attesaFallimento = (AttesaFallimento) partita.getStato();
        Giocatore povero = partita.getTurnoCorrente().getGiocatore();
        Giocatore creditore = partita.getGiocatori().get(1);
        int soldiCreditorePrimaDelFallimento = creditore.getConto();
        assertNotEquals(povero, creditore);

        Terreno c2 = (Terreno) partita.getTabellone().getCasella(3);
        c2.setProprietario(povero);
        c2.setStato(TerrenoAcquistato.builder().terreno(c2).build());
        povero.getProprietaPossedute().add(c2);
        povero.setConto(1000);
        attesaFallimento.onAzioneGiocatore(Ipoteca.builder().giocatore(povero).proprieta(c2).build());
        assertEquals(1000 + c2.getIpoteca() - attesaFallimento.getSoldiDaPagare(), povero.getConto());

        assertEquals("FineTurno", partita.getStato().getTipo());
        assertNotEquals(0, attesaFallimento.getSoldiDaPagare());

        assertEquals(soldiCreditorePrimaDelFallimento + attesaFallimento.getSoldiDaPagare(), creditore.getConto());
    }

    @Test
    void testAutoPagamento(){
        this.acquistoProprieta(1);

        Giocatore g = partita.getTurnoCorrente().getGiocatore();
        g.setCasellaCorrente(partita.getTabellone().getCasella(0));
        partita.getTabellone().muoviGiocatore(g, 1);
        partita.setStato(LancioDadi.builder().build());
        partita.getTabellone().getCasella(1).arrivo(g);

        assertEquals("FineTurno", partita.getStato().getTipo());
    }

    static void fakeTiro(IPartita partita, Giocatore giocatore, int spostamento) {
        ArrayList<Integer> dadi = new ArrayList<>();
        dadi.add(spostamento - 1);
        dadi.add(1);
        Turno t =   Turno.builder()
                .casellaDaVisitare(spostamento)
                .lanciConsecutivi(1)
                .valoreDadi(dadi)
                .giocatore(giocatore)
                .build();
        t.inizializzaDadi(2);
        partita.setTurnoCorrente(t);
    }

    @Test
    void testPagamentoAffitto() {
        partita.onAzioneGiocatore(EntraInPartita.builder().giocatore(Giocatore.builder().nick("Prova").build()).build());
        Giocatore g = partita.getTurnoCorrente().getGiocatore();
        TestProprieta.fakeTiro(partita, g, 1);

        g.getCasellaCorrente().arrivo(g);
        partita.getListenerTimeoutEventi().stopTimeout();
        partita.getStato().onAzioneGiocatore(AcquistaProprieta.builder().giocatore(g).build());
        partita.setStato(FineTurno.builder().build());
        partita.onAzioneGiocatore(TerminaTurno.builder().build());

        Giocatore debitore = partita.getTurnoCorrente().getGiocatore();
        TestProprieta.fakeTiro(partita, debitore, 1);
        debitore.getCasellaCorrente().arrivo(g);
        assertEquals("AttesaAffitto", partita.getStato().getTipo());
        partita.onAzioneGiocatore(Paga.builder().giocatore(debitore).build());
        partita.getListenerTimeoutEventi().stopTimeout();

        assertEquals("FineTurno", partita.getStato().getTipo());
    }
}
