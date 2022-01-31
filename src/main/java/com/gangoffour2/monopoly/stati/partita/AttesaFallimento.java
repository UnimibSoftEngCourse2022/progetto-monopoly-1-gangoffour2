package com.gangoffour2.monopoly.stati.partita;

import com.gangoffour2.monopoly.azioni.giocatore.DowngradaTerreno;
import com.gangoffour2.monopoly.azioni.giocatore.Ipoteca;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.model.giocatore.Giocatore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Optional;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
public class AttesaFallimento extends StatoPartita{

    //sono positivi
    private int soldiDaPagare;

    @Override
    public void acceptRiprendi(StatoPartita statoPartita) {
        statoPartita.riprendi(this);
    }

    @Override
    public void esegui() {
        partita.attendiAzione();
    }

    void checkFallimento() {
        Giocatore giocatore = partita.getTurnoCorrente().getGiocatore();
        if(giocatore.getConto() >= soldiDaPagare){
            partita.continua(this);
        } else {
            partita.attendiAzione();
        }
    }

    @Override
    public void onAzioneGiocatore(Ipoteca ipoteca) {
        Optional<Proprieta> qry = ipoteca.getGiocatore().getProprietaPossedute().stream()
                .filter(c -> c.getId() == ipoteca.getProprieta().getId()).findFirst();
        qry.ifPresent(proprieta -> {
            partita.fermaAttesa();
            proprieta.onAzioneGiocatore(ipoteca);
        });
        checkFallimento();
    }


    @Override
    public void onAzioneGiocatore(DowngradaTerreno downgradeTerreno){
        Optional<Proprieta> qry = findProprieta(downgradeTerreno.getGiocatore(), downgradeTerreno.getTerreno());
        qry.ifPresent(proprieta -> {
            partita.fermaAttesa();
            proprieta.onAzioneGiocatore(downgradeTerreno);
        });
        checkFallimento();
    }


    private Optional<Proprieta> findProprieta(Giocatore giocatore, Proprieta proprieta){
        return giocatore.getProprietaPossedute().stream()
                .filter(c -> c.getId() == proprieta.getId()).findFirst();
    }
}
