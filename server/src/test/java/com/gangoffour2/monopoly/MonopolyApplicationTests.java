package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.*;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.stati.casella.*;
import com.gangoffour2.monopoly.stati.partita.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MonopolyApplicationTests {

	@Test
	void cambioStatiTerreno(){

		Terreno t = Terreno.builder().build();

		t.setState(TerrenoNonAcquistato.builder().build());
		t.getStatoCorrente().setTerreno(t);

		t.onAzioneGiocatore(AcquistaProprieta.builder().build());
		assertTrue(t.getStatoCorrente() instanceof TerrenoAcquistato);

		t.onAzioneGiocatore(Ipoteca.builder().build());
		assertTrue(t.getStatoCorrente() instanceof TerrenoIpotecato);
	}


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
		partita.getTabellone().muoviGiocatore(g, 9);
		assertEquals("Terreno", g.getCasellaCorrente().getNome());

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

		ArrayList<Casella> caselle = new ArrayList<>();
		caselle.add(Via.builder().nome("Via").build());
		caselle.add(Prigione.builder().nome("Prigione").build());
		caselle.add(Tassa.builder().nome("Tassa").build());
		caselle.add(Stazione.builder().nome("Stazione").build());
		Terreno t = Terreno.builder()
				.nome("Terreno")
				.statoCorrente(TerrenoNonAcquistato
						.builder()
						.build())
				.build();
		t.getStatoCorrente().setTerreno(t);
		caselle.add(t);

		Partita partita = Partita.builder()
				.stato(new InizioTurno())
				.config(conf)
				.tabellone(tabellone)
				.build();

		partita.getStato().setPartita(partita);

		caselle.forEach(casella -> casella.aggiungi(partita));

		tabellone.setCaselle(caselle);

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
