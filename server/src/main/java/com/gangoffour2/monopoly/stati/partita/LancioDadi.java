package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import lombok.Builder;

@Builder
public class LancioDadi extends StatoPartita {

    @Override
    public void onTimeout() {
        partita.turnoStandard();
    }

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
    }

    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi) {
        partita.turnoStandard();
        partita.fermaAttesa();
    }

    @Override
    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(RichiediAcquisto richiediAcquisto) {
        partita.setStato(AttesaAcquisto.builder().build());
        partita.getStato().esegui(richiediAcquisto);
    }

    @Override
    public void onAzioneCasella(PagaAffitto pagaAffitto) {
        partita.setStato(AttesaAffitto.builder().build());
        partita.getStato().esegui(pagaAffitto);
    }

    @Override
    public void esegui(AttesaLancioDadi attesaLancioDadi) {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore) {
        partita.setStato(AttesaPrigione.builder().build());
        partita.getStato().esegui(arrestaGiocatore);
    }

    @Override
    public void onAzioneCasella(PescaImprevisto pescaImpervisto) {
        partita.getMazzo().pescaImprevisto(partita.getTurnoCorrente().getGiocatore());
    }

    @Override
    public void onAzioneCasella(PescaProbabilita pescaProbabilita) {
        partita.getMazzo().pescaProbabilita(partita.getTurnoCorrente().getGiocatore());
    }
}
