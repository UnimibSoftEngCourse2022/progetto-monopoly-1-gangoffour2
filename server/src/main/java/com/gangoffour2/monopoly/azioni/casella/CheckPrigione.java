package com.gangoffour2.monopoly.azioni.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CheckPrigione implements AzioneCasella {
    private Giocatore giocatore;
    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneCasella(this);
    }
}
