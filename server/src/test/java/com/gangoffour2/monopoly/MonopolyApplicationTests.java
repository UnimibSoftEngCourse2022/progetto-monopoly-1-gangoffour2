package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.*;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.services.FactoryPartita;
import com.gangoffour2.monopoly.stati.casella.*;
import com.gangoffour2.monopoly.stati.partita.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonopolyApplicationTests {



	@Test
	void creazionePartita(){
		Partita p = creaPartita();
		assertEquals("CancaroMan", p.getTurnoGiocatore().getNick());
		assertNotNull(p.getStato());
		assertNotNull(p.getStato().getPartita());
		assertNotNull(p.getTabellone());
		assertEquals(1, p.getGiocatori().size());
	}

	@Test
	void cambioStatiPartita(){
		Partita partita = creaPartita();
		Giocatore g = partita.getTurnoGiocatore();
		partita.setStato(LancioDadi.builder().build());
		partita.getTabellone().muoviGiocatore(g, 9);
		assertEquals("Garda Lake1", g.getCasellaCorrente().getNome());

		g.getCasellaCorrente().arrivo();
		assertTrue(partita.getStato() instanceof AttesaAcquisto);
		assertNotNull(partita.getStato().getPartita());
	}


	Partita creaPartita(){
		Tabellone tabellone = Tabellone.builder().build();
		Configurazione conf = Configurazione.builder()
				.difficolta(Configurazione.Difficolta.MEDIUM)
				.randomCaselle(true)
				.soldiIniziali(5000)
				.randomEconomia(true)
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
		partita.setTurnoGiocatore(g);

		return partita;
	}
}
