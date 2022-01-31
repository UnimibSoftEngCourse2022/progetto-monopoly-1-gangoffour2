package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.*;
import com.gangoffour2.monopoly.model.Asta;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Builder;

import java.util.Optional;

@Builder
public class FineTurno extends StatoPartita {

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void onTimeout() {
        partita.cambiaTurno();
    }

    @Override
    public void esegui(){
        partita.attendiAzione();
    }


    @Override
    public void riprendi(StatoAsta statoAsta){
        partita.attendiAzione();
    }

    @Override
    public void onAzioneGiocatore(TerminaTurno terminaTurno){
        partita.fermaAttesa();
        partita.cambiaTurno();
    }

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        Optional<Proprieta> qry = findProprieta(ipoteca.getGiocatore(), ipoteca.getProprieta());
        qry.ifPresent(proprieta -> proprieta.onAzioneGiocatore(ipoteca));
        partita.continua(this);
    }

    @Override
    public void onAzioneGiocatore(UpgradaTerreno upgradeTerreno){
        Optional<Proprieta> qry = findProprieta(upgradeTerreno.getGiocatore(), upgradeTerreno.getTerreno());
        qry.ifPresent(proprieta -> proprieta.onAzioneGiocatore(upgradeTerreno));
        partita.continua(this);
    }


    @Override
    public void onAzioneGiocatore(DowngradaTerreno downgradeTerreno){
        Optional<Proprieta> qry = findProprieta(downgradeTerreno.getGiocatore(), downgradeTerreno.getTerreno());
        qry.ifPresent(proprieta -> proprieta.onAzioneGiocatore(downgradeTerreno));
        partita.continua(this);
    }

    @Override
    public void onAzioneGiocatore(AvviaAsta avviaAsta){
        Optional<Proprieta> query = findProprieta(avviaAsta.getGiocatore(), avviaAsta.getProprieta());
        query.ifPresent(casella -> {
            partita.memorizzaStato(this);
            partita.setStato(StatoAsta.builder()
                    .proprieta(casella)
                    .astaCorrente(Asta.builder().prop(casella).build())
                    .build());
            partita.getStato().esegui();
        });
    }



    private Optional<Proprieta> findProprieta(Giocatore giocatore, Proprieta proprieta){
        return giocatore.getProprietaPossedute().stream()
                .filter(c -> c.getId() == proprieta.getId()).findFirst();
    }
}
