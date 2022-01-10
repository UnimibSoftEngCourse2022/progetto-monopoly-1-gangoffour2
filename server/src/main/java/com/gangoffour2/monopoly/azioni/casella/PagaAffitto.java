package com.gangoffour2.monopoly.azioni.casella;

import com.gangoffour2.monopoly.model.Giocatore;
import com.gangoffour2.monopoly.stati.partita.StatoPartita;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class PagaAffitto implements AzioneCasella{
    protected Giocatore proprietario;

    private PagaAffitto(){
    }

    @Override
    public void accept(StatoPartita statoPartita) {
        statoPartita.onAzioneCasella(this);
    }
}
