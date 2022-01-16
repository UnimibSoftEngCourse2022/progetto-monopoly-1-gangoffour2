package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import lombok.Builder;

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
}
