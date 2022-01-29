package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.model.AffittiStrategy;
import com.gangoffour2.monopoly.stati.casella.SocietaNonAcquistata;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Societa extends Proprieta {

    //manca img
    private Societa() {
        stato = SocietaNonAcquistata.builder().societa(this).build();
    }

    @Override
    public int calcolaAffitto(AffittiStrategy strategia) {
        return strategia.calcolaAffitto(this);
    }

}
