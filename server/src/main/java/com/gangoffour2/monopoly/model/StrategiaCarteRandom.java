package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.carta.Carta;
import lombok.Builder;

import java.security.SecureRandom;

@Builder
public class StrategiaCarteRandom implements RandomCarteStrategy{
    @Override
    public void randomizzaCarta(Carta c) {
        float moltiplicatore = (float) (new SecureRandom().nextFloat()*1.5 + 0.5);
        c.randomizzaCarta(moltiplicatore);
    }
}
