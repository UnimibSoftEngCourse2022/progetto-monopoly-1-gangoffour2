package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StazioneNonAcquistata;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Stazione extends Proprieta {

    private Stazione() {
        evento = StazioneNonAcquistata.builder().stazione(this).build();
    }

    @Override
    public int calcolaAffitto() {
        // Da decidere come calcolare l'affitto
        return 200;
    }


}
