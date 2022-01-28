package com.gangoffour2.monopoly.model.casella;


import com.gangoffour2.monopoly.stati.casella.StatoVaiInPrigione;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class VaiInPrigione extends Casella {
    public VaiInPrigione(){
        evento = StatoVaiInPrigione.builder().vaiInPrigione(this).build();
    }
}
