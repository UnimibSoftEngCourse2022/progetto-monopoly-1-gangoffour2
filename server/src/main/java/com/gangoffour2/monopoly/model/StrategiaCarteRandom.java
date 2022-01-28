package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.carta.Carta;

import java.security.SecureRandom;

public class StrategiaCarteRandom implements RandomCarteStrategy{
    @Override
    public void randomizzaCarta(Carta c) {
        float moltiplicatore = (float) (new SecureRandom().nextFloat()*1.5 + 0.5);
        c.randomizzaCarta(moltiplicatore);
    }
}
