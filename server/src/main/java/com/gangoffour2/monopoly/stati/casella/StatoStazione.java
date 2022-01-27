package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.model.casella.Stazione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoStazione implements StatoCasella {
    protected Stazione stazione;

    protected StatoStazione() {

    }
}
