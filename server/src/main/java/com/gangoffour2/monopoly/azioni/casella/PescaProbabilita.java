package com.gangoffour2.monopoly.azioni.casella;

import com.gangoffour2.monopoly.stati.partita.StatoPartita;

public class PescaProbabilita implements AzioneCasella{

    @Override
    public void accept(StatoPartita statoPartita) throws InterruptedException {
        statoPartita.onAzioneCasella(this);
    }
}
