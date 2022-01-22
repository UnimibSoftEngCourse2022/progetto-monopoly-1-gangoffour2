package com.gangoffour2.monopoly.model.carta;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
public abstract class Carta implements Serializable {
    private String testo;
    public abstract void Esegui();
    public abstract void Effetto();
}
