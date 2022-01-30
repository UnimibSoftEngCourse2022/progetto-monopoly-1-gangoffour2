package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.casella.Stazione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class StatoStazione implements StatoCasella {
    @JsonIgnore
    protected Stazione stazione;

    protected StatoStazione() {

    }
}
