package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Terreno;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class EventoTerreno implements EventoCasella{
    protected Terreno terreno;
    protected EventoTerreno(){
    }
}
