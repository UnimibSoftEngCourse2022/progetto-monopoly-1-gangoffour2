package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.casella.*;
import com.gangoffour2.monopoly.stati.casella.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MonopolyApplicationTests {

	@Test
	void contextLoads() {
	}

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

}
