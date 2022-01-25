package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import com.gangoffour2.monopoly.eccezioni.OffertaInvalidaException;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Asta extends StatoPartita {
    com.gangoffour2.monopoly.model.Asta asta;
    Proprieta proprieta;

    @Override
    public void onTimeout() {

    }

    @Override
    public boolean onAzioneGiocatore(Offerta offerta){
        if(offerta.isValida()){
            this.asta.offri(offerta.getGiocatore(), offerta.getValore());
        }
        else{
            throw new OffertaInvalidaException(offerta.getGiocatore());
        }

        return true;
    }
}
