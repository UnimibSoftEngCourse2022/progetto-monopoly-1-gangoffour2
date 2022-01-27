package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Terreno;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoTerreno extends StatoCasella {
    protected Terreno terreno;

    protected StatoTerreno(){

    }
}