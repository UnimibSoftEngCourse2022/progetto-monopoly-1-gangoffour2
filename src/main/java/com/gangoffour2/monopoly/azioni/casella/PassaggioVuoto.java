package com.gangoffour2.monopoly.azioni.casella;

import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;

@Builder
public class PassaggioVuoto implements AzioneCasella {
    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneCasella(this);
    }
}
