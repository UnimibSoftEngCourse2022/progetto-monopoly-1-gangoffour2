package com.gangoffour2.monopoly.model.casella;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gangoffour2.monopoly.stati.casella.SocietaNonAcquistata;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonTypeName("Societa")
public class Societa extends Proprieta {
    @Override
    public String getTipo() {
        return "Societa";
    }

    //manca img
    private Societa() {
        evento = SocietaNonAcquistata.builder().societa(this).build();
    }

    @Override
    public int calcolaAffitto() {
        // Da rivedere come calcolare l'affitto per la società
        return 200;
    }

}
