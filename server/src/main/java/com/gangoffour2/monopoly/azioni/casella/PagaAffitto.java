package com.gangoffour2.monopoly.azioni.casella;

import com.gangoffour2.monopoly.model.casella.Proprieta;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PagaAffitto implements AzioneCasella {
    protected Proprieta proprieta;

    private PagaAffitto() {
    }

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneCasella(this);
    }
}
