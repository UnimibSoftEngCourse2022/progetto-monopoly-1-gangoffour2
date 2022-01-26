package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.ArrestaGiocatore;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.Turno;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
public class AttesaPrigione extends StatoPartita {
    @Override
    public boolean onAzioneGiocatore(LanciaDadi lanciaDadi){
        return true;
    }

    @Override
    public boolean onAzioneGiocatore(Paga paga){
        return true;
    }

    @Override
    public void esegui(ArrestaGiocatore ag){
        Turno turno = partita.getTurnoCorrente();
        Giocatore g = turno.getGiocatore();
        if(g.haCartaEsciGratis()){
            partita.getTabellone().getProbabilita().add(g.rimuoviEsciGratis());
            //Notifica client Tizio ha usato carta
            partita.setStato(LancioDadi.builder().build());
            partita.turnoStandard();
        }
        //g.getCasellaCorrente().on


    }
}