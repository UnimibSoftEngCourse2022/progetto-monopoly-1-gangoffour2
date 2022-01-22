package com.gangoffour2.monopoly.model.casella;

import com.gangoffour2.monopoly.stati.casella.StatoPrigione;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Prigione extends Casella{

    protected Prigione(){
        evento = StatoPrigione.builder().prigione(this).build();
    }

}
