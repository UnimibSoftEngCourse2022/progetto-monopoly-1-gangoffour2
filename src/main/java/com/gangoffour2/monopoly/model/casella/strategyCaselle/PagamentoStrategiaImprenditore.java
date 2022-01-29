package com.gangoffour2.monopoly.model.casella.strategyCaselle;

import com.gangoffour2.monopoly.model.casella.Tassa;
import com.gangoffour2.monopoly.model.casella.Terreno;

public class PagamentoStrategiaImprenditore extends PagamentoStrategiaGiocatore {
    @Override
    public int calcolaAffitto(Terreno terreno) {

        return (int) Math.floor(super.calcolaAffitto(terreno) - super.calcolaAffitto(terreno)* 0.05);
    }
    @Override
    public int calcolaTassa(Tassa tassa){
        return (int) Math.floor(tassa.getCosto() + tassa.getCosto()*0.75);
    }
}
