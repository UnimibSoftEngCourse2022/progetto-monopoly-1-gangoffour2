package com.gangoffour2.monopoly.model.casella;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Societa extends Proprieta {
    //manca img
    private Societa() {
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
