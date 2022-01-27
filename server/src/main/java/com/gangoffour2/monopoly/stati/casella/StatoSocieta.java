package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Societa;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoSocieta implements StatoCasella {
    protected Societa societa;

    protected StatoSocieta() {

    }
}
