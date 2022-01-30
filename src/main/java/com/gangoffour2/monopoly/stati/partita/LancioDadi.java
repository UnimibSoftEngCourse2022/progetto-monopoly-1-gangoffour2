package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.*;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Builder;

@Builder
public class LancioDadi extends StatoPartita {

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onTimeout() {
        partita.fermaAttesa();
        partita.continua(this);
    }

    @Override
    public void riprendi(AttesaFallimento attesaFallimento) {
        Giocatore giocatore = partita.getTurnoCorrente().getGiocatore();
        giocatore.aggiungiDenaro(attesaFallimento.getSoldiDaPagare());
        partita.continua(this);
    }

    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi) {
        partita.fermaAttesa();
        partita.continua(this);
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
        if(pagaAffitto.getProprieta().getProprietario().equals(partita.getTurnoCorrente().getGiocatore())) {
            partita.continua(this);
        } else {
            partita.setStato(AttesaAffitto.builder()
                    .proprieta(pagaAffitto.getProprieta())
                    .build());
            partita.getStato().esegui();
        }
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore) {
        Giocatore giocatore = partita.getTurnoCorrente().getGiocatore();
        if (giocatore.haCartaEsciGratis()) {
            partita.getMazzo().utilizzaCarta(giocatore);
            partita.setStato(LancioDadi.builder().build());
            partita.continua(this);
        }
        else {
            partita.getTabellone().muoviAProssimaCasellaSemplice(giocatore, casella -> casella.getTipo().equals("Prigione"));
            partita.setStato(FineTurno.builder().build());
            partita.cambiaTurno();
        }
    }

    @Override
    public void onAzioneCasella(PescaImprevisto pescaImpervisto) {
        partita.broadcast(partita.getMazzo().nextImprevisto(), "carta");
        try {
            partita.getMazzo().pescaImprevisto(partita.getTurnoCorrente().getGiocatore());
        }catch (ModificaDenaroException e){
            fallimentoCarta(e.getSoldiDaPagare());
        }
    }

    @Override
    public void onAzioneCasella(PescaProbabilita pescaProbabilita) {
        partita.broadcast(partita.getMazzo().nextProbabilita(), "carta");
        try {
            partita.getMazzo().pescaProbabilita(partita.getTurnoCorrente().getGiocatore());
        }catch (ModificaDenaroException e){
            fallimentoCarta(e.getSoldiDaPagare());
        }
    }

    private void fallimentoCarta(int soldiDaPagare){
        partita.memorizzaStato(this);
        partita.setStato(
                AttesaFallimento.builder()
                        .soldiDaPagare(soldiDaPagare)
                        .build()
        );
    }

    @Override
    public void onAzioneCasella(AggiungiDenaro aggiungiDenaro) {
        try {
            partita.getTurnoCorrente().getGiocatore().aggiungiDenaro(aggiungiDenaro.getImporto());
            partita.continua(this);
        }catch (Exception e){
            int soldiDaPagare = aggiungiDenaro.getImporto();
            partita.setStato(AttesaFallimento.builder().soldiDaPagare(-soldiDaPagare).build());
        }
    }
}
