package com.gangoffour2.monopoly.stati.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.azioni.casella.AzioneCasella;
import com.gangoffour2.monopoly.azioni.casella.PescaProbabilita;
import com.gangoffour2.monopoly.model.casella.Probabilita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoProbabilita implements StatoCasella {
    @JsonIgnore
    private Probabilita probabilita;

    protected StatoProbabilita(){

    }

    @Override
    public AzioneCasella arrivo() {
        return PescaProbabilita.builder().build();
    }
}
