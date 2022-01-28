package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Turno;
import lombok.Builder;

@Builder
public class AttesaPrigione extends StatoPartita {
    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi) {
        partita.fermaAttesa();
        lanciaDadi.getGiocatore().getCasellaCorrente().onAzioneGiocatore(lanciaDadi);
    }

    @Override
    public void onAzioneGiocatore(Paga paga) {
        partita.fermaAttesa();
        paga.getGiocatore().getCasellaCorrente().onAzioneGiocatore(paga);
    }

    @Override
    public void esegui() {
        Turno turno = partita.getTurnoCorrente();
        Giocatore g = turno.getGiocatore();

        if (g.haCartaEsciGratis()) {
            partita.getMazzo().utilizzaCarta(g);
            //Notifica client Tizio ha usato carta
            partita.setStato(LancioDadi.builder().build());
            partita.continuaTurno();
            partita.broadcast();
        }
    }
}
