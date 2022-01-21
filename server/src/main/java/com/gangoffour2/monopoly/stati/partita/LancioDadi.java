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
    public boolean onAzioneGiocatore(Ipoteca ipoteca){
        return true;
    }

    @Override
    public boolean onAzioneGiocatore(LanciaDadi lanciaDadi){
        partita.turnoStandard();
        return true;
    }


    @Override
    public void onAzioneCasella(AttesaLancioDadi attesaLancioDadi) {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(RichiediAcquisto richiediAcquisto){
        partita.setStato(AttesaAcquisto.builder().build());
        partita.getStato().esegui(richiediAcquisto);
    }

    @Override
    public void onAzioneCasella(PagaAffitto pagaAffitto){
        partita.setStato(AttesaAffitto.builder().build());
        partita.getStato().esegui(pagaAffitto);
    }

    @Override
    public void esegui(AttesaLancioDadi attesaLancioDadi)  {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneCasella(ArrestaGiocatore arrestaGiocatore){
        partita.setStato(AttesaPrigione.builder().build());
        partita.getStato().esegui(arrestaGiocatore);
    }


}
