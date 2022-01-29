package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.casella.Societa;
import com.gangoffour2.monopoly.model.casella.Stazione;
import com.gangoffour2.monopoly.model.casella.Terreno;
import com.gangoffour2.monopoly.stati.casella.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TestEventiCaselle {

    @Test
    void cambioStatiTerreno() {
        Terreno t = Terreno.builder().build();

        t.setStato(TerrenoNonAcquistato.builder().build());
        ((StatoTerreno) t.getStato()).setTerreno(t);
        Giocatore giocatore = Giocatore.builder().nick("Ciao").build();

        t.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(giocatore).build());
        assertTrue(t.getStato() instanceof TerrenoAcquistato);

        t.onAzioneGiocatore(Ipoteca.builder().build());
        assertTrue(t.getStato() instanceof TerrenoIpotecato);
    }

    @Test
    void cambioStatiStazione() {
        Stazione stazione = Stazione.builder().nome("Stazione sud").build();
        stazione.setStato(StazioneNonAcquistata.builder().build());
        ((StatoStazione) stazione.getStato()).setStazione(stazione);
        Giocatore giocatore = Giocatore.builder().nick("Ciao").build();

        stazione.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(giocatore).build());
        assertTrue(stazione.getStato() instanceof StazioneAcquistata);

        stazione.onAzioneGiocatore(Ipoteca.builder().build());
        assertTrue(stazione.getStato() instanceof StazioneIpotecata);
    }

    @Test
    void cambioStatiSocieta() {
        Societa societa = Societa.builder().nome("Societa bella").build();
        societa.setStato(SocietaNonAcquistata.builder().build());
        ((StatoSocieta) societa.getStato()).setSocieta(societa);

        Giocatore giocatore = Giocatore.builder().nick("Ciao").build();
        societa.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(giocatore).build());
        assertTrue(societa.getStato() instanceof SocietaAcquistata);

        societa.onAzioneGiocatore(Ipoteca.builder().giocatore(giocatore).build());
        assertTrue(societa.getStato() instanceof SocietaIpotecata);
    }
}
