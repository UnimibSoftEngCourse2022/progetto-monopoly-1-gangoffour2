package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.carta.Probabilita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoProbabilita extends EventoCasella {

    private Probabilita probabilita;

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
