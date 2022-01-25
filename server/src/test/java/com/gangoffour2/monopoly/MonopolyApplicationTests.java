package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.eccezioni.GiocatoreEsistenteException;
import com.gangoffour2.monopoly.model.*;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.services.FactoryPartita;
import com.gangoffour2.monopoly.stati.partita.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonopolyApplicationTests {

	static void muoviGiocatore(Partita p, Giocatore giocatore) {
		p.getTabellone().muoviGiocatore(giocatore, 1);
		assertEquals(giocatore.getCasellaCorrente(), p.getTabellone().getCaselle().get(1));
	}


	@Test
	void creazionePartita() throws GiocatoreEsistenteException, IOException {
		Partita p = creaPartita();
		assertEquals("CancaroMan", p.getTurnoCorrente().getGiocatore().getNick());
		assertNotNull(p.getStato());
		assertNotNull(p.getStato().getPartita());
		assertNotNull(p.getTabellone());
		assertEquals(2, p.getGiocatori().size());
	}

	@Test
	void cambioStatiPartita() throws GiocatoreEsistenteException, IOException {
		Partita partita = creaPartita();
		Giocatore g = partita.getTurnoCorrente().getGiocatore();
		partita.setStato(LancioDadi.builder().build());
		partita.getTabellone().muoviGiocatore(g, 9);
		assertEquals("Viale Vesuvio", g.getCasellaCorrente().getNome());

		g.getCasellaCorrente().arrivo();
		assertTrue(partita.getStato() instanceof AttesaAcquisto);
		assertNotNull(partita.getStato().getPartita());
	}


	static Partita creaPartita() throws GiocatoreEsistenteException, IOException {
		Configurazione conf = Configurazione.builder()
				.difficolta(Configurazione.Difficolta.MEDIUM)
				.randomCaselle(true)
				.soldiIniziali(5000)
				.randomEconomia(true)
				.numeroGiocatori(2)
				.build();

		Partita partita = FactoryPartita.getInstance().creaPartita(conf);

		partita.getStato().setPartita(partita);

		ArrayList<Casella> caselle = partita.getTabellone().getCaselle();

		Giocatore g = Giocatore.builder()
				.nick("CancaroMan")
				.conto(partita.getConfig().getSoldiIniziali())
				.casellaCorrente(caselle.get(0))
				.build();
		partita.aggiungiGiocatore(g);
		partita.setTurnoCorrente(Turno.builder().giocatore(g).build());

		return partita;
	}
}
