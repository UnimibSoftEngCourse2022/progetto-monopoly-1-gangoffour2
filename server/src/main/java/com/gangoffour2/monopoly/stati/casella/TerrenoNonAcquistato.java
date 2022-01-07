package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.RichiediAcquisto;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TerrenoNonAcquistato extends EventoTerreno{

    private TerrenoNonAcquistato(){

    }

    @Override
    public void arrivo() {
        terreno.notificaTutti(RichiediAcquisto.builder().build());
    }

    @Override
    public void passaggio() {

    }

    @Override
    public void fineGiro() {

    }
}
