package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Prigione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoPrigione extends EventoCasella {

    private Prigione prigione;

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
