package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import lombok.Builder;

@Builder
public class AttesaPrigione extends StatoPartita {
    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi){

    }

    @Override
    public void onAzioneGiocatore(Paga paga){

    }
}
