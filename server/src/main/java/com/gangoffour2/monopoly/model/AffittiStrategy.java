package com.gangoffour2.monopoly.model;

import com.gangoffour2.monopoly.model.carta.Carta;
import com.gangoffour2.monopoly.model.casella.Societa;
import com.gangoffour2.monopoly.model.casella.Stazione;
import com.gangoffour2.monopoly.model.casella.Tassa;
import com.gangoffour2.monopoly.model.casella.Terreno;

public interface AffittiStrategy {
    int calcolaAffitto(Terreno terreno);
    int calcolaAffitto(Stazione stazione);
    int calcolaAffitto(Societa societa);
    int calcolaTassa(Tassa tassa);


}
