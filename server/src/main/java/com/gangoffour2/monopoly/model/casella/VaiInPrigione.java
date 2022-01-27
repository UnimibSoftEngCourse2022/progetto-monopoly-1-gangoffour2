package com.gangoffour2.monopoly.model.casella;


import com.gangoffour2.monopoly.stati.casella.StatoVaiInPrigione;

public class VaiInPrigione extends Casella {
    public VaiInPrigione(){
        evento = StatoVaiInPrigione.builder().vaiInPrigione(this).build();
    }
}
