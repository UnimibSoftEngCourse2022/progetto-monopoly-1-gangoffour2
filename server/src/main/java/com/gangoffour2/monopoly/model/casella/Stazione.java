package com.gangoffour2.monopoly.model.casella;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Stazione extends Proprieta {

    private Stazione() {
    }

    @Override
    public void arrivo() {
    }

    @Override
    public void passaggio() {
    }

    @Override
    public void fineGiro() {
    }
}
