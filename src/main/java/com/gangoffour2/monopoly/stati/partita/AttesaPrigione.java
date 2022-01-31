package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.Turno;
import lombok.Builder;

@Builder
public class AttesaPrigione extends StatoPartita {

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }


    @Override
    public void onTimeout(){
        partita.cambiaTurno();
    }

    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi) {
        partita.fermaAttesa();
        partita.getTurnoCorrente().lancioDadi(partita.getConfig().getFacceDadi());
        if (partita.getTurnoCorrente().dadiUguali()){
            lanciaDadi.getGiocatore().getCasellaCorrente().onAzioneGiocatore(lanciaDadi);
            partita.setTurnoCorrente(Turno.builder()
                    .giocatore(partita.getTurnoCorrente().getGiocatore())
                    .build());
            partita.continua(this);
        }
        else {
            partita.cambiaTurno();
        }
    }

    @Override
    public void onAzioneGiocatore(Paga paga) {
        try {
            paga.getGiocatore().getCasellaCorrente().onAzioneGiocatore(paga);
            partita.fermaAttesa();
            partita.continua(this);
        }catch (ModificaDenaroException ignored){
            partita.cambiaTurno();
        }
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }
}
