package com.gangoffour2.monopoly.azioni.casella;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RichiediAcquisto implements AzioneCasella {

    @JsonIgnore
    private Proprieta proprieta;

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneCasella(this);
    }
}
