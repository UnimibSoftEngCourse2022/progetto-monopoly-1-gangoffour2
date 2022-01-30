package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.giocatore.LanciaDadi;
import com.gangoffour2.monopoly.azioni.giocatore.Paga;
import com.gangoffour2.monopoly.azioni.giocatore.VaiInPrigioneAzione;
import com.gangoffour2.monopoly.model.casella.Prigione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoPrigione implements StatoCasella {
    @JsonIgnore
    private Prigione prigione;

    protected StatoPrigione(){

    }

    @Override
    public void onAzioneGiocatore(LanciaDadi lanciaDadi){
        prigione.getGiocatoriInPrigione().remove(lanciaDadi.getGiocatore());
    }


    @Override
    public void onAzioneGiocatore(VaiInPrigioneAzione vaiInPrigione){
        prigione.imprigionaGiocatore(vaiInPrigione.getGiocatore());
    }

    @Override
    public void onAzioneGiocatore(Paga paga) {
        try {
            paga.getGiocatore().aggiungiDenaro(-prigione.getCauzione());
            prigione.liberaGiocatore(paga.getGiocatore());
        } catch (Exception e){
            //Non è da gestire
        }
    }
}
