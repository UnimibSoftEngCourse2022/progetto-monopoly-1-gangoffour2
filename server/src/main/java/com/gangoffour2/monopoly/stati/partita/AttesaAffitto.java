package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.PagaAffittoAzione;
import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Builder;

@Builder
public class AttesaAffitto extends StatoPartita {

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void onAzioneGiocatore(PagaAffittoAzione pagaAffittoAzione) {
        Giocatore debitore = partita.getTurnoCorrente().getGiocatore();
        Proprieta proprieta = (Proprieta) debitore.getCasellaCorrente();
        debitore.paga(proprieta.getProprietario(), proprieta.calcolaAffitto());
        partita.turnoStandard();
    }
}
