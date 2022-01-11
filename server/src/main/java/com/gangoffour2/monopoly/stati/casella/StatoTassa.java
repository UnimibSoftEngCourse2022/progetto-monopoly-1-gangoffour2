package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Tassa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoTassa extends EventoCasella {
    private Tassa tassa;

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
