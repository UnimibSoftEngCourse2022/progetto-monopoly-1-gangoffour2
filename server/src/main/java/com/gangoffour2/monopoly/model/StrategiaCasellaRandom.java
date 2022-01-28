package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;

import java.security.SecureRandom;

@Builder
public class StrategiaCasellaRandom implements RandomCaselleStrategy{
    @Override
    public void randomizzaCasella(Casella c) {
        float moltiplicatore = (float) (new SecureRandom().nextFloat()*1.5 + 0.5);
        c.randomizzaCasella(moltiplicatore);
    }
}
