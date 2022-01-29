package com.gangoffour2.monopoly.model.casella.strategyCaselle;

import com.gangoffour2.monopoly.model.casella.Casella;
import lombok.Builder;

import java.security.SecureRandom;

@Builder
public class StrategiaCasellaRandom implements RandomCaselleStrategy{
    @Override
    public void randomizzaCasella(Casella c) {
        float moltiplicatore = (float) (new SecureRandom().nextFloat()*1.1 + 0.9);
        c.randomizzaCasella(moltiplicatore);
    }
}
