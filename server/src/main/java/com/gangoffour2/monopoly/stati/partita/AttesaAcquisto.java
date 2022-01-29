package com.gangoffour2.monopoly.stati.partita;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.giocatore.AcquistaProprieta;
import com.gangoffour2.monopoly.azioni.giocatore.AvviaAsta;
import com.gangoffour2.monopoly.model.Asta;
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
    public void riprendi(){
        partita.setStato(FineTurno.builder().build());
        partita.getStato().esegui();
    }

    @Override
    public void onAzioneGiocatore(AcquistaProprieta acquistaProprieta) {
        partita.fermaAttesa();
        acquistaProprieta.getGiocatore().getCasellaCorrente().onAzioneGiocatore(acquistaProprieta);
        partita.continua();
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