package com.gangoffour2.monopoly;

import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.model.Giocatore;
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
    void cambioStatiTerreno(){

        Terreno t = Terreno.builder().build();

        t.setEvento(TerrenoNonAcquistato.builder().build());
        ((EventoTerreno)t.getEvento()).setTerreno(t);
        Giocatore giocatore = Giocatore.builder().nick("Ciao").build();

        t.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(giocatore).build());
        assertTrue(t.getEvento() instanceof TerrenoAcquistato);

        t.onAzioneGiocatore(Ipoteca.builder().build());
        assertTrue(t.getEvento() instanceof TerrenoIpotecato);
    }

    @Test
    void camobioStatiStazione(){
        Stazione stazione = Stazione.builder().nome("Stazione sud").build();
        stazione.setEvento(StazioneNonAcquistata.builder().build());
        ((EventoStazione)stazione.getEvento()).setStazione(stazione);
        Giocatore giocatore = Giocatore.builder().nick("Ciao").build();

        stazione.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(giocatore).build());
        assertTrue(stazione.getEvento() instanceof StazioneAcquistata);

        stazione.onAzioneGiocatore(Ipoteca.builder().build());
        assertTrue(stazione.getEvento() instanceof StazioneIpotecata);
    }

    @Test
    void cambioStatiSocieta(){
        Societa societa = Societa.builder().nome("Societa bella").build();
        societa.setEvento(SocietaNonAcquistata.builder().build());
        ((EventoSocieta)societa.getEvento()).setSocieta(societa);

        Giocatore giocatore = Giocatore.builder().nick("Ciao").build();
        societa.onAzioneGiocatore(AcquistaProprieta.builder().giocatore(giocatore).build());
        assertTrue(societa.getEvento() instanceof  SocietaAcquistata);

        societa.onAzioneGiocatore(Ipoteca.builder().giocatore(giocatore).build());
        assertTrue(societa.getEvento() instanceof SocietaIpotecata);
    }
}
