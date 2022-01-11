package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Via;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoVia extends EventoCasella {

    private Via via;

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
