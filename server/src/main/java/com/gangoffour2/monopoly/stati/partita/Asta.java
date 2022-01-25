package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import com.gangoffour2.monopoly.eccezioni.OffertaInvalidaException;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode
public class Asta extends StatoPartita {
    com.gangoffour2.monopoly.model.Asta astaCorrente;
    Proprieta proprieta;

    @Override
    public void onTimeout() {
        if (astaCorrente.getMiglioreOfferente() != null){
            astaCorrente.getMiglioreOfferente().aggiudica(astaCorrente.getProp(),
                    astaCorrente.getOffertaAttuale());
        }
        partita.setStato(LancioDadi.builder().build());
        partita.turnoStandard();
    }

    @Override
    public void esegui(RichiediAcquisto richiediAcquisto){
         astaCorrente = com.gangoffour2.monopoly.model.Asta.builder().prop(richiediAcquisto.getProprieta()).build();
         partita.attendiAzione();
    }


    @Override
    public boolean onAzioneGiocatore(Offerta offerta){
        if(!offerta.isValida()){
            throw new OffertaInvalidaException(offerta.getGiocatore());
        }
        astaCorrente.offri(offerta.getGiocatore(), offerta.getValore());

        return true;
    }
}
