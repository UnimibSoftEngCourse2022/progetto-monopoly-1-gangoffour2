package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.casella.Societa;
import com.gangoffour2.monopoly.model.casella.Stazione;
import com.gangoffour2.monopoly.model.casella.Tassa;
import com.gangoffour2.monopoly.model.casella.Terreno;
import lombok.Builder;

@Builder
public class AffittiStrategiaImprenditore extends AffittiStrategiaGiocatore{
    @Override
    public int calcolaAffitto(Terreno terreno) {

        return (int) Math.floor(super.calcolaAffitto(terreno) - super.calcolaAffitto(terreno)* 0.05);
    }
    @Override
    public int calcolaTassa(Tassa tassa){
        return (int) Math.floor(tassa.getCosto() + tassa.getCosto()*0.75);
    }
}
