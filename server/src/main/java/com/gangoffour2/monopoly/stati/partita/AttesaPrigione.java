package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.ArrestaGiocatore;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Turno;
import lombok.Builder;

@Builder
public class AttesaPrigione extends StatoPartita {
    @Override
    public void onAzioneGiocatore(Paga paga) {
        paga.getGiocatore().getCasellaCorrente().onAzioneGiocatore(paga);
        partita.fermaAttesa();
    }

    @Override
    public void esegui(ArrestaGiocatore ag) {
        Turno turno = partita.getTurnoCorrente();
        Giocatore g = turno.getGiocatore();
        if (g.haCartaEsciGratis()) {
            partita.getMazzo().utilizzaCarta(g);
            //Notifica client Tizio ha usato carta
            partita.setStato(LancioDadi.builder().build());
            partita.turnoStandard();
            partita.broadcast();
        }
    }
}
