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
        if (terreno.getAlbergo() != null) {
            return terreno.getAffitti().get(terreno.getAffitti().size() - 1);
        } else if (terreno.getListaCase().size() > terreno.getAffitti().size()) {
            return terreno.getAffitti().get(terreno.getAffitti().size() - 2);
        }
        if (!terreno.getListaCase().isEmpty()) {
            return terreno.getAffitti().get(terreno.getListaCase().size() - 1);
        }
        return (int) Math.floor(terreno.getRendita() - terreno.getRendita()* 0.05);
    }
    @Override
    public int calcolaTassa(Tassa tassa){
        return (int) Math.floor(tassa.getCosto() + tassa.getCosto()*0.75);
    }
}
