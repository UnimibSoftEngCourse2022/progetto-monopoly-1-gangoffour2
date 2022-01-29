package com.gangoffour2.monopoly.model.casella.strategyCaselle;

import com.gangoffour2.monopoly.model.casella.Societa;
import com.gangoffour2.monopoly.model.casella.Stazione;
import com.gangoffour2.monopoly.model.casella.Tassa;
import com.gangoffour2.monopoly.model.casella.Terreno;


public class PagamentoStrategiaGiocatore implements PagamentoStrategy {
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
        return terreno.getRendita();
    }
    @Override
    public int calcolaAffitto(Stazione stazione){
        return stazione.getRendita();
    }
    @Override
    public int calcolaAffitto(Societa societa) {
        return societa.getRendita();
    }
    @Override
    public int calcolaTassa(Tassa tassa){
        return tassa.getCosto();
    }
}
