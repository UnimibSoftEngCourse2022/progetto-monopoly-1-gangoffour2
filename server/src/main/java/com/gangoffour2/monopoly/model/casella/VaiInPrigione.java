package com.gangoffour2.monopoly.model.casella;


import com.gangoffour2.monopoly.stati.casella.StatoVaiInPrigione;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class VaiInPrigione extends Casella {
    public VaiInPrigione(){
        stato = StatoVaiInPrigione.builder().vaiInPrigione(this).build();
    }
}
