package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.Offerta;
import com.gangoffour2.monopoly.eccezioni.OffertaInvalidaException;
import com.gangoffour2.monopoly.model.Asta;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class StatoAsta extends StatoPartita {
    Asta astaCorrente;
    Proprieta proprieta;

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onTimeout() {
        if (astaCorrente.getMiglioreOfferente() != null) {
            astaCorrente.getMiglioreOfferente().aggiudica(astaCorrente.getProp(),
                    astaCorrente.getOffertaAttuale());
        }
        partita.continua(this);
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneGiocatore(Offerta offerta) {
        try{
            astaCorrente.offri(offerta.getGiocatore(), offerta.getValore());
            partita.fermaAttesa();
            partita.attendiAzione();
        } catch (OffertaInvalidaException ignored){
            // Non è necessaria alcuna azione
        }
    }
}
