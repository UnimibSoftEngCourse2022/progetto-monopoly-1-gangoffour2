package com.gangoffour2.monopoly.model.casella.strategy;

import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;

import java.security.SecureRandom;

@Builder
public class StrategiaCasellaRandom implements ProprietaCaselleStrategy {
    @Override
    public void randomizzaCasella(Casella c) {
        float moltiplicatore = (float) (new SecureRandom().nextFloat()*0.4 + 0.8);
        c.randomizzaCasella(moltiplicatore);
    }
}
