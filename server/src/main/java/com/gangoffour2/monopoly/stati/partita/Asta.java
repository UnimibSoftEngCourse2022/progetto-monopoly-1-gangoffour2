package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import com.gangoffour2.monopoly.azioni.giocatore.AstaTerminata;
import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import com.gangoffour2.monopoly.eccezioni.OffertaInvalidaException;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class Asta extends StatoPartita {
    com.gangoffour2.monopoly.model.Asta astaCorrente;
    Proprieta proprieta;
    StatoPartita statoPrecedente;

    @Override
    public void onTimeout() {
        if (astaCorrente.getMiglioreOfferente() != null) {
            astaCorrente.getMiglioreOfferente().aggiudica(astaCorrente.getProp(),
                    astaCorrente.getOffertaAttuale());
        }
        partita.setStato(statoPrecedente);
        partita.getStato().esegui(AstaTerminata.builder().build());
    }

    @Override
    public void esegui(RichiediAcquisto richiediAcquisto) {
        astaCorrente = com.gangoffour2.monopoly.model.Asta.builder().prop(richiediAcquisto.getProprieta()).build();
        partita.attendiAzione();
    }


    @Override
    public boolean onAzioneGiocatore(Offerta offerta) {
        if (!offerta.isValida()) {
            throw new OffertaInvalidaException(offerta.getGiocatore());
        }
        astaCorrente.offri(offerta.getGiocatore(), offerta.getValore());

        return true;
    }
}
