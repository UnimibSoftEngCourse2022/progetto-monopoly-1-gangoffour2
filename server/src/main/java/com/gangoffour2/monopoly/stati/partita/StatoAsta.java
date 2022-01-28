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
    StatoPartita statoPrecedente;

    @Override
    public void onTimeout() {
        if (astaCorrente.getMiglioreOfferente() != null) {
            astaCorrente.getMiglioreOfferente().aggiudica(astaCorrente.getProp(),
                    astaCorrente.getOffertaAttuale());
        }
        partita.setStato(statoPrecedente);
        partita.getStato().esegui();
    }

    @Override
    public void esegui() {
        astaCorrente = com.gangoffour2.monopoly.model.Asta.builder()
                .prop(proprieta)
                .build();
        partita.attendiAzione();
    }

    @Override
    public void onAzioneGiocatore(Offerta offerta) {
        partita.fermaAttesa();
        if (!offerta.isValida()) {
            throw new OffertaInvalidaException(offerta.getGiocatore());
        }
        astaCorrente.offri(offerta.getGiocatore(), offerta.getValore());
    }
}
