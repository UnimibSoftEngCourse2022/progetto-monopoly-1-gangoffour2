package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
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
        lanciaDadi.getGiocatore().getCasellaCorrente().onAzioneGiocatore(lanciaDadi);
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
