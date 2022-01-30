package com.gangoffour2.monopoly.stati.partita;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.AvviaAsta;
import com.gangoffour2.monopoly.eccezioni.ModificaDenaroException;
import com.gangoffour2.monopoly.model.Asta;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class AttesaAcquisto extends StatoPartita {
    @JsonIgnore
    Proprieta daAcquistare;

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onTimeout() {
        partita.memorizzaStato(this);
        partita.setStato(StatoAsta.builder()
                .proprieta(daAcquistare)
                .astaCorrente(Asta.builder().prop(daAcquistare).build())
                .build());
        partita.getStato().esegui();
    }


    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    @Override
    public void riprendi(StatoAsta statoPartita){
        partita.continua(this);
    }

    @Override
    public void riprendi(AttesaFallimento attesaFallimento) {
        Giocatore giocatore = partita.getTurnoCorrente().getGiocatore();
        giocatore.getCasellaCorrente().onAzioneGiocatore(
                AcquistaProprieta.builder().giocatore(giocatore).build()
        );
        partita.continua(this);
    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        try {
            acquistaProprieta.getGiocatore().getCasellaCorrente().onAzioneGiocatore(acquistaProprieta);
            partita.fermaAttesa();
            partita.continua(this);
        }catch (ModificaDenaroException e){
            partita.memorizzaStato(this);
            partita.setStato(StatoAsta.builder()
                    .proprieta(daAcquistare)
                    .astaCorrente(Asta.builder()
                            .prop(daAcquistare)
                            .build())
                    .build()
            );
        }
    }


    @Override
    public void onAzioneGiocatore(AvviaAsta avviaAsta){
        partita.fermaAttesa();
        partita.memorizzaStato(this);
        partita.setStato(StatoAsta.builder()
                .proprieta(daAcquistare)
                .astaCorrente(Asta.builder().prop(daAcquistare).build())
                .build());
        partita.getStato().esegui();
    }
}