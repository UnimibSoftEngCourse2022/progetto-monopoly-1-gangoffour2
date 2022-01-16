package com.gangoffour2.monopoly.stati.partita;

import lombok.Builder;

@Builder
public class FineTurno extends StatoPartita{

    @Override
    public void onTimeout(){
        partita.cambiaTurno();
    }
}
