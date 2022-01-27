package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import lombok.Builder;

@Builder
public class LancioDadi extends StatoPartita {

    @Override
    public void onTimeout() {
        partita.turnoStandard();
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
        partita.setStato(AttesaAcquisto.builder()
                .daAcquistare(richiediAcquisto.getProprieta())
                .build());
        partita.getStato().esegui();
    }

    @Override
    public void onAzioneCasella(PagaAffitto pagaAffitto) {
        partita.setStato(AttesaAffitto.builder()
                .proprieta(pagaAffitto.getProprieta())
                .build());
        partita.getStato().esegui();
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore) {
        partita.setStato(AttesaPrigione.builder().build());
        partita.getStato().esegui();
    }

    @Override
    public void onAzioneCasella(PescaImprevisto pescaImpervisto) {
        partita.getMazzo().pescaImprevisto(partita.getTurnoCorrente().getGiocatore());
    }

    @Override
    public void onAzioneCasella(PescaProbabilita pescaProbabilita) {
        partita.getMazzo().pescaProbabilita(partita.getTurnoCorrente().getGiocatore());
    }

    @Override
    public void onAzioneCasella(AggiungiDenaro aggiungiDenaro) {
        try {
            partita.getTurnoCorrente().getGiocatore().aggiungiDenaro(aggiungiDenaro.getImporto());
            partita.turnoStandard();
        }catch (Exception e){
            int soldiDaPagare = aggiungiDenaro.getImporto();
            partita.setStato(AttesaFallimento.builder().soldiDaPagare(-soldiDaPagare).build());
        }
    }
}
