package com.gangoffour2.monopoly.stati.casella;

import com.gangoffour2.monopoly.azioni.casella.ArrestaGiocatore;
import com.gangoffour2.monopoly.model.casella.VaiInPrigione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StatoVaiInPrigione extends EventoCasella{
    VaiInPrigione vaiInPrigione;

    @Override
    public void arrivo(){
        ArrestaGiocatore.builder().build();
    }
}
