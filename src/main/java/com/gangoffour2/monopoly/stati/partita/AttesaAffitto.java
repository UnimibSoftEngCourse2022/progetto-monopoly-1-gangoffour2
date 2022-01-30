package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = true)
public class AttesaAffitto extends StatoPartita {
    private Proprieta proprieta;

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onTimeout() {
        Giocatore debitore = partita.getTurnoCorrente().getGiocatore();
        debitore.paga(proprieta.getProprietario(), proprieta.calcolaAffitto(debitore.getStrategiaCalcoloAffitto()));
        partita.continua(this);
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void riprendi(AttesaFallimento attesaFallimento) {
        Giocatore debitore = partita.getTurnoCorrente().getGiocatore();
        Giocatore creditore = proprieta.getProprietario();

        debitore.paga(creditore, attesaFallimento.getSoldiDaPagare());
        partita.continua(this);
    }

    @Override
    public void onAzioneGiocatore(Paga paga) {
        Giocatore debitore = paga.getGiocatore();
        int soldiDaPagare = proprieta.calcolaAffitto(debitore.getStrategiaCalcoloAffitto());
        Giocatore creditore = proprieta.getProprietario();

        try {
            if(!debitore.equals(creditore))
                debitore.paga(creditore, soldiDaPagare);
            partita.continua(this);
        }catch (ModificaDenaroException e){
            partita.memorizzaStato(this);
            partita.setStato(
                    AttesaFallimento.builder()
                            .soldiDaPagare(soldiDaPagare)
                            .build()
            );
            partita.getStato().esegui();
        }
    }


}
