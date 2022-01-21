package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.StazioneNonAcquistata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonTypeName("Stazione")
public class Stazione extends Proprieta {

    @Override
    public String getTipo() {
        return "Stazione";
    }

    private Stazione() {
        evento = StazioneNonAcquistata.builder().stazione(this).build();
    }

    @Override
    public int calcolaAffitto() {
        // Da decidere come calcolare l'affitto
        return 200;
    }


}
