package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.ArrestaGiocatore;
import com.gangoffour2.monopoly.azioni.casella.AttesaLancioDadi;
import com.gangoffour2.monopoly.azioni.casella.PagaAffitto;
import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import lombok.Builder;

@Builder
public class LancioDadi extends StatoPartita {

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca){
        partita.getCodaAzioniGiocatore().removeLast();
    }

    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi){
        partita.getCodaAzioniGiocatore().removeLast();
        partita.turnoNormale();
    }


    @Override
    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) throws InterruptedException {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(RichiediAcquisto richiediAcquisto){
        partita.setStato(AttesaAcquisto.builder().build());
    }

    @Override
    public void onAzioneCasella(PagaAffitto pagaAffitto){

    }

    @Override
    public void esegui(AttesaLancioDadi attesaLancioDadi) throws InterruptedException {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore){

    }


}
