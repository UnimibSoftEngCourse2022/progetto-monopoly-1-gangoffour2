package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.PagaAffittoAzione;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = true)
public class AttesaAffitto extends StatoPartita {
    private Proprieta proprieta;

    @Override
    public void onTimeout() {
        Giocatore debitore = partita.getTurnoCorrente().getGiocatore();
        debitore.paga(proprieta.getProprietario(), proprieta.calcolaAffitto());
        partita.continuaTurno();
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneGiocatore(PagaAffittoAzione pagaAffittoAzione) {
        Giocatore debitore = pagaAffittoAzione.getGiocatore();
        debitore.paga(proprieta.getProprietario(), proprieta.calcolaAffitto());
        partita.continuaTurno();
    }


}
